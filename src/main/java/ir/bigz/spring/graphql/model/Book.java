package ir.bigz.spring.graphql.model;

public record Book(Integer id, String title, Integer pages, Rating rating, Author author) {
}
