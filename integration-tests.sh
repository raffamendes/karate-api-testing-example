#!/bin/sh 

token=$1

server=$2

amqNamespace=$3
echo "---------------------------------------"
echo "Login into Openshift"
echo "---------------------------------------"
oc login --token=$token --server=$server

echo "---------------------------------------"
echo "Starting pod deletion routine in the background"
echo "---------------------------------------"
./delete-pods.sh $amqNamespace&

echo "---------------------------------------"
echo "Starting Karate Tests"
echo "---------------------------------------"

mvn clean test-compile gatling:test

echo "---------------------------------------"
echo "Scaling Down amq cluster to test message migration"
echo "---------------------------------------"

oc apply -f amq.yaml





