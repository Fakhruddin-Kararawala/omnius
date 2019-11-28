FROM openjdk:10-jre-slim
COPY ./target/tms-0.0.1-SNAPSHOT.jar /usr/omnius/
WORKDIR /usr/omnius
EXPOSE 9000
CMD ["java", "-jar", "tms-0.0.1-SNAPSHOT.jar"]
