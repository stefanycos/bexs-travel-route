#!/bin/sh
 
csvFileName=$1

echo $csvFileName

echo "Starting travel route service..."

mvn exec:java -Dexec.args=$csvFileName