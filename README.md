# job-webservices
#### This is web service for get job positions requirement

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [MySQL](https://www.mysql.com/)

## Create Database
```shell
create schema db_recruitment;
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.dansmultipro.test.jobwebservices` class from your IDE.

Alternatively, you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

The table will be generated. In this service use [Flyway](https://flywaydb.org/) for the migration tool.

## Get Information All Resources

If you want to access resources about these services you can see them at the link [Local Environment](http://localhost:8080/swagger-ui.html). For Example:

![Screenshot 2023-07-20 070953](https://github.com/fahmikudo/job-webservices/assets/20161826/41bea5fd-2c52-4da2-bd25-8b19a3f7e5a6)
