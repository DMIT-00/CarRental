#!/bin/sh
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-brand?brandName=BMW'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-brand?brandName=KIA'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-brand?brandName=Honda'

curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=1&modelName=X1'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=1&modelName=X3'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=1&modelName=X5'

curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=2&modelName=Sportage'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=2&modelName=Sorento'

curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=3&modelName=Accord'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=3&modelName=CR-V'
curl -d username=root -d password=root -L http://localhost:8080/CarRental/user-login --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=3&modelName=Civic'
