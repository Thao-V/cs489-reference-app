
#!/bin/bash
set -euo pipefail

# Docker setup for Amazon Linux 2023
# Run as ec2-user, without sudo

if [ "$(id -u)" -eq 0 ]; then
    echo "Please run as ec2-user, not root."
    exit 1
fi

# 1. Install Docker
sudo dnf update -y
sudo dnf install -y docker git curl file

sudo systemctl enable --now docker
sudo usermod -aG docker "$(whoami)"

# 2. Detect architecture
case "$(uname -m)" in
    x86_64)
        ARCH="amd64"
        ;;
    aarch64)
        ARCH="arm64"
        ;;
    *)
        echo "Unsupported architecture"
        exit 1
        ;;
esac

# 3. Create plugin directory
PLUGIN_DIR="$HOME/.docker/cli-plugins"
mkdir -p "$PLUGIN_DIR"

# 4. Install Docker Compose
COMPOSE_VERSION="v5.6.0"

curl -fSL --retry 3 \
    "https://github.com/docker/compose/releases/download/${COMPOSE_VERSION}/docker-compose-linux-$(uname -m)" \
    -o "$PLUGIN_DIR/docker-compose.tmp"

file "$PLUGIN_DIR/docker-compose.tmp" | grep -q ELF
chmod +x "$PLUGIN_DIR/docker-compose.tmp"
mv "$PLUGIN_DIR/docker-compose.tmp" \
   "$PLUGIN_DIR/docker-compose"

# 5. Install Docker Buildx
BUILDX_VERSION="v0.38.0"

curl -fSL --retry 3 \
    "https://github.com/docker/buildx/releases/download/${BUILDX_VERSION}/buildx-${BUILDX_VERSION}.linux-${ARCH}" \
    -o "$PLUGIN_DIR/docker-buildx.tmp"

file "$PLUGIN_DIR/docker-buildx.tmp" | grep -q ELF
chmod +x "$PLUGIN_DIR/docker-buildx.tmp"
mv "$PLUGIN_DIR/docker-buildx.tmp" \
   "$PLUGIN_DIR/docker-buildx"

# 6. Verify
echo "=== Docker Versions ==="
docker --version
docker compose version
docker buildx version

echo ""
echo "Docker setup completed."
echo "Log out and reconnect via SSH to activate Docker permissions."
echo ""
echo "After reconnecting, run:"
echo "  docker ps"
echo "  docker compose up -d --build"
