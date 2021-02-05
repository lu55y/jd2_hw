package by.it.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "P_ID")
    private Integer id;
    @Column(name = "P_AGE")
    private Integer age;
    @Column(name = "P_NAME")
    private String name;
    @Column(name = "P_SURNAME")
    private String surname;


    @OneToOne
    @JoinColumn(name = "PERSON_PHONE_NUMBER_ID")
    private PoneNumber poneNumber;

    @Embedded
    private Address address;
}
