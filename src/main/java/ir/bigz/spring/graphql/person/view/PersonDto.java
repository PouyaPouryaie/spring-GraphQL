package ir.bigz.spring.graphql.person.view;

import ir.bigz.spring.graphql.person.model.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @NotBlank(message = "first name must not be blank")
    private String firstName;
    @NotBlank(message = "last name must not be blank")
    private String lastName;
    private String phoneNumber;
    private String email;
    @Valid
    private AddressDto addressDto;

    public static Person mapToPerson(PersonDto personDto){
        return new Person(personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getPhoneNumber(),
                personDto.getEmail(),
                AddressDto.mapToPerson(personDto.getAddressDto()));
    }
}
