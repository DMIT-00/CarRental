#!/bin/sh
curl --request POST --url http://localhost:8080/CarRentalRest/api/v1/users --header 'Content-Type: application/json' \
  --data '{
	"email": "root@gmail.com",
	"username": "root",
	"password": "root",
	"userDetail": {
		"firstName": "Root",
		"lastName": "The Root",
		"phoneNumber": "+880000000",
		"creditCard": "0000000000000000",
		"birthDate": -61895934000000
	}
}'

curl --request POST --url http://localhost:8080/CarRentalRest/api/v1/users --header 'Content-Type: application/json' \
  --data '{
	"email": "manager@gmail.com",
	"username": "manager",
	"password": "manager",
	"userDetail": {
		"firstName": "Manager",
		"lastName": "The Manager",
		"phoneNumber": "+880000001",
		"creditCard": "0000000000000001",
		"birthDate": -61895934000000
	}
}'

curl --request POST --url http://localhost:8080/CarRentalRest/api/v1/users --header 'Content-Type: application/json' \
  --data '{
	"email": "user@gmail.com",
	"username": "user",
	"password": "user",
	"userDetail": {
		"firstName": "User",
		"lastName": "The User",
		"phoneNumber": "+880000002",
		"creditCard": "0000000000000002",
		"birthDate": -61895934000000
	}
}'