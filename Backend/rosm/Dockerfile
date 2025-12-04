# Stage 1: build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copy root .env
COPY .env .env

# Copy backend sources
COPY Backend/rosm/pom.xml .
# Create a dummy source folder so mvn dependency:go-offline won't fail
RUN mkdir -p src/main/java

# Pre-download dependencies (cached unless pom.xml changes)
RUN mvn -B dependency:go-offline
COPY Backend/rosm/src ./src
RUN mvn -B -DskipTests package

# Stage 2: run the JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app
# copy built jar (assumes single jar in target/)
COPY --from=build /app/target/*.jar app.jar
# Copy .env for runtime (so Dotenv can find it)
COPY .env .env
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
