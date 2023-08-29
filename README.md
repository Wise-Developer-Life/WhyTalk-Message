# WhyTalk-Message
CRUD message service,and do some cache operation

### Create Dev APP 
```shell
docker-compose --file docker-compose.dev.yaml up -d
```
1. connect to http://localhost:8080/swagger-ui.html
2. check openAPI document

### Spotless Check

1. Check coding style
```shell
./gradlew spotlessCheck
```
2. Apply coding style
```shell
./gradlew spotlessApply
```
### Generate OpenAPI Document
```shell
./gradlew clean generateOpenApiDocs
```
