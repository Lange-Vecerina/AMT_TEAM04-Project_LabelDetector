FROM openjdk:17
EXPOSE 8081
ADD target/label-detector.jar label-detector.jar
ENTRYPOINT ["java","-jar","/label-detector.jar"]