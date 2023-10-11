package pl.javastart.equipy.components.inventory.category;

import jakarta.persistence.*;
import lombok.*;
import pl.javastart.equipy.components.inventory.asset.Asset;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Asset> assets = new HashSet<>();
}
