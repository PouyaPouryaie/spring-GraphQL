package ir.bigz.spring.graphql.controller;

import ir.bigz.spring.graphql.model.Book;
import ir.bigz.spring.graphql.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {


    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    @SchemaMapping(typeName = "Query", value = "allBooks")
    @QueryMapping
    public List<Book> allBooks(){
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book findOne(@Argument(name = "id") Integer id){
        return bookRepository.findOne(id);
    }
}
