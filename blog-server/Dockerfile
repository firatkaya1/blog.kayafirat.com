FROM gradle:7.3.0-jdk8
COPY --chown=gradle:gradle . /home/gradle/src
RUN ls -l
WORKDIR /home/gradle/src
RUN gradle bootJar

FROM openjdk:8-jdk-alpine
RUN mkdir -p /app/
ADD build/libs/blog.jar /app/blog.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/blog.jar"]
