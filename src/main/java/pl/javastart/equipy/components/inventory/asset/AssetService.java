package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AssetService {
    private final AssetMapper assetMapper;
    private final AssetRepository assetRepository;

    List<AssetDto> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    List<AssetDto> findAssetsByNameOrSerialNumber(String text) {
        return assetRepository.findAssetsByNameOrSerialNumber(text).
                stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    AssetDto save(AssetDto assetDto) {
        Optional<Asset> assetById = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        assetById.ifPresent(a-> {
            throw new DuplicateSerialNumberException();
        });
        Asset entityToSave = assetMapper.toEntity(assetDto);
        Asset savedAsset = assetRepository.save(entityToSave);
        return assetMapper.toDto(savedAsset);
    }
}
