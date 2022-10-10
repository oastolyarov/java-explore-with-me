package explore.controller;

import explore.model.dto.CategoryDto;
import explore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
@Slf4j
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PatchMapping()
    public CategoryDto changeCategory(@RequestBody CategoryDto categoryDto) {
        log.debug("Администратор: запрос на изменение категории с id " + categoryDto.getId());

        return categoryService.changeCategory(categoryDto);
    }

    @PostMapping()
    public CategoryDto addNewCategory(@RequestBody CategoryDto categoryDto) {
        log.debug("Администратор: запрос на добавление новой категории + " + categoryDto.getName());

        return categoryService.addNewCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        log.debug("Администратор: запрос на удаление категории по id " + id);

        categoryService.deleteById(id);
    }
}
