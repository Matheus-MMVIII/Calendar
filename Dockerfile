FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

COPY src ./src

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir -p lib out && \
    curl -L \
    https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.13/postgresql-42.7.13.jar \
    -o lib/postgresql.jar

RUN javac -cp "lib/*" \
    -d out \
    $(find src/main/java -name "*.java")


FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/out ./out
COPY --from=build /app/lib ./lib

EXPOSE 8081

ENTRYPOINT ["java", "-cp", "out:lib/*", "com.calendar.App"]