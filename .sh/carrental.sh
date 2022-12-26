#!/bin/sh
curl --request POST --url 'http://localhost:8080/CarRental/add-brand?brandName=BMW'
curl --request POST --url 'http://localhost:8080/CarRental/add-brand?brandName=KIA'
curl --request POST --url 'http://localhost:8080/CarRental/add-brand?brandName=Honda'

curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=1&modelName=X1'
curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=1&modelName=X3'
curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=1&modelName=X5'

curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=2&modelName=Sportage'
curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=2&modelName=Sorento'

curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=3&modelName=Accord'
curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=3&modelName=CR-V'
curl --request POST --url 'http://localhost:8080/CarRental/add-model?carBrand.id=3&modelName=Civic'
