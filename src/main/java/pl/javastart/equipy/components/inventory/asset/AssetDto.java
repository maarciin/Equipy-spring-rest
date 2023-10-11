package pl.javastart.equipy.components.inventory.asset;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class AssetDto {
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String category;
}
