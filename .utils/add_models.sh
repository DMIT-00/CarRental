#!/bin/sh
curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/brands \
  --header 'Content-Type: application/json' --data '{
  "id": "1",
	"brandName": "BMW"
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/brands \
  --header 'Content-Type: application/json' --data '{
  "id": "2",
	"brandName": "KIA"
}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/brands \
  --header 'Content-Type: application/json' --data '{
  "id": "3",
	"brandName": "Honda"
}'

# BMW models
curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
		"modelName": "X1",
		"carBrand": {
			"id": 1
		}
	}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
		"modelName": "X3",
		"carBrand": {
			"id": 1
		}
	}'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
		"modelName": "X5",
		"carBrand": {
			"id": 1
		}
	}'

# KIA models
curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
    "modelName": "Sportage",
    "carBrand": {
      "id": 2
    }
  }'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
    "modelName": "Sorento",
    "carBrand": {
      "id": 2
    }
  }'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
    "modelName": "Rio",
    "carBrand": {
      "id": 2
    }
  }'

# Honda models
curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
    "modelName": "Accord",
    "carBrand": {
      "id": 3
    }
  }'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
    "modelName": "CR-V",
    "carBrand": {
      "id": 3
    }
  }'

curl --request POST -u "manager:manager" --url http://localhost:8080/CarRentalRest/api/v1/models \
  --header 'Content-Type: application/json' --data '{
    "modelName": "Civic",
    "carBrand": {
      "id": 3
    }
  }'