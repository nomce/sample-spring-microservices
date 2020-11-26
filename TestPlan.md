
# Introduction  

This test plan is a description of the test activity for the Sample Spring Microservices POC project.   
It identifies the product requirements, assumptions, dependencies and risks.   
This test plan will be updated in the earliest possible time to reflect the actual situation of the test implementation.  
  
# Project  
  
The Sample Spring Microservices allows the user to run a customer and an account manager service. 
It does so through two main endpoints: ``account-service:2222`` and ``customer-service:3333``. 
The services contain multiple accounts by default and have the option to retrieve, delete or add new ones.  

# Test structure  
  
Based on the requirements, the test plan covers the functional and the performance aspect of the project.   
For this test plan, the SUT is black box with described requirements in the section below.  
For this POC, because the tests have to validate a REST service API, there are multiple tools to choose from.   
Each of them has its pros and cons.   
  
- Rest Assured with JUnit (Maven)  
  - [Pros] It supports a Given/When/Then test notation, human readable  
  - [Pros] Easy integration with TestNG/Junit  
  - [Pros] Support Data-Driven framework   
  - [Pros] jUnit/xUnit Reports   
  - [Cons] Knowledge of Java  
  - [Cons] No support for SOAP APIs  
- Cucumber with Apache HttpClient (Maven)  
  - [Pros] Uses Given/When/Then test notation, human readable  
  - [Pros] Support Data-Driven framework  
  - [Pros] Stable and proven HTTP Library  
  - [Cons] Knowledge of Java  
  - [Cons] Forces BDD approach  
- Robot Framework and RequestsLibrary (Python)  
  - [Pros] Uses simple language syntax  
  - [Pros] Detailed HTML report  
  - [Cons] Different platform compared to the project  
  
# Requirements  
  
## Common requirements  

1. [Performance] Response time under 1 second for every request.  
  
## account-service  
  
1. ``GET /``: shall return a list of elements in JSON format with the following structure   
``{id: integer, customerId: integer, number: string}``.  
2. ``GET /{id}``: if it exists, it shall return an element with the following structure   
``{id: integer, customerId: integer, number: string}``. Otherwise, it shall return a 404 HTTP error with the following  
body: ``{"timestamp": number, "status": number, "error": string, "exception": string, "message": string, "path": string}``  
3. ``GET /number/{number}``: if it exists, it shall return an element with the following structure   
``{id: integer, customerId: integer, number: string}``. Otherwise, it shall return a 404 HTTP error with the following  
body: ``{"timestamp": number, "status": number, "error": string, "exception": string, "message": string, "path": string}``.  
4. ``GET /customer/{number}``: if it exists, it shall return all matching elements with the following structure   
``{id: integer, customerId: integer, number: string}``. Otherwise, it shall return an empty JSON list.  
5. ``POST /`` with BODY: shall add the BODY element into the list of elements if it has the correct structure and  
the "id" is not in use. Otherwise, it shall return an 200 HTTP error with empty body.  
6. ``DELETE /{id}``: shall remove the existing element with id in parameter from the list of elements.  
If the element does not exist in the first place, it will return "false".   
If the parameter is text, it shall return 400 HTTP error with the following  
body: ``{"timestamp": number, "status": number, "error": string, "exception": string, "message": string, "path": string}``.  
  
## customer-service  
  
1. ``GET /``: shall return a list of elements in JSON format with the following structure   
``{id: integer, pesel: string, name: string, type: string, accounts: list of accounts (from account-service)}``.  
2. ``GET /{id}``: if it exists, it shall return an element with the following structure   
``{id: integer, pesel: string, name: string, type: string, accounts: list of accounts (from account-service)}``.   
Otherwise, it shall return a HTML error page.  
3. ``GET /pesel/{number}``: if it exists, it shall return an element with the following structure   
``{id: integer, pesel: string, name: string, type: string, accounts: list of accounts (from account-service)}``.   
Otherwise, it shall return a 404 HTTP error with the following body:   
``{"timestamp": number, "status": number, "error": string, "exception": string, "message": string, "path": string}``.  
4. ``POST /`` with BODY: shall add the BODY element into the list of elements if it has the correct structure and  
the "id" is not in use. Otherwise, it shall return an 415 HTTP error with the following  
body: ``{"timestamp": number, "status": number, "error": string, "exception": string, "message": string, "path": string}``.  
5. ``DELETE /{id}``: shall remove the existing element with id in parameter from the list of elements.  
If the element does not exist in the first place, it will return "false".   
If the parameter is text, it shall return 400 HTTP error with the following  
body: ``{"timestamp": number, "status": number, "error": string, "exception": string, "message": string, "path": string}``.  
  
