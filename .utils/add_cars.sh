#!/bin/sh
curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2020,
	"color": "BLACK",
	"enginePower": 10.0,
	"fuelType": "DIESEL",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": true,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 80.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 1,
		"carBrand": {
			"id": 1
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2000,
	"color": "RED",
	"enginePower": 8.0,
	"fuelType": "ELECTRIC",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": false,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 60.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 2,
		"carBrand": {
			"id": 1
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2020,
	"color": "GREEN",
	"enginePower": 8.0,
	"fuelType": "PETROL",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": false,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 60.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 3,
		"carBrand": {
			"id": 1
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2020,
	"color": "BLACK",
	"enginePower": 10.0,
	"fuelType": "DIESEL",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": true,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 80.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 4,
		"carBrand": {
			"id": 2
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2000,
	"color": "RED",
	"enginePower": 8.0,
	"fuelType": "ELECTRIC",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": false,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 60.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 5,
		"carBrand": {
			"id": 2
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2020,
	"color": "GREEN",
	"enginePower": 8.0,
	"fuelType": "PETROL",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": false,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 60.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 6,
		"carBrand": {
			"id": 2
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2020,
	"color": "BLACK",
	"enginePower": 10.0,
	"fuelType": "DIESEL",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": true,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 80.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 7,
		"carBrand": {
			"id": 3
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2000,
	"color": "RED",
	"enginePower": 8.0,
	"fuelType": "ELECTRIC",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": false,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 60.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 8,
		"carBrand": {
			"id": 3
		}
	}
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/cars \
  --header 'Content-Type: application/json' --data '{
	"year": 2020,
	"color": "GREEN",
	"enginePower": 8.0,
	"fuelType": "PETROL",
	"fuelConsumption": 8.0,
	"transmission": "AUTOMATIC",
	"numberOfSeats": 5,
	"abs": true,
	"cruiseControl": false,
	"heatedSeats": true,
	"climateControl": true,
	"airBags": true,
	"price": 60.00,
	"bodyType": "SUV",
	"carModel": {
		"id": 9,
		"carBrand": {
			"id": 3
		}
	}
}'