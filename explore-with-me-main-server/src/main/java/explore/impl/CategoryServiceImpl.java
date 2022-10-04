package explore.impl;

import explore.mapper.CategoryMapper;
import explore.model.Category;
import explore.model.dto.CategoryDto;
import explore.repository.CategoryRepository;
import explore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto changeCategory(CategoryDto categoryDto) {

        categoryRepository.changeCategory(categoryDto.getName(), categoryDto.getId());
        return categoryDto;
    }

    @Override
    public CategoryDto addNewCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.save(CategoryMapper.toCategory(categoryDto));
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAll(Integer from, Integer size) {
        List<CategoryDto> categories = categoryRepository.findAll().stream()
                .map(CategoryMapper::toCategoryDto)
                .toList();
        return categories;
    }

    @Override
    public CategoryDto getById(Integer catId) {
        Category category = categoryRepository.findById(catId).get();

        return CategoryMapper.toCategoryDto(category);
    }
}
