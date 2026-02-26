# -------- BUILD STAGE --------
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
# əvvəlcə dependency cache üçün pom-u kopyala
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline
# sonra source-u kopyala və build elə
COPY src ./src
RUN mvn -q -DskipTests clean package
# -------- RUN STAGE --------
FROM eclipse-temurin:17-jre
WORKDIR /app
# jar-ı build stage-dən götürürük
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]