## Step 1

Let's try to build and deploy the simple microservice we have created onto the openshift cluster **without requiring docker on developer's machine**

### 1. Build the app:

```
mvn  -DskipTests clean package
cp target/*.jar dist/app.jar
```
``dist`` directory contains the build jar executable


#### 2. Create Openshift Project

In order to deploy our app, we need to create a project/namespace on the ocp cluster. Let's call the project namespace as ```dev-apps`` 

```
oc login -server=https://api.<cluster-domain>:6443
oc new-project dev-apps
```

#### Sample output

```
Already on project "dev-apps" on server "".

You can add applications to this project with the 'new-app' command. For example, try:

    oc new-app rails-postgresql-example

to build a new example application in Ruby. Or use kubectl to deploy a simple Kubernetes application:

    kubectl create deployment hello-node --image=k8s.gcr.io/serve_hostname
```
 <i> Note:

User needs to have create project permissions on the cluster
</i>

#### 3. Create Openshift Build specification

Since we don't have a docker image in a container registry, we have to build the image using OCP's build system. This can be achieved either by using the ``BuildConfig`` CRD or the ``oc new-build`` command.

```
% cd basic/simple-microservice
simple-microservice % cat Dockerfile | oc new-build -D - --name springbootapp
```
Above step create as a new build config and dependent resources like imagestreams(to store the base & built images) on the name space. Output will look like the one below:

```
-> Found container image a6de6da (9 days old) from docker.io for "docker.io/openjdk:11"

    * An image stream tag will be created as "openjdk:11" that will track the source image
    * A Docker build using a predefined Dockerfile will be created
      * The resulting image will be pushed to image stream tag "springbootapp:latest"
      * Every time "openjdk:11" changes a new build will be triggered

--> Creating resources with label build=springbootapp ...
    imagestream.image.openshift.io "openjdk" created
    imagestream.image.openshift.io "springbootapp" created
    buildconfig.build.openshift.io "springbootapp" created
--> Success
```
#### 2.1 Resource limits 

Lets set the resource limits for the builds. 


```
oc patch bc springbootapp -p '{"spec":{"resources": {"limits": {"cpu": "1500m", "memory": "900Mi"}, "requests": {"cpu": "800m", "memory": "500Mi"}}}}'
```
###### Sample output
```
buildconfig.build.openshift.io/springbootapp patched
```

#### 3. Build the container image based on the local jar file

```
simple-microservice % oc start-build springbootapp --from-dir ./dist --follow  
```

This step will **upload the local jar** to OCP cluster and build the container image based on the steps defined the ``Dockerfile`` we have provided in Step #3. 

