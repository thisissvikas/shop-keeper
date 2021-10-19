# DESIGN DOCUMENT FOR PRODUCT MANAGEMENT SERVICE

## Overview
This design document shows the high-level flow of components of product management in the Shop-Keeper project.

## Link to API Spec
https://github.com/krvikas1011/shop-keeper/blob/main/Product%20Management%20Service/Documents/API_Spec.txt

## Flow
- The product controller will have the endpoints as listed in the API spec. 
- The database connection will be made using Spring Data JPA that will provide us with the repository component providing basic CRUD operations. 
- The service class will be using repository controller to perform CRUD operations.
- The service class will then serve the response back to the controller. 

## Dependancies
- For version-based database migration, we will be using flyway. It will help us to handle version control of database schemas.

## Exception Handling
- Proper exception handling will be followed, like in case of resource not found, 404 will be thrown, or in case of internal server issue, 503 will be thrown, etc.