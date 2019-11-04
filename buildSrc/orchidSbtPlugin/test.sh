#!/usr/bin/env bash

if [ "$1" = "release" ]; then
  echo "[SBT Plugin] Test Release"
  ./sbtw -Dorchid.version=${GRADLE_PROJECT_RELEASE_NAME} -Dorchid.testbuild=true scripted
else
  echo "[SBT Plugin] Test Debug"
  ./sbtw -Dorchid.version=${GRADLE_PROJECT_RELEASE_NAME} -Dorchid.testbuild=true scripted
fi
