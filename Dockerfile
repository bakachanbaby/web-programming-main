FROM maven:3.6-openjdk-11

COPY . .

RUN mvn clean package

EXPOSE 8080

ENTRYPOINT java -jar target/*.jar
