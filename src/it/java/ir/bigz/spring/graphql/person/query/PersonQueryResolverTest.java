package ir.bigz.spring.graphql.person.query;

import com.github.javafaker.Faker;
import ir.bigz.spring.graphql.customer.config.ScalarConfig;
import ir.bigz.spring.graphql.person.controller.PersonGraphQlController;
import ir.bigz.spring.graphql.person.model.Address;
import ir.bigz.spring.graphql.person.model.Person;
import ir.bigz.spring.graphql.person.repository.PersonRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Import({ScalarConfig.class})
@GraphQlTest(value = {PersonGraphQlController.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonQueryResolverTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockBean
    private PersonRepository personRepository;

    private Faker faker;

    @BeforeAll
    public void init(){
        faker = new Faker();
    }

    @Test
    public void findAllPeopleShouldReturnAllPeople(){

        List<Person> people = IntStream.rangeClosed(1,5)
                .mapToObj(i -> new Person(
                        faker.number().randomDigit(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().cellPhone(),
                        faker.internet().emailAddress(),
                        new Address(
                                faker.address().streetAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        )
                )).toList();

        Mockito.when(personRepository.findAll()).thenReturn(people);

        graphQlTester.documentName("people-query")
                .execute()
                .path("allPeople")
                .entityList(Person.class)
                .hasSize(5);
    }
}
