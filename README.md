# PRODUCT PRICE API

This microservice obtains information about a product of a brand.   
Specifically gets the final sale price and applicable price list by a given date.  
  
## HOW TO RUN  
- Open project on your IDE  
- Go to Run - Edit Configurations menu
- Add new configuration of type 'Application'
- Insert any name, and select this class to be run:  
com.inditex.ecommerce.ProductPriceApplication  
- Save configuration and click play button
- Go to this url to open swagger page:  
[url]: http://localhost:8080/ProductPrice.html
- To test endpoint, insert this info:  
brandId: number    
productId: number    
applicationDate: LocalDateTime, format: yyyy-MM-ddThh:mm
  
## ECOMMERCEDB - H2 IN-MEMORY DATABASE    
Application uses an H2 in-memory database.
It has a table called PRICES.  
When application is run, four rows are automatically inserted.  
To see/edit inserted data, look for data.sql file on price-launcher module.

- Run application and go to this url: http://localhost:8080/h2-console    
Driver Class: org.h2.Driver  
JDBC URL: jdbc:h2:mem:ecommercedb  
UserName: admin  
Password:  
- Credentials can be edited on application.yml file on resources package of Price-Launcher module.
  
## SERVICE INFORMATION   
This service is based on a hexagonal architecture and DDD. This ensures the re-usability of the business logic by making it technical-agnostic.    
The Domain layer is the core of the architecture and should not be affected by a stack change, it dependes on nothing but himself.  
This service lives on a multi-module spring boot project.   
These modules are:  
- Application: Exposes a rest endpoint.
- Domain:  The core, contains the business logic. Has no dependency on other layers.
- Infrastructure:  Communicates with the database.
- Price-Launcher: Bootstrap all the modules.
  
## UNIT TESTS   
Each module has a battery of unit tests.  
In Application module there are unit tests for Test1 to Test5 requested cases.
