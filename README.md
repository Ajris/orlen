# Orlen Project

Project for finding path to specified 
map of crossroads and roads, with 
specification of length and width of road 
and truck.

## Getting Started

To run project:

*  `git clone https://github.com/Ajris/orlen.git`
* `mvn clean install`
* `mvn spring-boot:run `

### Prerequisites

You need to have jdk 11 and mongo.
If you have mongo, just type
```
sudo mongod
```
and check that its set up on port 27017

## Running the tests

Just run

`mvn clean test`

If you want to check jacoco and sonarqube, just type

`mvn clean test`

and then

````
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.exclusions="**/*/entity/**/*" 
````
this configuration is for currently /entity package
and sonarqube on docker, which run on this port