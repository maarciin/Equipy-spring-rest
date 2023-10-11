package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@AllArgsConstructor
class AssetController {
    private final AssetService assetService;

    @GetMapping
    List<AssetDto> findAll() {
        return assetService.findAll();
    }

}
