# golden-oven-671



## REST API for a Bus Reservation System Portal

## Description


- This is a REST API for a Bus reservation system portal. This API performs all the fundamental CRUD operations of any Bus reservation platform with user and admin validation at every step.
- Build by a team of 5 members in 5 days.


 
## Techstacks

- Java
- Spring Framework
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL



## Modules

- Admin Module
- User Module
- Login Module
- Route Module
- Bus Module
- Reservation Module
- Feedback Module

## ER Diagram
Following ER diagram indicates the database tables and thier interaction which we are using.
![ER Diagram](https://user-images.githubusercontent.com/101388764/201461961-ebc6fe01-78ad-4668-b14c-46ce2993f972.png)



## Features

* User and Admin authentication & validation with session uuid having.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid session token can add/update/delete route or customer from main database
    * Admin can access the details of different users, buses and routes.
* User Features:
    * Registering themselves with application, and logging in to get the valid session token.
    * Viewing list of available bus and booking a reservatioon.
    * Only logged in users can update his/her reservation, profile updation and other features.




## Contributors
https://github.com/Shaili-Srivastava

https://github.com/SamsonSeemal

https://github.com/Saurabhpatel0894

https://github.com/abhinav110695

https://github.com/owaisshassan

## Installation & Run

- Before running the API server, you should update the database config inside the [application.properties](https://github.com/owaisshassan/golden-oven-671/blob/main/src/main/resources/application.properties) file.
- Update the port number, username and password as per your local database config.

```
    server.port=8888

    spring.datasource.url=jdbc:mysql://localhost:8888/brpDB;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```


## API Module Endpoints

### User Module



* `POST /login` : Logging in user with valid mobile number & password
* `POST /logout` : Logging out user based on session token
* `POST /brsp/user` : To register user himself
* `POST /feedback/userId/busId` : To give feedback by user based on userId and busId
* `GET /bus/busId` : Getting the details of the bus by its id
* `GET /buses` : Getting All the buses
* `GET /busByType/busType` : Getting details of bus by its type
* `GET /reservation/userId` :  To do reservation by user by his/her id
* `GET /reservation/reservationId` : Getting the reservation details by reservation id
* `PUT /reservation/userId` : Update reservation details by user id
* `PUT /brsp/user` : Update user details by himself
* `PUT /feedback` : To update feedback given by user.
* `DELETE /reservation/userId/reservationId` : Cancel reservation by reservationId, userId


### Swagger UI

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Swagger.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Admin Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Admin_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---


### User Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/User_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---


### Login Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Login_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Route Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Route_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Bus Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Bus_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---

### Reservation Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Reservation_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---


### Feedback Controller

---

<img src="https://github.com/owaisshassan/golden-oven-671/blob/main/Images/Feedback_Controller.jpeg" style="max-width: 100%; display: inline-block;" data-target="animated-image.originalImage">

---  


