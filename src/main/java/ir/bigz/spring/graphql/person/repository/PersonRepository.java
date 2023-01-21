package ir.bigz.spring.graphql.person.repository;

import ir.bigz.spring.graphql.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
