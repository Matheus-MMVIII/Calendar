FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

COPY lib ./lib
COPY src ./src

RUN mkdir -p out

RUN javac -cp "lib/*" \
    -d out \
    $(find src/main/java -name "*.java")


FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/out ./out
COPY --from=build /app/lib ./lib

EXPOSE 8081

ENTRYPOINT ["java", "-cp", "out:lib/*", "com.calendar.App"]