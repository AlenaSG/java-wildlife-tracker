## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Refactoring Notes:
1. Create abstract class for animals include endangered and non-endangered as extensions. Add tests and database.
1a. Move health and age in Endangered Animals from properties to additional fields.
2. Include timestamp for each sighting. and tests
3. Add extra methods for updated classes.
3. Make sure constants are used for heath and aga values.
4. Rearrange App.java.


### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar, type varchar, health varchar, age varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, timeseen timestamp);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

### License

Copyright (c) 2017 **_MIT License_**
