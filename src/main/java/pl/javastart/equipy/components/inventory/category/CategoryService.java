package pl.javastart.equipy.components.inventory.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class CategoryService {
    private final CategoryRepository categoryRepository;

    List<String> findAllNames() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
