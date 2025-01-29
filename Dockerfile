FROM openjdk:17
EXPOSE 8080
WORKDIR /bankmicroservice/application
COPY /target/BankMicroservice-1.0.0-SNAPSHOT.jar /bankmicroservice/application
ENTRYPOINT ["java","-jar","BankMicroservice-1.0.0-SNAPSHOT.jar"]