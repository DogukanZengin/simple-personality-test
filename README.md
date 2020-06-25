# Simple-personality-test
This is a simple personality test for assignment purposes

# BUILD

Prerequisites
----------------
1- Docker must be installed on the environment

Run
--------
1- Clone the project to your local environment
2- Run docker-compose up on a terminal in the project folder

Project Structure
-----------------

There two modules in the project[backend,frontend]
  - Backend uses Spring Boot 2.3, Java 11 and H2 DB for data store
  - Frontend uses Reactjs, Axios, Semantic UI
  
Miscellaneous
-------------

- Database console can be accessed from http://localhost:8080/api/v1/h2-console

Thoughts
-----------
- Right now answers are stored in H2 RDBMS. Since there is no relation between different user's answers, It can be stored on a NOSQl DB like Mongo for faster reads and processing
