rm deployment/charts/*
cd /Users/varunkrishnamurthy/projects/dbs/dbs-cicd-common-helm
helm package chart/  
cp common_ci_template-1.0.0.tgz /Users/varunkrishnamurthy/projects/labs-enablement-spring-ocp/basic_helm/simple-microservice/deployment/charts/
cd - 
helm template deployment --debug