# spring-GraphQL

This repo contains:
- GraphQL (query - mutation)
- Spring Data Jpa
- Pagination
- GraphQL test

This repo show how to use graphQL with spring for represent data, also show how to test it.


Instruction for use GraphQL
1. define graphql as directory on resources and create schema file into this directory
2. write type of object which represent in Rest-APIs base on your models.
   1. use ```type``` keyword for define objects 
   2. use ```ID``` as type for id
   3. use ```!``` to show this field have value and don't need check for null
   4. use ```[]``` to show this field has more than one data
3. define controller method and mapped query to the method
   1. use SchemaMapping -> ```@SchemaMapping(typeName = "Query", value = "allBooks")```
   2. use QueryMapping -> ```@QueryMapping``` and set name of method as same as query that define in schema
4. sample query in UI
    ~~~ GraphQL
    query {
      findOne(id:2) {
        id
        title
        pages
        rating{
          star
        }
        author{
          firstName
          lastName
        }
      }
    }
    ~~~
5. sample mutation in UI
    ~~~ GraphQL
    mutation {
      create(name: "Hot coffee", size: SHORT){
        id,
        name,
        size
      }
    }
    ~~~

Note: <br> 
- <b> Three Top level root Operation </b> <br>
  Query -> define query <br>
  Mutation -> change data, creating, updating, deleting <br>
  Subscription -> read data and keep connection open for when changes <br>

- In order to use <b>graphical UI </b>, first enable ```spring.graphql.graphiql.enabled``` in application properties, <br>
and then write this URL in browser: http://localhost:8080/graphiql?path=/graphql

Special Thanks from [Dan Vega](https://github.com/danvega/graphql-paging) for the tutorial about GraphQL