# warehouse-app

This application is developed with Spring Boot, uses MySQL as the database and Gradle as the build tool. Additionally, it uses Thymeleaf for server-side rendering of HTML views. It implements authentication and CRUD (Create, Read, Update, Delete) operations.

## Setup the needed dependencies

The application runs in a local environment, for which you need to install the following tools:

- Java 17+ https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
- Gradle https://gradle.org/install/
- MySQL Server https://dev.mysql.com/downloads/installer/ 

## Clone the repository 

Download the source code, by running the command 'git clone https://github.com/kalligia/warehouse-app'. And then run the command
'cd warehouse-app'.

## Database setup

- For Windows users: Open a Command Prompt and navigate to the MySQL installation folder. The default path is C:\Program Files\MySQL\MySQL Server X.X\bin. / For Linux/Mac users: Open a Terminal navigate to /usr/bin/ or /usr/local/mysql/bin/
- Connect to the server as a root user, using the command 'mysql -u root -p;'
- Run the following command to create the database schema 'CREATE DATABASE warehousedb;'
- Create a user 'CREATE USER 'warehousedbuser'@'%' IDENTIFIED BY 'XXXX';' (Replace 'XXXX' with your password). Grant the privileges on the schema that you created 'GRANT ALL PRIVILEGES ON warehousedb.* TO 'warehousedbuser'@'%'; FLUSH PRIVILEGES; '
- Go to the '.env' file of the project and update the variables with the values you used (wherever you see ###)

## Run the project

- For Windows: Open a command prompt, navigate to the project folder and run the command 'gradlew bootRun'
- For Linux/Mac: Open a terminal, navigate to the project folder and run the command './gradlew bootRun'

The application is available in the address http://localhost:8080


