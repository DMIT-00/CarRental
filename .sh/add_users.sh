#!/bin/sh
curl --request POST --url 'http://localhost:8080/CarRental/add-user?email=user%40gmail.com&username=user&password=user&passwordRepeat=user&userDetail.firstName=User&userDetail.lastName=The+User&userDetail.phoneNumber=%2B12200000000&userDetail.creditCard=0000000000000001&userDetail.birthDate=0008-08-08&submit-user='
curl --request POST --url 'http://localhost:8080/CarRental/add-user?email=root%40gmail.com&username=root&password=root&passwordRepeat=root&userDetail.firstName=Root&userDetail.lastName=The+Root&userDetail.phoneNumber=%2B880000000&userDetail.creditCard=0000000000000000&userDetail.birthDate=0008-08-08&submit-user='
curl --request POST --url 'http://localhost:8080/CarRental/add-user?email=manager%40gmail.com&username=manager&password=manager&passwordRepeat=manager&userDetail.firstName=Manager&userDetail.lastName=The+Manager&userDetail.phoneNumber=%2B2800000000&userDetail.creditCard=0000000000000008&userDetail.birthDate=0008-08-08&submit-user='