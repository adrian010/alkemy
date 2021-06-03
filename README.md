# Alkemy

# Spring Boot, MySQL, JPA


## Requirements

1. Java - 1.11.x

2. Maven - 3.x.x

3. Mysql - 8.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/adrian010/alkemy.git
```

**2. Create Mysql database**
```bash
create database alkemyDB
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**


```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

## App Documentation: 

### Create a character:  

Req method: POST   
localhost:8080/characters   
     
body:      
```
{     
"image": "PersonajeX.jpg",         
"name": "Personaje X",     
"age": 55,     
"height": 230.0,     
"history": "Personaje X"    
}     
```
### Create a Genre:

Req method: POST   
localhost:8080/genre

body:     
```
{    
"name":"Terror",     
"image":"Terror.jpg"      
}       
```    
### Create a Movie:
    
Req method: POST     
localhost:8080/movies     
**"NOTE: in the character and genre field you must use their IDs"**    

body:    

```
{      
"image":"Pelixula X",     
"title":"peliculaX.jpg",        
"date_created":"2005-07-25T23:09:01+07:00",      
"qualification":5,     
"characters":[      
  {      
    "id":1      
  }     
],      
"genre":[      
  {     
    "id":1     
  }      
]      
}      
```

  Swagger: 

  http://localhost:8080/swagger-ui.html#/


You can test using postman or any other rest client.

