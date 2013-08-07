keyper
======

# Introduction

Keyper is a basic Key Management Service that would allow SA to add users and their keys for later retrival via a rest
interface

# Overview

for development purpouses everytime you run the service a new DB will be created from scratch (loosing all previouse data)
this behavior can be changed in persistence.xml file

# Running The Application

* To package the example run.

        mvn package

* To run the server and create the DB.

        java -jar target/keyper-0.0.1-SNAPSHOT.jar server example.yml