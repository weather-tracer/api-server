FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY . /app/
RUN ./mvnw clean package

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","app.jar"]