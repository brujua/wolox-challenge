# Api Rest for wolox challenge
This API REST has been done as a challenge for the company Wolox. The definition of the problem can be found [here](/blob/master/Java-%20Wchallenge%20-%20Enunciado%20entrevista.pdf).

Some information of the endpoints can be found via Swagger once the service is running on the url: http://localhost:8080/swagger-ui.html

This is an API REST consumes most of its resources from the API REST service: https://jsonplaceholder.typicode.com/

### Install and set up PostgreSQL to work with the project
```
$ sudo apt install postgresql
```
check if it is actually running on default port 5432 with
```
$ ss ntls
```
create db, user and grant permissions
```
$ sudo -u postgres psql
postgres=# create database wolox_challenge;
postgres=# create user <user> with encrypted password <password>;
postgres=# grant all privileges on database wolox_challenge to <user>;
```

reflect configuration on src/main/resources/application.properties file.

