#!/bin/sh

amqNamespace=$1


sleep 15
echo "Navigating to amq project"

oc project $amqNamespace

echo "Getting pods"
pods=($(oc get pods | grep -v operator | awk 'NR >1{print $1}'))

echo "Deleting random pod"

for i in {1..4}
do
  random=$(shuf -i 0-2 -n 1)
  oc delete pod ${pods[$random]}
  sleep 50
done




