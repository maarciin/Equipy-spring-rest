package pl.javastart.equipy.components.inventory.asset;

import jakarta.persistence.*;
import lombok.*;
import pl.javastart.equipy.components.assignment.Assignment;
import pl.javastart.equipy.components.inventory.category.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(unique = true)
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "asset")
    private List<Assignment> assignments = new ArrayList<>();


}
