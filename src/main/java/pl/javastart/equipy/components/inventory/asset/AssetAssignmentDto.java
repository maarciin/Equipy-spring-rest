package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class AssetAssignmentDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;
}
