#!/bin/bash

./gradlew clean build

echo "Starting Server..."
./gradlew run &
JETTY_PID=$!

cleanup() {
  echo "Stopping Server..."
  kill $JETTY_PID
}

trap cleanup EXIT

cd src/visualization || exit

npm start
