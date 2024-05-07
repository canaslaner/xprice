# Xprice
new generation best price finder ...

## Technical Stack
+ java 21
+ embedded h2 for local use
+ postgres support for uat/prod use
+ spring boot 3.2.4
+ open api 3
+ docker

## Development Notes
+ For development purposes, app integrates with h2 by default. 
+ Under resources folder you can find schema.sql to create schema on startup.
+ Under resources folder you can find data.sql to create example data.

## How to
### Standalone
To run the app standalone, please follow the steps below.
>./mvnw install

>java -jar target/xprice-0.0.1-SNAPSHOT.jar

### Docker
Because of the size issues openjdk:21-slim is preferred. Please follow the steps below.

>docker build -t moneypay/xprice-1.0.0 .

>docker run -p 8080:8080 moneypay/xprice

## Api Documentation & Responses
For openapi documentation :  http://localhost:8080/swagger-ui/index.html

+ Some Error Response Examples
```json
{
  "result": {
    "code": "400",
    "message": "[brand] field should not be blank"
  }
}
```
```json
{
  "result": {
    "code": "404",
    "message": "Product not found"
  }
}
```