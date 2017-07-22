FROM java:8
WORKDIR /data
RUN mkdir config
ADD student-registration-notification-service-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]