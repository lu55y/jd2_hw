package it.academy.pojos;


import it.academy.util.AddressAnnotated;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component
public class Person {

    private Integer id;
    private Integer age;
    private String name;
    private String surname;
    @Autowired
    @AddressAnnotated
    private IAddress address;
}
