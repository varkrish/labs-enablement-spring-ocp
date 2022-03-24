==2 hours==
mvn  -DskipTests clean package
cp target/*.jar dist/app.jar
java -jar dist/app.jar
-------
cat Dockerfile | oc new-build -D - --name springbootapp
oc start-build springbootapp --from-dir ./dist --follow  
oc new-app --name springbootapp springbootapp
oc new-app --name springbootapp-2 springbootapp
oc new-app  --name springbootapp-2 springbootapp -e spring.application.name=test
oc set env dc/springbootapp spring.application.name=springboot3
##Service to service communication

0. App metrics via ocp console
1. Create a service for the app
2. Use the url to curl from linux
3. Port-forwarding for local test
#access service external access
4. Create route to access the app outside the cluster -
# Give info k8s service discovery
# Openshift annotations on topology
# Labels
5. Adhoc tasks - Spring cloud task
6. Jobs and cronjobs - Spring batch
5. Scaling? oc scale dc/ 2 -auto scaling, hpa and vpa

##
===deployment descriptors
1. Deployment, deploymentconfig, service route etc., 
2. Helm
=====
Intro
=====
----
#with config
 mvn -DskipTests package
 java -jar dist/app.jar --spring.application.name=test
-------





