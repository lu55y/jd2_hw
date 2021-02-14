package it.academy.pojos;


import it.academy.util.AddressAnnotated;
import lombok.*;

@NoArgsConstructor
@Data
@AddressAnnotated
public class AddressFull implements IAddress {

    private Long id;
    private String street;
    private String city;
    private String country;
    private String email;
}
