# Bank Microservice

### How to start application

#### You can choose where to start application: docker-container or your host.
1. Starting as host: set (spring.profiles.active=host) in application.properties and run your application.
2. Starting in docker container: set (spring.profiles.active=container) in application.properties
   and go to the root project directory, compile jar-file with the help of maven command (mvn clean, mvn package -Dmaven.test.skip), call terminal and perform (docker compose up).

### Swagger

http://localhost:8080/swagger-ui.html

### Endpoints

#### - Transactions:

Endpoint which helps us to get information about transactions.

Api for getting all transactions (method GET) - 
http://localhost:8080/api/v1/transactions

This API creates a new transaction and returns its ID (method POST) - 
http://localhost:8080/api/v1/transactions

Api for getting all limit exceeded transactions - 
http://localhost:8080/api/v1/transactions/limit-exceeded

#### - Monthly Limits:

Endpoint which helps us to get information about monthly limits.

Api for getting all limits (method GET) - 
http://localhost:8080/api/v2/limits

This API creates a new limit and returns its dto structure (method POST) - 
http://localhost:8080/api/v2/limits

### Application properties

#### Datasource properties:

- spring.jpa.properties.hibernate.dialect
#####
- spring.datasource.url
#####
- spring.datasource.username
#####
- spring.datasource.password

#### Base URL for data manipulation with API:

- twelvedata.api.baseApiUrl=https://api.twelvedata.com/time_series

#### TwelveData - API KEY:

- twelvedata.api.apiKey={your personal key}

____________________________________________________

### To start the service, follow these steps:

1. **Installation PostgreSQL:**
    - Create user (postgres) with password (admin)
    - Create database:
      ```sql
      CREATE DATABASE bank;
      ```
      
2. **Setting up a key to access daily exchange rate requests:**
    -  Register and copy the apiKey in your personal account: https://twelvedata.com
    - Replace the key in the file application-host.properties or application-contrainer.properties:
      ```bash
      twelvedata.api.baseApiUrl=https://api.twelvedata.com/time_series
      twelvedata.api.apiKey={here}
      ```

3. **Clone the repository:**
      ```bash
      git clone URL-Repository
      ```
   
4. **Test microservice:**

    Build the project using maven in the integrated environment:
    ```bash
      mvn clean package
    ```
    - Using Liquibase migration: all tables and basic data required for the program to work.
    - Open Docker Desktop
    - Go to the project directory and enter
    - ```bash
      docker compose up --build
