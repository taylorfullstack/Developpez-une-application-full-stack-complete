# Monde de Dev
![Monde de Dev](https://github.com/taylorfullstack/Developpez-une-application-full-stack-complete/blob/main/front/src/assets/logo_p6.png?raw=true)

## Description
Monde de Dev is a social networking application for developers, built with Spring Boot 3.2.4 and Angular 17.3.1

## Prerequisites
Before you begin, ensure you have met the following requirements:
- You have installed the latest version of Java and Maven.
- You have a MySQL server running. If not, you can [download it from here](https://dev.mysql.com/downloads/installer/).
- [Git](https://docs.github.com/en/get-started/quickstart/set-up-git)
- [NodeJS (**version 20.9.0 - LTS**)](https://nodejs.org/en/) - includes NPM 10.1.0
- [NPM (**version 10.1.0**)](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)
- [Angular CLI](https://github.com/angular/angular-cli)

## Test User

For your convenience, a test user will be initialized once you complete the backend installation steps. 
You can then use these credentials to access the Swagger Documentation and the frontend of the application.

- email : `test@mdd.com`
- username : `testuser`
- password : `Mdd@123!`

You may also choose to register a new user with your own credentials.

## Backend installation and launch

### 1. Clone the repository

```bash
git clone https://github.com/taylorfullstack/Developpez-une-application-full-stack-complete.git
```

### 2. Navigate to the backend directory

```bash
cd back
```

### 3. Create a new database in your MySQL server

   Open a terminal and run the command

  ```bash
  mysql -u mysql_username -p < mdd.sql
  ```

Important Note: Replace `mysql_username` with your actual MySQL username.

[Use this script](https://github.com/taylorfullstack/Developpez-une-application-full-stack-complete/blob/main/back/src/main/resources/sql/mdd.sql) to create the tables for the database.

### 4. Update the application.properties file

  You need to update the application properties file with the correct server, database, and security variables.

  You may use a .env file (recommended), or replace the variables in the resources/application.properties file directly.
  
  The following variables need to updated with your own values:
  
  ```properties
  #use the database URL, username, and password from your MySQL server
  spring.datasource.url=${DB_URL}
  spring.datasource.username=${DB_USERNAME}
  spring.datasource.password=${DB_PASSWORD}

  jwt.secret=${JWT_SECRET} #a secure, random string (UUID recommended)
  client.url=${CLIENT_URL} #example: http://localhost:4200

  springdoc.swagger-ui.oauth.clientSecret=${JWT_SECRET} #the same secure, random string (UUID recommended) used for jwt.secret
  ```

  *Note* - You can optionally change the server.url via the application properties file. The default url is `http://localhost:8080/api/`
  
  If you change the server url, be sure to change it [here in the frontend environments directory](https://github.com/taylorfullstack/Developpez-une-application-full-stack-complete/tree/main/front/src/environments) as well.

### 5. Build the project and install its dependencies

```bash
mvn clean install
```

### 6. Launch the backend of the application

```bash
mvn spring-boot:run
```

Once the server is running locally, you can access the API endpoints at `http://localhost:8080/api/`.

## Swagger Documentation

After launching the backend of the application locally, the Swagger documentation can be found at `http://localhost:8080/swagger-ui/index.html`

From here, you can access all routes that do not require authentication.

To access authenticated routes:
- Either login with the [test user credentials](https://github.com/taylorfullstack/Developpez-une-application-full-stack-complete/edit/main/README.md#test-user) or register a new user and login. 
- Copy the returned jwt token, click the authorize button, paste the token into the form in the modal and click authorize. 
- You will now be able to access authenticated routes as this user until you click the authorize button again and then click logout.

## Frontend installation and launch

### 1. Navigate to the frontend directory

```bash
cd front
```

### 2. Install the frontend dependencies

```bash
npm install
```

### 3. Launch the frontend of the application

```bash
npm run start
```

The application will launch in your browser at `http://localhost:4200`.

### 4. Connect to Monde de Dev

You may now register a new user and then login, or connect using the [test user credentials](https://github.com/taylorfullstack/Developpez-une-application-full-stack-complete/edit/main/README.md#test-user).
