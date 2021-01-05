FROM openjdk:8-jdk-alpine
EXPOSE 9092
ADD target/EurekaNamingServer-0.0.1-SNAPSHOT.jar EurekaNamingServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c", "java -jar /EurekaNamingServer-0.0.1-SNAPSHOT.jar"]