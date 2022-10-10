package explore.service;

import explore.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto changeCategory(CategoryDto categoryDto);

    CategoryDto addNewCategory(CategoryDto categoryDto);

    void deleteById(Integer id);

    List<CategoryDto> getAll(Integer from, Integer size);

    CategoryDto getById(Integer catId);
}
