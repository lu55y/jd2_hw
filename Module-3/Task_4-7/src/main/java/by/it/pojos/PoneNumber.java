package by.it.pojos;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Access(AccessType.FIELD)
public class PoneNumber {

    @Id
    @Column(name = "PERSON_PHONENUMBER_ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "PHONENUMBER")
    @Transient
    private Integer phoneNumber;

    @Access(AccessType.PROPERTY)
    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    @Access(AccessType.PROPERTY)
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}
