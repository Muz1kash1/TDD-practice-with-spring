FROM openjdk:17
COPY target/bootest-*.jar /usr/app/boottest.jar
WORKDIR /usr/app/
ENTRYPOINT ["java","-jar","boottest.jar"]