### Install and set up PostgreSQL to work with the project

$ sudo apt install postgresql

check if it is actually running on default port 5432 with
 $ ss ntls
 
create db, user and grant permissions
$ sudo -u postgres psql
postgres=# create database wolox_challenge;
postgres=# create user <user> with encrypted password <password>;
postgres=# grant all privileges on database wolox_challenge to <user>;
 
reflect configuration on src/main/resources/application.properties file.

