### Movie Catalog

RESTful application implemented in Java using JAX-RS. 

It allows you to browse through a list of movies, 
where it is possible to add, update and delete movies 
as well as searches based on genres.

### Tech

The application uses a number of open source projects to work properly:

- Backbone.js
- Underscore.js 
- jQuery
- Cassandra
- Gradle

The application uses [Accessor-annotated interface](http://docs.datastax.com/en/developer/java-driver/2.1/common/drivers/reference/accessorAnnotatedInterfaces.html) for all CRUD operations.

### Set Up:

To run the application you need to install [Apache Cassandra.](http://docs.datastax.com/en/cassandra/2.0/cassandra/install/installDeb_t.html)

Execute movie_app.cql to create the Keyspace "movie_ks" 
as well as create and populate the table "movies"

```sh
$ cqlsh -f movie_app.cql
```
