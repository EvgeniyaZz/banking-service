# Service for banking operations

A Java backend project with registration/authorization and role-based access rights, authentication via JWT.  
A service API has been implemented in which you can create a new user. Authorized users can manage their profile and transfer money to another registered user. There is also filtering and sorting of users by date of birth, phone, email and full name.

## Tools

- JDK 17
- Spring Boot 3.2.5
- Lombok
- H2
- Caffeine Cache
- Swagger/OpenAPI 3.0

## Run

`mvn spring-boot:run` in root directory.

-----------------------------------------------------
[REST API documentation](http://localhost:8080/swagger-ui/index.html)  
Credentials:

```
Admin: Admin / admin
User:  User / password
User2: User2 / user2
```
