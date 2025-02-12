# FROM registry.access.redhat.com/ubi8/openjdk-17:1.13
# COPY target/quarkus-app/quarkus-run.jar /app/quarkus-api.jar
# CMD ["java", "-jar", "/app/quarkus-api.jar"]
# FROM openjdk:17-jdk-slim

# COPY target/quarkus-app/lib/ /app/lib/
# COPY target/quarkus-app/quarkus/ /app/quarkus/
# COPY target/quarkus-app/app/ /app/app/

# ENTRYPOINT ["java", "-jar", "/app/app/quarkus-run.jar"]
