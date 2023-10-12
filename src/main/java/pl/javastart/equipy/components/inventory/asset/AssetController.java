package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
@AllArgsConstructor
class AssetController {
    private final AssetService assetService;

    @GetMapping
    List<AssetDto> findAll(@RequestParam(required = false) String text) {
        if (text == null) {
            return assetService.findAll();
        } else {
            return assetService.findAssetsByNameOrSerialNumber(text);
        }
    }

    @PostMapping
    ResponseEntity<AssetDto> saveAsset(@RequestBody AssetDto assetDto) {
        if (assetDto.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Zapisywany obiekt nie może mieć ustawionego id"
            );
        }
        AssetDto savedAsset = assetService.save(assetDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAsset.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<AssetDto> getAssetById(@PathVariable Long id) {
        return assetService.findAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<AssetDto> updateAsset(@PathVariable Long id, @RequestBody AssetDto assetDto) {
        if (!id.equals(assetDto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu"
            );
        }
        AssetDto updatedAsset = assetService.update(assetDto);
        return ResponseEntity.ok(updatedAsset);
    }

}
