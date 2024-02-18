FROM openjdk:17
ARG JAR_FILE=./build/libs/tyniUrl-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} url.jar
ENTRYPOINT ["java","-jar","/url.jar"]