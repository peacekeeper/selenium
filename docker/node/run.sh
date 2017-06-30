#!/bin/bash

sed "s/XXHUBHOSTXX/$1/g" SeleniumGridExtras/target/node_5555.json.input > SeleniumGridExtras/target/node_5555.json

cd SeleniumGridExtras/target && java -jar SeleniumGridExtras-1.12.12-SNAPSHOT-jar-with-dependencies.jar
