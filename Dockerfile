FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/testApp-1.0-SNAPSHOT.jar test-app.jar
ENTRYPOINT ["java","-jar","test-app.jar"]