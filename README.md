This repo contains sample spring boot applications (client and a service) and spring components such as Config Server, Cloud Gateway. The aim of this repo is to demonstrate on how we can deploy the items on OCP.
<br>
<br>
Repo structure
<br>
basic<br>
|<br>
|-- simple-microservice<br>
|-- simple-microservice-client<br>
|<br>
helm<br>
|<br>
|-- ci-common-charts<br>
|-- rollouts-blue-green<br>
|-- rollouts-canary<br>
|-- spring-component<br>
|-- spring-config-server<br>
|<br>
spring-components<br>
|<br>
|-- cloud-gateway<br>
|-- config-service<br>


basic<br>
simple-microservice
- a simple spring boot REST application to demonstrate GET methods<br>

simple-microservice-client
- a simple spring boot REST application that calls to the simple-microservice app<br>


helm<br>
ci-common-charts
- {description}

rollouts-blue-green
- a helm chart that uses Argo Rollouts Blue-Green deployment strategy
- creates the following k8s objects
    - hpa
    - ingress (TODO: change to route)
    - role
    - rolebinding
    - rollout
    - service-preview
    - service
    - serviceaccount   
- to quickly deploy the chart set the following, check the Values.yaml file for full list of properties that can be set
    - image.repository
    - image.tag
    - rollouts.autoPromotionEnabled (bool) -> set to true on Values.yaml, controls the deployment if the blue (preview) will be automatically promoted to green (stable). If set to falls, manual promotion can be done using the Argo Rollouts UI or cli<br>

rollouts-canary (not yet tested WIP)
- a helm chart that uses Argo Rollouts Canary deployment strategy

spring-components
- a helm chart that deploys a spring boot image
- creates the following k8s objects
    - deployment
    - ingress (TODO: change to route)
    - role
    - rolebinding
    - service
    - serviceaccount   
- to quickly deploy the chart set the following, check the Values.yaml file for full list of properties that can be set
    - image.repository
    - image.tag


spring-config-server
- a helm chart that deploys a spring boot image with PVC and XDG_HOME_CONFIG set to /tmp
- creates the following k8s objects
    - deployment
    - ingress (TODO: change to route)
    - pvc
    - service
    - serviceaccount   
- to quickly deploy the chart set the following, check the Values.yaml file for full list of properties that can be set
    - image.repository
    - image.tag


spring-components<br>
cloud-gateway
- a simple spring boot cloud gateway app that uses application.properties to configure the gateway
- no security is implemented

config-service
- a simple spring config server app that uses application.properties to configure the config server
- no security is implemented