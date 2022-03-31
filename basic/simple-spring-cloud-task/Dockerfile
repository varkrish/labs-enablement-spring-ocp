FROM registry.access.redhat.com/ubi8/ubi-minimal:8.5

LABEL BASE_IMAGE="registry.access.redhat.com/ubi8/ubi-minimal:8.5"  JAVA_VERSION="11"  WORK_DIRECTORY="/work/"

ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' TZ='Asia/Jakarta'

RUN microdnf install --nodocs java-11-openjdk-headless tzdata    && \
        ln -sf /usr/share/zoneinfo/$TZ /etc/localtime  && \
        microdnf clean all

WORKDIR /work/
COPY *.jar /work/app.jar

CMD ["java", "-jar", "app.jar"]