package exercise.model;

import jakarta.persistence.*;
import lombok.Data;

// BEGIN
@Data
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Lob
    private String body;

    @ManyToOne
    private Category category;
}
// END
