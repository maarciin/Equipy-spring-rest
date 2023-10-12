package pl.javastart.equipy.components.user;

import jakarta.persistence.*;
import lombok.*;
import pl.javastart.equipy.components.assignment.Assignment;


import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String pesel;

    @OneToMany(mappedBy = "user")
    private List<Assignment> assignments = new ArrayList<>();

}
