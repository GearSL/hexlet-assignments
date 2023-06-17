package exercise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
//@Table(name = "person")
@Getter
@Setter
// END
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // BEGIN
    private String firstName;
    private String lastName;
    // END
}
