# warehouse-app

This application is developed with Spring Boot, uses MySQL as the database and Gradle as the build tool. Additionally, it uses Thymeleaf for server-side rendering of HTML views. It implements authentication and CRUD (Create, Read, Update, Delete) operations.

## Setup the needed dependencies

The application runs in a local environment, for which you need to install the following tools:

- [Java 17+](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Gradle](https://gradle.org/install/)
- [MySQL Server](https://dev.mysql.com/downloads/installer/) 


After having cloned the project in your local pc, open a Command Prompt (if using Windows), or a Terminal (if using Linux/Mac), and follow the next steps:

## Setup the database

- Navigate to the MySQL installation folder. For Windows users, the default path is C:\Program Files\MySQL\MySQL Server X.X\bin (replace X.X with the version you use). For Linux/Mac users, navigate to /usr/bin/ or /usr/local/mysql/bin/.
- Run the following commands: 
  - `mysql -u root -p` and enter the root password, to connect to the server as a root user.
  - `CREATE DATABASE warehousedb;` to create the database schema.
  - `CREATE USER 'warehousedbuser'@'%' IDENTIFIED BY 'XXXX';` (replace 'XXXX' with your password) to create a user. 
  - `GRANT ALL PRIVILEGES ON warehousedb.* TO 'warehousedbuser'@'%'; FLUSH PRIVILEGES;` to grant the privileges on the schema that you created.
  - `exit`
- Go to the '.env' file of the project, update the variables with the password you used, and save it.

## Run the project

- Navigate to the project folder and use the command `gradlew bootRun` to run the project.
- Stop the execution by pressing `Ctrl + C` and then `Y`.
- Go to the 'application.properties' file of the project. Uncomment the indicated lines, save it and run the project one more time. Then, comment these lines again and save the file.

You are ready to use the application, which is available in the address http://localhost:8080.


