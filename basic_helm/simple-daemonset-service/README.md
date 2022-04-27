# Sample Helm Chart for DaemonSet
 
## How To Debug 

 ```
$ helm template --debug deployment
```

the result would be a `yaml` display,
```yaml

# Source: simple-daemonfulset-service/templates/renderall.yaml
apiVersion: v1
kind: Service
metadata:
  name: release-name-simple-daemonfulset-service
  labels:
    helm.sh/chart: simple-daemonfulset-service-0.1.0
    app.kubernetes.io/name: simple-daemonfulset-service
    app.kubernetes.io/component: simple-daemonfulset-service
    app.kubernetes.io/instance: release-name
    deploymentconfig: release-name-simple-daemonfulset-service
    app: release-name-simple-daemonfulset-service
    version: latest
    app.kubernetes.io/version: "1.16.0"
    app.kubernetes.io/managed-by: Helm
spec:
  ports:
    - port: 8080
      protocol: TCP
      targetPort: 8080
      name: tcp-8080
    - port: 8443
      protocol: TCP
      targetPort: 8443
      name: tcp-8443
  selector:
    app.kubernetes.io/name: simple-daemonfulset-service
    app.kubernetes.io/component: simple-daemonfulset-service
    app.kubernetes.io/instance: release-name
    deploymentconfig: release-name-simple-daemonfulset-service
    app: release-name-simple-daemonfulset-service
    version: latest
  sessionAffinity: None
  type: ClusterIP
status:
  loadBalancer: {}
---
# Source: simple-daemonfulset-service/templates/renderall.yaml
apiVersion: apps/v1
kind: DaemonSet
metadata:
    name: release-name-simple-daemonfulset-service
    labels:
        helm.sh/chart: simple-daemonfulset-service-0.1.0
        app.kubernetes.io/name: simple-daemonfulset-service
        app.kubernetes.io/component: simple-daemonfulset-service
        app.kubernetes.io/instance: release-name
        deploymentconfig: release-name-simple-daemonfulset-service
        app: release-name-simple-daemonfulset-service
        version: latest
        app.kubernetes.io/version: "1.16.0"
        app.kubernetes.io/managed-by: Helm
spec:

    selector:
        matchLabels:
            helm.sh/chart: simple-daemonfulset-service-0.1.0
            app.kubernetes.io/name: simple-daemonfulset-service
            app.kubernetes.io/component: simple-daemonfulset-service
            app.kubernetes.io/instance: release-name
            deploymentconfig: release-name-simple-daemonfulset-service
            app: release-name-simple-daemonfulset-service
            version: latest
            app.kubernetes.io/version: "1.16.0"
            app.kubernetes.io/managed-by: Helm
    template:
        metadata:
            labels:
                helm.sh/chart: simple-daemonfulset-service-0.1.0
                app.kubernetes.io/name: simple-daemonfulset-service
                app.kubernetes.io/component: simple-daemonfulset-service
                app.kubernetes.io/instance: release-name
                deploymentconfig: release-name-simple-daemonfulset-service
                app: release-name-simple-daemonfulset-service
                version: latest
                app.kubernetes.io/version: "1.16.0"
                app.kubernetes.io/managed-by: Helm
        spec:
            containers:
                - name: release-name-simple-daemonfulset-service
                  image: image-registry.openshift-image-registry.svc:5000/labs-dev/simple-daemonset-set-service:latest
                  resources:
                    limits:
                      cpu: 500m
                      memory: 1024Mi
                    requests:
                      cpu: 100m
                      memory: 512Mi

``` 

## How To Install

first try a dry run
```
$ helm install deployment --dry-run --generate-name
``` 

if it is successful then can install it directly
```
$ helm upgrade --install stateful-set-app basic_helm/simple-stateful-set-service/deployment
```