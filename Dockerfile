### Build stage ###
FROM gradle:8.3 AS build

WORKDIR /app
COPY /src ./src
COPY settings.gradle .
COPY build.gradle .
COPY . .

RUN gradle build -x test -x spotlessCheck


### Run stage ###
FROM amazoncorretto:20-alpine3.15

ENV APP_NAME WhyTalk-Message-0.0.1-SNAPSHOT

WORKDIR /app
COPY --from=build /app/build/libs/$APP_NAME.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]

EXPOSE 8080
EXPOSE 7001

