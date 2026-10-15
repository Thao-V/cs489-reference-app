# Run in development mode
docker compose -f compose.dev.yaml up
mvn spring-boot:run
# Run with Test Server
./setup-env.sh
docker compose up -d --build