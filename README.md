# Logging-filter [![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/wandi)

This project is a simple Spring Boot application that demonstrates how to create a logging filter to log only specific requests segments in DEBUG mode  

1. Change the application log level to debug
```shell
curl -i -X POST -H 'Content-Type: application/json'  'http://localhost:8080/actuator/loggers/com.example.logging_filter' \
     -d '{
           "configuredLevel": "DEBUG"
         }'
```
1.1 Confirm the log level change
```shell
curl -i -X GET 'http://localhost:8080/actuator/loggers/com.example.logging_filter'
```

2. Sample request with correlation id 12345 (see **application.properties** file, prints DEBUG message)
```shell
curl -i -H 'X-Correlation-Id:12345'  -X GET 'http://localhost:8080/hello?name=Wanderlei'
```

3. Sample request with correlation id 11111 (do not print debug message)
```shell
curl -i -H 'X-Correlation-Id:11111'  -X GET 'http://localhost:8080/hello?name=Wanderlei'
```

Log output:
- only the request with correlation id 12345 will be logged in DEBUG mode (second log line in the example output)
```
2024-06-17 18:13:50.694  INFO 66816 --- [nio-8080-exec-6] c.example.logging_filter.FilterConfig    [12345] : Intercept coming request and set MDC context information
2024-06-17 18:13:50.694 DEBUG 66816 --- [nio-8080-exec-6] c.e.logging_filter.HelloController       [12345] : Saying hello to Wanderlei
2024-06-17 18:14:08.736  INFO 66816 --- [nio-8080-exec-7] c.example.logging_filter.FilterConfig    [12346] : Intercept coming request and set MDC context information
```
