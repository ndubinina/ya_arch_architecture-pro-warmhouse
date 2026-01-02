FROM gradle:jdk21-alpine AS builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradle/ ./
COPY gradle/ gradle/

RUN gradle build --write-verification-metadata sha256 || true

COPY . .

RUN gradle clean build

FROM alpine/java:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
