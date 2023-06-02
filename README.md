# AUI-Lab
Labs from AUI (Internet Services Architectures)

Labs are incremental meaning every task is dependant on the previous one.

Description of each task:
* Lab1 - Spring Framework & Spring Boot
* Lab2 - Spring Data & Spring MVC
* Lab3 - Microservices
* Lab4 - Consuming Microservices
* Lab5 - Basic deployment setup

### Lab5 deployment
To avoid errors `book` and `library` microservices need to use JAVA 18.

1. In `book`, `library` and `rest-gateway` microservices create .jar files by running Maven `package` lifecycle.
2. Make sure Docker is running.
3. Inside `Lab5/` directory open terminal and run this command:
```
docker-compose up --build
```
4. On `localhost:80` simple GUI will appear giving option to add, delete, edit books and libraries.
