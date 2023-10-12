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
        assetById.ifPresent(a -> {
            throw new DuplicateSerialNumberException();
        });
        return mapAndSave(assetDto);
    }

    Optional<AssetDto> findAssetById(Long id) {
        return assetRepository.findById(id)
                .map(assetMapper::toDto);
    }

    AssetDto update(AssetDto assetDto) {
        Optional<Asset> optionalAsset = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        optionalAsset.ifPresent(asset -> {
            if (!asset.getId().equals(assetDto.getId())) {
                throw new DuplicateSerialNumberException();
            }
        });
        return mapAndSave(assetDto);
    }

    private AssetDto mapAndSave(AssetDto assetDto) {
        Asset entityToUpdate = assetMapper.toEntity(assetDto);
        Asset updatedAsset = assetRepository.save(entityToUpdate);
        return assetMapper.toDto(updatedAsset);
    }

    List<AssetAssignmentDto> getAssetAssignments(Long assetId) {
        return assetRepository.findById(assetId)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(AssetAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }
}
