#!/bin/sh

mysql -u $1 -p < resources/query.sql
