package ir.bigz.spring.graphql.book.model;

public record Book(Integer id, String title, Integer pages, Rating rating, Author author) {
}
