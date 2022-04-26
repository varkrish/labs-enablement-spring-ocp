Red Hat Openshift Servicemesh (RHOS) Traffic Management

Pre-requisites
1. RHOS operator is installed
2. RHOS ControlPlane and MemberRoll is installed in istio-samplemesh
3. samplemesh project is already created

```bash
oc login <credentials>
oc new-project samplemesh
oc new-project istio-samplemesh

cd dbs-servicemesh
helm upgrade istio . --install -n istio-samplemesh \
--set memberroll.members={samplemesh} \
--set virtualService.enabled=false \
--set destinationRule.enabled=false

cd ..
```


Deploy 2 Services using the workload helm chart. The workload helm chart uses the common_ci_template helm library chart

```bash
cd workload
oc project samplemesh
```

Service
```bash
helm upgrade sample-service . -n samplemesh --install \
--set image_repository=quay.io \
--set image_name=estaana/simple-service \
--set image_version=latest \
--set controller.enabled=true \
--set controller.type="deployment" \
--set route=false \
--set env.server_port: 8080 \
--set env.spring.application.name: service1
```

Client
```bash
helm upgrade sample-client . -n samplemesh --install \
--set image_repository=quay.io \
--set image_name=estaana/simple-client \
--set image_version=latest \
--set controller.enabled=true \
--set controller.type="deployment" \
--set route=false \
--set env.server_port: 8080 \
--set env.service.baseuri=http://sample-serivce-workload:8080
```

Setup the mesh resources
```bash
oc apply -f gateway.yaml

# faultinject delay sample
oc apply -f faultinjection.yaml
# clean-up
oc delete virtualservice faultinjection

# traffic shifting using host
oc apply -f tafficshifting-host.yaml
# clean-up
oc delete virtualservice trafficshifting-host

# circuit breaking
oc apply -f circuitbreaking.yaml
# test
fortio load -c 4 http://<route>/servicename 
# clean-up
oc delete virtualservice circuitbreaking
oc delete destinationrule circuitbreaking

# mirroring
oc apply -f mirroring.yaml
# clean-up
oc delete virtualservice mirroring

# request timeout
oc apply -f requesttimeout.yaml
# clean-up
oc delete virtualservice sample-service
oc delete virtualservice requesttimeout

```