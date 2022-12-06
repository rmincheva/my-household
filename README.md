## My Household

### Description
My Household provides RESTful resources for accessing information about a household with persons, children, meals, etc.
It is also possible to get the amount of parents to the amount of children ratio.

### Implementation
The application is implemented following the Domain Driven Design as a showcase how a project can be structured in a way to be easily maintainable and extendable with layer isolation. 
The requirements of the task could have been covered with a simpler implementation, but the project would not have been modified easily if the requirements change.  

The project consists of the following layers:
* application - provides an API for all functionality provided by the application
* domain - contains the core domain logic
* infrastructure - contains interfaces to the outer world. If the data source is changed, it will not affect the other layers.

### Setting up the database
Go to the following directory:

```
/{projectDirectory}/docker
```
Download the following
[MySQL dump](https://drive.google.com/file/d/1toUEFeBSV6Kp2pfRlCRxhegOHxaaaQla/view?usp=sharing)
and place it there. Then execute the following command:
```
docker-compose up
```
Have in mind that importing the SQL dump might take a while.

### Running the application with Maven
You can start the application by running:
```
./mvnw spring-boot:run
```
### Running the application with Docker
First create a docker image with the following command:
```
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=wagawin/my-household
```
Then start the application by running:
```
docker run -p 8080:8080 wagawin/my-household
```
### Running the tests
Have in mind that it is required to have a configured and running database for the load tests. You can run the tests with the following command:
```
./mvnw clean verify
```

### Documentation
After starting the application the documentation can be found here:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Improvements
The following improvements can be done:
* more tests - unit, integration and load testing
* an isolated MySQL server used only in the load tests e.g. with testcontainers.
