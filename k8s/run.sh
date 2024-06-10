kubectl apply -f database/secret.yaml
kubectl apply -f rabbitmq/secret.yaml
kubectl apply -f database/pv.yaml
kubectl apply -f database/pvc.yaml
kubectl apply -f database/deployment.yaml
kubectl apply -f database/svc.yaml
kubectl apply -f rabbitmq/deployment.yaml
kubectl apply -f rabbitmq/svc.yaml
kubectl apply -f hotelaria/deployment.yaml
kubectl apply -f hotelaria/svc.yaml
kubectl apply -f node-handler/deployment.yaml
kubectl apply -f node-handler/svc.yaml


