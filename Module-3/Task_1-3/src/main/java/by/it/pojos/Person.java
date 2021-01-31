package by.it.pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "T_PERSON")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ID")
    private Integer id;
    @Column(name = "P_AGE")
    private Integer age;
    @Column(name = "P_NAME")
    private String name;
    @Column(name = "P_SURNAME")
    private String surname;
}
