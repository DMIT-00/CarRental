## CarRental

- [About](#about)
- [Technology stack](#technology-stack)
- [How to build](#how-to-build)
- [Project architecture](#project-architecture)

## About
CarRental is a simple car rental webapp.

## Technology stack
**Java: 11**

**Build:** Maven

**Core:** Spring MVC, Spring Security, Spring Data JPA, Hibernate

**Additional:** Hibernate Validator, MapStruct, Mockito

## How to build
Required: Tomcat, Mysql

```sh
mvn clean install
```

Directory **.utils** has additional shell scripts:
- **add_users.sh** - creates additional users using REST API.
- **add_models.sh** - creates some car models and brands using REST API.
- **add_cars.sh** - creates some cars using REST API (requires **add_models.sh** to be run beforhand).
- **jMeter/CarRental.jmx** - jMeter profile to generate random cars (requires **add_models.sh** to be run beforhand).

## Project architecture
![layers](https://user-images.githubusercontent.com/114758136/211890854-582977c3-3732-41bb-8376-8e868b0f9ab7.jpg)


## Screenshots
<details>
  <summary>Show</summary>
  <img src="https://user-images.githubusercontent.com/114758136/215787284-a56cfdbf-fc9f-430d-9360-b66d7e71885d.jpg">
  <img src="https://user-images.githubusercontent.com/114758136/215787295-f5a93803-485b-41fa-9d0c-d1a7ba628292.jpg">
  
</details>
