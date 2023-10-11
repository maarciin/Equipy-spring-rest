package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