# Assumptions and dependencies  
  
In the response time for the HTTP requests, we have to assume the whole response time: the network transport  
from the client to the server, the server processing time, and the network transport back to the client.  
  
# Test cases  

## Performance  

|ID| Test slug | Test steps | Ref.  
|---|---|---|---|  
|01|customer-service response time|1. Start timer<br>2. Send ``GET /`` request to the server<br>3. Wait Until the response arrives<br>4. Stop Timer<br>5. Verify the contents of the response, shall contain a list of elements.<br>6. Elapsed time shall be less than 1s|1.1
|02|account-service response time|1. Start timer<br>2. Send ``GET /`` request to the server<br>3. Wait Until the response arrives<br>4. Stop Timer<br>5. Verify the contents of the response, shall contain a list of elements.<br>6. Elapsed time shall be less than 1s|1.1  
  
## account-service  

|ID| Test slug | Test steps | Ref.   
|---|---|---|---|
|03|Account Initial Elements exist|1. Send ``GET /`` request to the server<br>2. The initial list of elements shall be received | 2.1  
|04|Account Retrieve existing element on / endpoint|1. Send ``GET /1`` request to the server<br>2. The JSON object ``{"id": 1, "customerId": 1,"number": "111111"}`` shall be received | 2.2 
|05|Account Retrieve non-existing element on / endpoint|1. Send ``GET /0`` request to the server<br>2. The JSON object ``{"timestamp": 1606130410813, "status": 404, "error": "Not Found", "exception": "pl.piomin.microservices.account.exceptions.AccountNotFoundException", "message": "No such account with id : 0", "path": "/0" }`` shall be received | 2.2 
|06|Account Retrieve existing element on /number endpoint|1. Send ``GET /number/111111`` request to the server<br>2. The JSON object ``{"id": 1, "customerId": 1,"number": "111111"}`` shall be received | 2.3 
|07|Account Retrieve non-existing element on /number endpoint|1. Send ``GET /number/1`` request to the server<br>2. The JSON object ``{"timestamp": 1606130410813, "status": 404, "error": "Not Found", "exception": "pl.piomin.microservices.account.exceptions.AccountNotFoundException", "message": "No such account with id : 1", "path": "/number/1" }`` shall be received | 2.3 
|08|Account Retrieve one existing element on /customer endpoint|1. Send ``GET /customer/4`` request to the server<br>2. The JSON object ``{"id": 4, "customerId": 4,"number": "444444"}`` shall be received | 2.4
|09|Account Retrieve multiple existing elements on /customer endpoint|1. Send ``GET /customer/1`` request to the server<br>2. The list of JSON objects ``[{"id": 1, "customerId": 1,"number": "111111"},{ "id": 5,"customerId": 1, "number": "555555" }]`` shall be received | 2.4
|10|Account Retrieve non-existing element on /customer endpoint|1. Send ``GET /customer/0`` request to the server<br>2. An empty JSON list shall be received | 2.4 
|11|Account Create new element| 1. Send ``POST / {id: 10, customerId: 10, number: "101010"}`` request to the server<br>2. HTTP Status code 200 shall be received and the created structure in body | 2.5
|12|Account Create new element with known ID| 1. Send ``POST / {id: 1, customerId: 1, number: "101010"}`` request to the server<br>2. HTTP Status code 200 shall be received and empty body and no new element created | 2.5
|13|Account Delete existing element| 1. Send ``DELETE /5`` request to the server<br>2. HTTP Status code 200 shall be received and body ``true`` | 2.6
|14|Account Delete non-existing element| 1. Send ``DELETE /50`` request to the server<br>2. HTTP Status code 200 shall be received and body ``false`` | 2.6
|15|Account Delete text element| 1. Send ``DELETE /test`` request to the server<br>2. HTTP Status code 400 shall be received and body ``{"timestamp": 1606155398250, "status": 400, "error": "Bad Request","exception": "org.springframework.web.method.annotation.MethodArgumentTypeMismatchException", "message": "Failed to convert value of type [java.lang.String] to required type [java.lang.Integer]; nested exception is java.lang.NumberFormatException: For input string: \"test\"", "path": "/test" }`` | 2.6

