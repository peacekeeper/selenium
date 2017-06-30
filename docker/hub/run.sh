#!/bin/bash

cp SeleniumGridExtras/target/hub_4444.json.input SeleniumGridExtras/target/hub_4444.json

echo "grid.uuid=$1" >>SeleniumGridExtras/target/statusupdate.properties
echo "grid.name=$2" >>SeleniumGridExtras/target/statusupdate.properties
echo "grid.location=$3" >>SeleniumGridExtras/target/statusupdate.properties
echo "grid.authid=$4" >>SeleniumGridExtras/target/statusupdate.properties
echo "grid.authkey=$5" >>SeleniumGridExtras/target/statusupdate.properties

/etc/init.d/nginx start

cd SeleniumGridExtras/target && java -jar SeleniumGridExtras-1.12.12-SNAPSHOT-jar-with-dependencies.jar
