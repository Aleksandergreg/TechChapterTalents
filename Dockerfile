# ---- Build Stage ----
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copy only the pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Now copy in the actual source
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM openjdk:21-jdk-slim
WORKDIR /app

ARG JAR_FILE=TalentsTechChapter-0.0.1-SNAPSHOT.jar

# Copy the JAR from the build stage to /app/app.jar
COPY --from=build /app/target/${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
