#!/usr/bin/env bash

echo 'Execute docker build'
set -x
docker build -t rafael.altagnam/spring-security .
set +x

echo 'Execute docker run'
set -x
docker run -d -p 2000:8080 rafael.altagnam/spring-security
set +x

