# Eltex Test

Test task for Eltex company.

## Installation

OS X & Linux:

1. Configure MongoDB  
Install mongoDB database  
Set database, collections and documents
  ``` use mydb
      db.createCollection("visitors") 
      db.visitors.insert({"title" : "visitors_counter", "counter" : NumberInt(0)})
  ```  
  Configure config.properties (see resources folder)
  ```  
  db.address=localhost
  db.port=27017 
  db.name=mydb
  db.tablename=visitors  
  ```  
2. Create war file
  ```
  mvn war:war
  ```
## Usage example

We can run app in two ways.

1. Jetty from maven   
  ```
  mvn jetty:run
  ```
  Type http://localhost:8090/ in browser  
2. Tomcat  
   Place testEltex-1.0-SNAPSHOT.war in webapps Tomcat folder.  
   Type http://localhost:8080/testEltex-1.0-SNAPSHOT  
  
## Release History

* 0.0.1
    * Work in progress
