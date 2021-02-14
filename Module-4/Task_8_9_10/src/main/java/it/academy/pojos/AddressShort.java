package it.academy.pojos;

import it.academy.util.AddressAnnotated;
import lombok.*;

@NoArgsConstructor
@Data
//@AddressAnnotated
public class AddressShort implements IAddress {

    private Long id;
    private String street;
    private Long home;

}
