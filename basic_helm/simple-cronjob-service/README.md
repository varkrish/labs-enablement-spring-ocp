# Sample Helm Chart for CronJob
 
## How To Debug 

 ```
$ helm template --debug deployment
```

the result would be a `yaml` display,
```yaml
# Source: simple-cronjob-service/templates/renderall.yaml
apiVersion: batch/v1beta1
kind: CronJob
metadata:
    name: release-name-simple-cronjob-service
    labels:
        helm.sh/chart: simple-cronjob-service-0.1.0
        app.kubernetes.io/name: simple-cronjob-service
        app.kubernetes.io/component: simple-cronjob-service
        app.kubernetes.io/instance: release-name
        deploymentconfig: release-name-simple-cronjob-service
        app.kubernetes.io/version: "1.16.0"
        app.kubernetes.io/managed-by: Helm
spec:
    schedule: 0 * * * *
    concurrencyPolicy:  Forbid
    suspend: false
    jobTemplate:
        metadata:
            creationTimestamp: null
            labels:
                helm.sh/chart: simple-cronjob-service-0.1.0
                app.kubernetes.io/name: simple-cronjob-service
                app.kubernetes.io/component: simple-cronjob-service
                app.kubernetes.io/instance: release-name
                deploymentconfig: release-name-simple-cronjob-service
                app.kubernetes.io/version: "1.16.0"
                app.kubernetes.io/managed-by: Helm
        spec:
            backoffLimit: 0
            parallelism: 1
            template:
                spec:
                    containers:
                        - env:
                          name: simple-cronjob-service
                          image: image-registry.openshift-image-registry.svc:5000/labs-dev/simple-job-service:latest    
                          resources:
                            limits:
                              cpu: 500m
                              memory: 1024Mi
                            requests:
                              cpu: 100m
                              memory: 512Mi

                          terminationMessagePath: /dev/termination-log
                          terminationMessagePolicy: File
                          imagePullPolicy: Always
                    restartPolicy: Never
                    terminationGracePeriodSeconds: 30
                    dnsPolicy: ClusterFirst
                    securityContext: {}
                    schedulerName: default-scheduler
``` 

## How To Install

first try a dry run
```
$ helm install deployment --dry-run --generate-name
``` 

if it is successful then can install it directly
```
$ helm upgrade --install simple-cronjob-service basic_helm/simple-cronjob-service/deployment
```