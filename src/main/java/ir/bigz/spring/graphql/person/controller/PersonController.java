package ir.bigz.spring.graphql.person.controller;

import ir.bigz.spring.graphql.person.model.Person;
import ir.bigz.spring.graphql.person.repository.PersonRepository;
import ir.bigz.spring.graphql.person.view.PersonDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Person> findAll(@RequestParam int page, @RequestParam int size) {
        PageRequest pr = PageRequest.of(page,size);
        return repository.findAll(pr);
    }

    @PostMapping
    public Person addPerson(@RequestBody @Valid PersonDto personDto) {
        Person person = PersonDto.mapToPerson(personDto);
        return repository.save(person);
    }
}
