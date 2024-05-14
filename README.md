# spring-boot-jsp-demo

This spring-boot application describes how to use JSP as a view technology when deploying as a standalone self-executing WAR file. The important thing to note is that the application is packaged as a WAR file but can still be run with `java -jar ...`

# Requirements

- JDK 21 or higher

# Compiling/Packaging

```
./mvnw clean package
```

# Running

```
java -jar target/jspdemo-1.0.0.war
```

