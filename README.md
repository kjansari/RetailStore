# RetailStore

### Requirements
For building and running the application you need:

* JDK 1.8
* Maven 3

### Running the application locally:
* There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.example.demo.RetailStoreOfferApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

* mvn spring-boot:run

### Request Detail:
* Sample Request URL : localhost:8080/bills/calculate
* Request Type : Post
* Swagger URL : http://localhost:8080/swagger-ui.html

### Sample Request :

```json
 {
	"itemList":[{"id":1,"quantity":2},{"id":2,"quantity":50},{"id":7,"quantity":2},{"id":3,"quantity":100}],
    "userid":1
    }
```
	
### Sample Response :
* HttpStatus : 200 OK

```json
 {
    "billAmount": 2932,
    "discountedAmount": 2787,
    "itemList": [
        {
            "id": 1,
            "quantity": 2
        },
        {
            "id": 2,
            "quantity": 50
        },
        {
            "id": 7,
            "quantity": 2
        },
        {
            "id": 3,
            "quantity": 100
        }
    ],
    "userid": 1
}
```

### Sample Request for Validation check:

```json
{
	"itemList":[],
    "userid":2
    }
	
```
    
### Sample Response for Validation check:
```json
 {
    "message": "List should not empty"
}
```

### Sample Request for Validation check for userId:
```json
 {
	"itemList":[{"id":1,"quantity":2},{"id":2,"quantity":50},{"id":7,"quantity":2},{"id":3,"quantity":100}],
    "userid":null
    }
	
```

### Sample Response for Validation check for userId:
```json
 {
    "message": "userid is mandatory"
}
```



	
	
