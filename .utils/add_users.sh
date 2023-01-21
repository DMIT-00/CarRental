#!/bin/sh
curl --request POST --url http://localhost:8080/CarRentalRest/api/v1/users --header 'Content-Type: application/json' \
  --data '{
	"email": "user@gmail.com",
	"username": "user",
	"password": "user",
	"userDetail": {
		"firstName": "User",
		"lastName": "The User",
		"phoneNumber": "+884020002",
		"creditCard": "0000020030000002",
		"birthDate": -61895934000000
	}
}'

curl --request POST --url http://localhost:8080/CarRentalRest/api/v1/users --header 'Content-Type: application/json' \
  --data '{
	"email": "alex@gmail.com",
	"username": "Alex",
	"password": "Alex",
	"userDetail": {
		"firstName": "Alex",
		"lastName": "The Alex",
		"phoneNumber": "+880000004",
		"creditCard": "0000000000000202",
		"birthDate": -61895934000000
	}
}'

curl --request POST --url http://localhost:8080/CarRentalRest/api/v1/users --header 'Content-Type: application/json' \
  --data '{
	"email": "emily08@gmail.com",
	"username": "Emily",
	"password": "Emily",
	"userDetail": {
		"firstName": "Emily",
		"lastName": "The Emily",
		"phoneNumber": "+880000204",
		"creditCard": "0000004000030202",
		"birthDate": -61895934000000
	}
}'