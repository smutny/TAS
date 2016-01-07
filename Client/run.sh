#!/bin/sh

if [ "$1" = "run" ]; then
  mvn jetty:run
else
  mvn install
  mvn jetty:run
fi
