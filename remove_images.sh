#!/bin/bash
docker image rm -f \
 services_authorization-service:latest \
 services_book-service:latest \
 services_config-service:latest \
 services_discovery-service:latest \
 services_gateway-service:latest
