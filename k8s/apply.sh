#!/bin/bash

kubectl apply -f k8s/config-map-dev.yaml
kubectl apply -f k8s/config-map-preprod.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml