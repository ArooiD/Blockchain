FROM openjdk:17-jdk-slim AS runtime

WORKDIR /opt

COPY target/node.jar /opt/node.jar

ENTRYPOINT ["java", "-jar", "/opt/node.jar"]