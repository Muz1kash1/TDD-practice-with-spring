FROM openjdk:17
COPY target/bootest-*.jar /usr/app/boottest.jar
WORKDIR /usr/app/
EXPOSE 8080:8080
ENTRYPOINT ["java","-jar","boottest.jar"]