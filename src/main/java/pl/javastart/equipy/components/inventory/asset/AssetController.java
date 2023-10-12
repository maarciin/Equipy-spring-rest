package pl.javastart.equipy.components.inventory.asset;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
