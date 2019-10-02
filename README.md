# Organisational-API
An app that helps to manipulate user, news and departments for any given organisation.
### Author: 
**Odette Ahishakiye** on October 2nd, 2019.

## Description
The **Organisational-API app** is designed to help manipulating workers and the departments information, as well as the news about department.
When it is said manipulation, it means registering(create), deleting and updating. All these, with this app are done with database. 

## Setup/Installation Requirements
* Open your computer
* Connect to the internet 
* Open the IntelliJ and the Terminal
* Go to  [my GitHub page](https://github.com/ahiodette/Organisational-API)
    #### PostgreSQL for Database
        * CREATE DATABASE organisational_api;
        * \c organisational_api;
        
        * CREATE TABLE users(id SERIAL PRIMARY KEY, username VARCHAR, post VARCHAR, depsize VARCHAR);
        * CREATE TABLE departments(id SERIAL PRIMARY KEY, depname VARCHAR, depdescr VARCHAR, depsize INTEGER);
        
        * CREATE TABLE news(id SERIAL, depid INTEGER, content VARCHAR, header VARCHAR);
        * CREATE TABLE department_users(id SERIAL, userid INTEGER, deptid INTEGER);
        * CREATE TABLE department_news(id SERIAL, newsid INTEGER, deptid INTEGER);

## Link to the live site:
https://odette-organisational-api.herokuapp.com/

## Known bugs
Even though the app should help the user to delete and update the info; for today, it is not possible to do so on the UI unless he/she goes through the database, but that issue will be resolved ASAP!


## Support and Contact details
In case you may need any support about this app, do not hesitate to contact the developer on ahiode6@gmail.com and 
[linkedIn](https://www.linkedin.com/in/odette-ahishakiye-096a39188/)

## Licence and copyright

This app is licenced by [MIT](https://github.com/ahiodette/Organisational-API/blob/master/LICENSE) 