## customer-service  

|ID| Test slug | Test steps | Ref.   
|---|---|---|---|
|16|Customer Initial Elements exist|1. Send ``GET /`` request to the server<br>2. The initial list of elements shall be received | 3.1  
|17|Customer Retrieve existing element on / endpoint|1. Send ``GET /1`` request to the server<br>2. The JSON object ``{"id":1,"pesel":"12345","name":"Adam Kowalski","type":"INDIVIDUAL","accounts":[{"id":1,"number":"111111"},{"id":5,"number":"555555"}]}`` shall be received | 3.2 
|18|Customer Retrieve non-existing element on / endpoint|1. Send ``GET /0`` request to the server<br>2. The JSON object ``{"timestamp":1606204094576,"status":404,"error":"Not Found","exception":"pl.piomin.microservices.customer.exceptions.CustomerNotFoundException","message":"No such customer with id : 0","path":"/0"}`` shall be received | 3.2 
|19|Customer Retrieve existing element on /pesel endpoint|1. Send ``GET /pesel/12345`` request to the server<br>2. The JSON object ``{"id":1,"pesel":"12345","name":"Adam Kowalski","type":"INDIVIDUAL","accounts":[{"id":1,"number":"111111"},{"id":5,"number":"555555"}]}`` shall be received | 3.3 
|20|Customer Retrieve non-existing element on /pesel endpoint|1. Send ``GET /pesel/1`` request to the server<br>2. The JSON object ``{"timestamp":1606204248204,"status":404,"error":"Not Found","exception":"pl.piomin.microservices.customer.exceptions.CustomerNotFoundException","message":"No such customer with pesel : 1","path":"/pesel/1"}`` shall be received | 3.3 
|21|Customer Create new element| 1. Send ``POST / {"pesel":"123245","name":"Adam Kowalski","type":"INDIVIDUAL"}`` request to the server<br>2. HTTP Status code 200 shall be received and the created structure in body | 3.4
|22|Customer Create new element with known ID| 1. Send ``POST / {id:1, "pesel":"123245","name":"Adam Kowalski","type":"INDIVIDUAL"}`` request to the server<br>2. HTTP Status code 200 shall be received and empty body, and no new element created | 3.4
|23|Customer Delete existing element| 1. Send ``DELETE /5`` request to the server<br>2. HTTP Status code 200 shall be received and body ``true`` | 3.5
|24|Customer Delete non-existing element| 1. Send ``DELETE /50`` request to the server<br>2. HTTP Status code 200 shall be received and body ``false`` | 3.5
|25|Customer Delete text element| 1. Send ``DELETE /test`` request to the server<br>2. HTTP Status code 400 shall be received and body ``{"timestamp":1606208299595,"status":400,"error":"Bad Request","exception":"org.springframework.web.method.annotation.MethodArgumentTypeMismatchException","message":"Failed to convert value of type [java.lang.String] to required type [java.lang.Integer]; nested exception is java.lang.NumberFormatException: For input string: \"test\"","path":"/test"}`` | 3.5
  
# Reporting  
  
All proposed tools produce reports with similar level of details. 
All of them can export the data to Jenkins, either by exporting a XML or JSON formated file, or a human-readable HTML report.
Also, Jenkins can be configured to push the test results to an Application Lifecycle Management software, like HP ALM, JIRA/XRay, SquashTM, etc.
