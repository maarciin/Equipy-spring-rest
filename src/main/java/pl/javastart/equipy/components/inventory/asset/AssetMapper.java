package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javastart.equipy.components.inventory.category.Category;
import pl.javastart.equipy.components.inventory.category.CategoryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
class AssetMapper {

    private final CategoryRepository categoryRepository;
    AssetDto toDto(Asset asset) {
        AssetDto assetDto = new AssetDto();
        assetDto.setId(asset.getId());
        assetDto.setName(asset.getName());
        assetDto.setDescription(asset.getDescription());
        assetDto.setSerialNumber(asset.getSerialNumber());
        if(asset.getCategory() != null) {
            assetDto.setCategory(asset.getCategory().getName());
        }
        return assetDto;
    }

    Asset toEntity(AssetDto dto) {
        Asset entity = new Asset();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setSerialNumber(dto.getSerialNumber());
        //mamy assetDto.getCategory - co jest nazwą kategorii
        //musimy zatem odnaleźć kategorie po nazwie
        Optional<Category> categoryByName = categoryRepository.findByName(dto.getName());
        //jeśli istnieje to ustawiamy ją do encji
        categoryByName.ifPresent(entity::setCategory);
        return entity;
    }
}
