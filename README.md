# Simple-personality-test
This is a simple personality test for assignment purposes, User can enter their email and answer to several personality questions seperated by categories.
Right now,  answers are not processed to create a personality result only tored in database. This can be done in the future.

** When application boots, personality-test.json file in the root folder is parsed, mapped and stored in DB

# BUILD

Prerequisites
----------------
1- Docker must be installed on the environment

Run
--------
1- Clone the project to your local environment

2- Run docker-compose up on a terminal in the project folder, this can take a while in the first run.

Project Structure
-----------------

There two modules in the project[backend,frontend]
  
| Backend                   | Frontend      |      
|---------------------------|--------------:|
| Java 11                   | React 16.13.1 |
| Spring Boot 2.3.1.RELEASE | Axios         |
| H2 In Memory Database     | Semantic UI.  |  
  
Miscellaneous
-------------

- Database console can be accessed from http://localhost:8080/api/v1/h2-console
- REST API Requests can be accessed from https://www.getpostman.com/collections/cfbf0057b9790cc3b277
- UI design is copied from https://github.com/hitman99/personality-test Thanks hitman!

Thoughts
-----------
- Right now, answers are stored in H2 RDBMS. Since there is no relation between different user's answers, It can be stored on a NOSQL DB like Mongo for faster reads and processing
