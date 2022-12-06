## My Household

### Description
My Household application provides access to personal data. It is also possible to generate reports containing the amount of parents to the amount of children ratio.

### Implementation
The application is implemented following the Domain Driven Design as a showcase how a project can be structured in a way to be easily maintainable and extendable with layer isolation. 
The requirements of the task could have been covered with a simpler implementation, but the project would not have been modified easily if the requirements change.  

The project consists of the following layers:
* application - provides an API for all functionality provided by the application
* domain - contains the core domain logic
* infrastructure - contains interfaces to the outer world. If the data source is changed, it will not affect the other layers.

### Setting up the database
Download the following
[MySQL dump](https://drive.google.com/file/d/1toUEFeBSV6Kp2pfRlCRxhegOHxaaaQla/view?usp=sharing)
and place it in the directory where the application is checked out.
Go to the root of the application and execute the following command:
```
docker compose -f ./docker/docker-compose.yaml up
```

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
### Documentation
After starting the application the documentation can be found here:

[http://localhost:8080/swagger-ui.html]()

### Improvements
The following improvements can be done:
* more tests - unit, integration and load testing
* the existing load tests to be independent of the database e.g to use a MySQL testcontainer
