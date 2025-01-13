# warehouse-app

This application is built with spring boot, uses MySQL as a database and Gradle as a build tool.

## Setup the needed dependencies

The application runs in a local environment, for which you need to install the following tools:

- Java >= 17 https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
- Gradle https://gradle.org/install/
- MySQL https://dev.mysql.com/downloads/installer/ 
- MySQL Workbench https://www.mysql.com/products/workbench/

## Database setup

In MySQL Workbench, create a new database and a new user connected to this database. Then, go to the '.env' file of the project and change the values of the variables (wherever you see ###) to the values that you used. 

## Run the project

- For Windows: Open a command prompt, navigate to the project folder and run the command 'gradlew bootRun'
- For Linux/Mac: Open a terminal, navigate to the project folder and run the command './gradlew bootRun'

The application is available in the address http://localhost:8080


