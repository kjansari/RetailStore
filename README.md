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
* {
	"itemList":[{"id":1,"quantity":2},{"id":2,"quantity":50},{"id":7,"quantity":2},{"id":3,"quantity":100}],
    "userid":1
    }
	
### Sample Response :
* HttpStatus : 200 OK
* {
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

### Sample Request for Validation check:
* {
	"itemList":[],
    "userid":1
    }
    
### Sample Response for Validation check:
* {
    "message": "Validation failed for argument [0] in public com.example.demo.dto.BillDTO com.example.demo.controller.RetailStoreAPIController.calculateBill(com.example.demo.dto.BillDTO): [Field error in object 'billDTO' on field 'itemList': rejected value [[]]; codes [NotEmpty.billDTO.itemList,NotEmpty.itemList,NotEmpty.java.util.List,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [billDTO.itemList,itemList]; arguments []; default message [itemList]]; default message [List should not empty]] "
}

### Sample Request for Validation check for userId:
* {
	"itemList":[{"id":1,"quantity":2},{"id":2,"quantity":50},{"id":7,"quantity":2},{"id":3,"quantity":100}],
    "userid":null
    }

### Sample Response for Validation check for userId:
* {
    "message": "Validation failed for argument [0] in public com.example.demo.dto.BillDTO com.example.demo.controller.RetailStoreAPIController.calculateBill(com.example.demo.dto.BillDTO): [Field error in object 'billDTO' on field 'userid': rejected value [null]; codes [NotNull.billDTO.userid,NotNull.userid,NotNull.java.lang.Integer,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [billDTO.userid,userid]; arguments []; default message [userid]]; default message [userid is mandatory]] "
}



	
	
