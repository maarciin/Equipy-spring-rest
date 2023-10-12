package pl.javastart.equipy.components.assignment;

import jakarta.persistence.*;
import lombok.*;
import pl.javastart.equipy.components.inventory.asset.Asset;
import pl.javastart.equipy.components.user.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
//jest pośrednikiem relacje ManyToMany miedzy Asset i User
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
}
