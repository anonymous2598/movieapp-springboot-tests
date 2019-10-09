This branch contains:

1)The CRUD endpoints of MovieService Applications

2)H2 Console to view in-memory data

3)Using @Query for getMovieByName method

4)Using Swagger2 to generate API Documentation

5)Exception Handling For MovieDoesNotExist and MovieAlreadyExists exceptions and propagation

6)Running logic on Spring Startup Using ApplicationListener and CommandLineRunner

7)GlobalExceptionHandler Class

8)Removed Hard Coded values using @Value, @PropertySource, Environment,@ConfigurationProperties

9)Added Lombok plugin and annotations

10)Added DummyServiceImpl and using @Primary and @Qualifier annotations to choose 

11)Using @Profile annotation to choose the service implementations

12)Replacing h2 with mySQL

13)2 properties files. application-dev.properties for h2 and application-prod.properties for mySQL

14)Added Spring developer tools

15)Added Sping boot actuator to collect metrics for service end points
