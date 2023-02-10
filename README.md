## CarRental

- [About](#about)
- [Technology stack](#technology-stack)
- [How to build](#how-to-build)
- [How to use](#how-to-use)
- [Project architecture](#project-architecture)

## About
CarRental is a simple car rental webapp.

## Technology stack
**Java: 11**

**Build:** Maven

**Core:** Spring MVC, Spring Security, Spring Data JPA, Hibernate

**Additional:** Lombok, Hibernate Validator, MapStruct, Mockito

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

## How to use
The application has different user roles:
- **ADMIN** - can edit user details and roles.
- **MANAGER** - can edit and add cars, brands, models and change orders.
- **USER** - can see available cars and make orders.
- **GUEST** (User not logged in) - can see available cars and register a new user.

One user can have multiple roles.

Application UI will reflect current role and actions available for the role.

By default application will create:
- **ADMIN** with login **"root"** and password **"root"**
- **MANAGER** with login **"manager"** and password **"manager"**

## Project architecture
![layers](https://user-images.githubusercontent.com/114758136/211890854-582977c3-3732-41bb-8376-8e868b0f9ab7.jpg)


## Screenshots
<details>
  <summary>Show</summary>
  <img src="https://user-images.githubusercontent.com/114758136/215787284-a56cfdbf-fc9f-430d-9360-b66d7e71885d.jpg">
  <img src="https://user-images.githubusercontent.com/114758136/215787295-f5a93803-485b-41fa-9d0c-d1a7ba628292.jpg">
  <img src="https://user-images.githubusercontent.com/114758136/216294490-c64f624e-2bdd-4476-b312-e1f997de7684.jpg">
</details>

