# Doctor Label Application

This is a web application for managing doctor labels.

### Getting Started

#### Prerequisites

* Java 11
* Maven


### Running the Application

To run the application, navigate to the project directory in your terminal and run the following command:

mvn spring-boot:run

This will start the application on localhost:8088.

### Creating a Doctor Label

To create a new doctor label, you can use an HTTP POST request to the /doctor-labels endpoint. Here is an example of how to create a doctor label using the Postman app:

### Usage

The API provides the following endpoints:

* POST /doctor-labels: Create a new doctor label
* GET /doctor-labels: Get all doctor labels
* GET /doctor-labels/{id}: Get a doctor label by ID
* GET /doctor-labels/label-code/{label}: Get all doctor labels with a specific label
* PUT /doctor-labels/{id}: Update a doctor label by ID
* DELETE /doctor-labels/{id}: Delete a doctor label by ID
* DELETE /doctor-labels/label/{label}: Delete all doctor labels with a specific label

### Request Examples

##### Create Doctor Label

Create a new doctor label.

``` http
POST /doctor-labels
Content-Type: application/json

{
  "label": "Label1",
  "doctorId": 1,
  "description": "Description"
}
```

##### Find All Doctor Labels

Retrieve a list of all doctor labels.

``` http
GET /doctor-labels
```

##### Find Doctor Label by ID

Retrieve a doctor label by its ID.

``` http
GET /doctor-labels/{id}
```

##### Find Doctor Labels by Label Code

Retrieve a list of doctor labels by label code.
``` http
GET /doctor-labels/label-code/{label}
```

##### Update Doctor Label

Update an existing doctor label.

``` http
PUT /doctor-labels/{id}
Content-Type: application/json

{
  "label": "Label2",
  "doctorId": 2,
  "description": "Updated Description"
}
```

##### Delete Doctor Label by ID

Delete a doctor label by its ID.

``` http
DELETE /doctor-labels/{id}
```

##### Delete Label from Doctor Cases

Delete a label from all doctor cases.

``` http
DELETE /doctor-labels/label/{label}
```



### Built With

* Spring Boot
* Spring Data JPA
* Lombok