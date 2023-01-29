package ir.bigz.spring.graphql.person.controller;

import ir.bigz.spring.graphql.person.exception.PersonQLException;
import ir.bigz.spring.graphql.person.model.Person;
import ir.bigz.spring.graphql.person.repository.PersonRepository;
import ir.bigz.spring.graphql.person.view.PersonDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PersonGraphQlController {

    private final PersonRepository personRepository;

    public PersonGraphQlController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @QueryMapping
    public List<Person> allPeople() {
        return personRepository.findAll();
    }

    @QueryMapping
    public Page<Person> allPeoplePaged(@Argument int page, @Argument int size) {
        PageRequest pr = PageRequest.of(page, size);
        return personRepository.findAll(pr);
    }

    @QueryMapping
    public Person findPeopleById(@Argument(name = "id") Integer id) {
        return personRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new PersonQLException(String.format("person with %s dose not exists", id));
                });
    }

    @MutationMapping
    public Person addPerson(@Argument(name = "person") @Valid PersonDto personDto) {
        Person person = PersonDto.mapToPerson(personDto);
        return personRepository.save(person);
    }
}
