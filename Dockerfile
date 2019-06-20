FROM openjdk:8-jdk as java
USER root
COPY . .
RUN ./gradlew --no-daemon build

FROM openjdk:8-jre-alpine
WORKDIR /data
RUN mkdir config
COPY --from=java /home/gradle/build/libs/student-registration-notification-service-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]
