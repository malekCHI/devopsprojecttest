FROM openjdk:1.8
EXPOSE 8700
ADD target/DevOps_Project-2.1.jar DevOps_Project.jar
ENTRYPOINT ["java","-jar","DevOps_Project.jar"]