### Build stage ###
FROM gradle:8.3 AS build

WORKDIR /app
COPY . .

RUN gradle build


### Run stage ###
FROM amazoncorretto:20-alpine3.15

ENV APP_NAME WhyTalk-Message

WORKDIR /app
COPY --from=build /app/build/libs/$APP_NAME.jar ./app.jar

CMD ["java", "-jar", "app.jar"]