#### Sample output
```
Uploading directory "dist" as binary input for the build ...
..................
Uploading finished
build.build.openshift.io/springbootapp-2 started
Receiving source from STDIN as archive ...
Replaced Dockerfile FROM image docker.io/openjdk:11
time="2022-03-28T23:47:52Z" level=info msg="Not using native diff for overlay, this may cause degraded performance for building images: kernel has CONFIG_OVERLAY_FS_REDIRECT_DIR enabled"
I0328 23:47:52.787849       1 defaults.go:102] Defaulting to storage driver "overlay" with options [mountopt=metacopy=on].
Caching blobs under "/var/cache/blobs".

Pulling image docker.io/openjdk@sha256:4449cc0f4498abae89db038632e21301f7c3efe7f547cff10f4c6ff8dad5cba7 ...
Trying to pull docker.io/library/openjdk@sha256:4449cc0f4498abae89db038632e21301f7c3efe7f547cff10f4c6ff8dad5cba7...
Getting image source signatures
Copying blob sha256:3585e93a38a5f5bdc15c5bffa0884ea65534684d451eb5c8f042b40ac2cb1a5a
Copying blob sha256:5492f66d270062ddb73f28649d80eef162f2c9376d53973a3557158390af4f30
Copying blob sha256:540ff8c0841d610e4cc2ad3b9ed4c6edcad4f5be2add8765f416515fbc2be2a8
Copying blob sha256:21fd8f6c2501f1cdc9feb11cba7ec67178be0baee18065067ecd65548f5aa7b0
Copying blob sha256:a0bf850a0df065fb202ebf8a3527699dc18322469c34733a6cb7f412a7aaefa6
Copying blob sha256:d751dc38ae511bbc21c148bffa7e863057cbc7b4a8ff5155f2eca7e8d03740c6
Copying blob sha256:fca7cf2735d168e192f5c2c0aa0e6b4b1c37aa3d3254e108f975bca5b4e01a34
Copying config sha256:a6de6da8040c6ec4bbebf63efbdefd0ffd87d1ec416c57b3572dc0c6b916b903
Writing manifest to image destination
Storing signatures
Adding transient rw bind mount for /run/secrets/rhsm
STEP 1/5: FROM docker.io/openjdk@sha256:4449cc0f4498abae89db038632e21301f7c3efe7f547cff10f4c6ff8dad5cba7
STEP 2/5: COPY app.jar /
--> 931f75b883c
STEP 3/5: ENTRYPOINT ["java","-jar","/app.jar"]
--> ff43e533171
STEP 4/5: ENV "OPENSHIFT_BUILD_NAME"="springbootapp-2" "OPENSHIFT_BUILD_NAMESPACE"="dev-apps"
--> cd6dbe5d362
STEP 5/5: LABEL "io.openshift.build.name"="springbootapp-2" "io.openshift.build.namespace"="dev-apps"
COMMIT temp.builder.openshift.io/dev-apps/springbootapp-2:d6e66687
--> 8da88b4f2bc
Successfully tagged temp.builder.openshift.io/dev-apps/springbootapp-2:d6e66687
8da88b4f2bcbfe389b4d523143bb3368c361253f4e33038e73029348ebb101f4

Pushing image image-registry.openshift-image-registry.svc:5000/dev-apps/springbootapp:latest ...
Getting image source signatures
Copying blob sha256:a0bf850a0df065fb202ebf8a3527699dc18322469c34733a6cb7f412a7aaefa6
Copying blob sha256:d751dc38ae511bbc21c148bffa7e863057cbc7b4a8ff5155f2eca7e8d03740c6
Copying blob sha256:3585e93a38a5f5bdc15c5bffa0884ea65534684d451eb5c8f042b40ac2cb1a5a
Copying blob sha256:5492f66d270062ddb73f28649d80eef162f2c9376d53973a3557158390af4f30
Copying blob sha256:540ff8c0841d610e4cc2ad3b9ed4c6edcad4f5be2add8765f416515fbc2be2a8
Copying blob sha256:21fd8f6c2501f1cdc9feb11cba7ec67178be0baee18065067ecd65548f5aa7b0
Copying blob sha256:f9b914840a2d3ac37d05f4a1a93a5817e651c66405b553a7f324d7f97430b98d
Copying blob sha256:fca7cf2735d168e192f5c2c0aa0e6b4b1c37aa3d3254e108f975bca5b4e01a34
Copying config sha256:8da88b4f2bcbfe389b4d523143bb3368c361253f4e33038e73029348ebb101f4
Writing manifest to image destination
Storing signatures
Successfully pushed image-registry.openshift-image-registry.svc:5000/dev-apps/springbootapp@sha256:7ab0a63735378944359b46e2a31a58570d598e00663d858e2c7ef5e937c519b7
Push successful
```

Hurray, we have created the application container image without the need for having docker/podman locally. Its time to deploy the image as an application

#### 5. Deploy the image as app

To create an application based on existing image, we will the simplest method i.e. using ``oc new-app`` command 
```
oc new-app --name springbootapp springbootapp -l app=springbootapp
```
**Where:**

  <i> -l options attaches a label (/ a tag) to the application</i>

##### Sample output
```
--> Found image 8da88b4 (8 minutes old) in image stream "dev-apps/springbootapp" under tag "latest" for "springbootapp"


--> Creating resources with label app=springbootapp ...
    deployment.apps "springbootapp" created
--> Success
    Run 'oc status' to view your app.
```

##### Let's verify the deployment:

```
% oc get pods -l deployment=springbootapp

NAME                             READY   STATUS    RESTARTS   AGE
springbootapp-5888bf98f6-rhrrr   1/1     Running   0          114s
```

We can specify additional labels to the deployment even after we create them. For example:

```
% oc label deployment springbootapp deployment=springbootapp
deployment.apps/springbootapp labeled
```

**We need to define labels in order to select/search our deployments to perform certain operation like attaching a service etc.,.**


#### 5. Create additional applications/deployments

Using the container image we had created earlier, it is possible to deploy any number of applications under different name.

For example:

``` 
oc new-app  --name springbootapp-2 springbootapp 
```

Above command will create another ``deployment`` on openshift with the name springbootapp-2 with the image we have specified.