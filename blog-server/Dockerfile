FROM openjdk:8-jdk-alpine
ADD build/libs/*.jar blog.jar
ENTRYPOINT ["java","-jar","blog.jar"]