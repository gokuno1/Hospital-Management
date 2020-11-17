FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD target/UserService:0.0.1-SNAPSHOT.jar UserService:0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c", "java -jar /user-service.jar"]