#!/bin/bash

# Остановить выполнение скрипта при ошибке
set -e

# Загрузить переменные среды из файла .env
source .env

# Построить проект без тестов
./gradlew build -x test

# Построить Docker образы
docker build -f Dockerfile-eureka-gateway -t dmitriy2028/eureka-gateway .
docker build -f Dockerfile-auth-user-cart -t dmitriy2028/auth-user-cart .
docker build -f Dockerfile-catalog -t dmitriy2028/catalog .

# Запустить контейнеры
docker-compose up -d