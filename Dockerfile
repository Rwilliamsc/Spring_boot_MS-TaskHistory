FROM eclipse-temurin:21-jdk
LABEL authors="raphael.costa/Mariana.Sukevicz"

VOLUME /tmp
EXPOSE 8084

ADD target/MS-TaskHistory-0.0.1-SNAPSHOT.jar TaskHistoryService.jar
ENTRYPOINT ["java","-jar","/TaskHistoryService.jar"]