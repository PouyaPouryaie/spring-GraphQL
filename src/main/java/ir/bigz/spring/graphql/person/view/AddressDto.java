package ir.bigz.spring.graphql.person.view;

import ir.bigz.spring.graphql.person.model.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotBlank(message = "address must not be blank")
    private String address;
    private String city;
    private String state;
    private String zip;

    public static Address mapToPerson(AddressDto addressDto) {
        return new Address(addressDto.getAddress(),
                addressDto.getCity(),
                addressDto.getState(),
                addressDto.getZip());
    }
}
