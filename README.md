# WebClient
## Running Prometheus

1. Open Docker
2. `docker run -d -p 9091:9090 -v "/$(pwd)/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml" prom/prometheus`

Useful Prometheus queries for WebFlux start with:
reactor_netty_connection_provider_

## Running Gatling - Performance Testing framework

**Ensure server and WebFlux client is running before attempting Gatling run**

`./gradlew gatlingRun `

**Example output of Terminal window and report found in *examples***


