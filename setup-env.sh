#!/bin/bash
set -euo pipefail

ENV_FILE=".env"

# Create .env only if it does not exist
if [ ! -f "$ENV_FILE" ]; then
    echo "Creating database environment..."

    MYSQL_PASSWORD=$(openssl rand -hex 16)
    MYSQL_ROOT_PASSWORD=$(openssl rand -hex 16)

    cat > "$ENV_FILE" <<EOF
MYSQL_DATABASE=my_database
MYSQL_USER=app_user
MYSQL_PASSWORD=$MYSQL_PASSWORD
MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD
EOF

    chmod 600 "$ENV_FILE"
fi

echo "Database environment is ready."