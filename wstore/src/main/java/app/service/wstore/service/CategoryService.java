package app.service.wstore.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.CategoryDto;
import app.service.wstore.entity.Category;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Boolean checkCategoryName(String name) {
        return categoryRepository.existsByName(name);
    }

    // Create category
    public CategoryDto create(CategoryDto categoryDto) {
        Category categoryEntity = new Category();
        categoryEntity = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(categoryEntity);

        return modelMapper.map(categoryEntity, CategoryDto.class);
    }

    // List all category active = true
    public List<CategoryDto> getListCategory() {
        List<Category> categories = categoryRepository.findAll();
        ArrayList<CategoryDto> result = new ArrayList<CategoryDto>();

        for (Category category : categories) {
            if (category.getIsActive() == true) {
                result.add(modelMapper.map(category, CategoryDto.class));
            }
        }
        return result;
    }

    // Update cateory from category id
    public CategoryDto updateCategory(int id, CategoryDto categoryDto) {

        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category Id Not Exist!");
        } else {

            categoryDto.setId(id);
            Category categoryEntity = modelMapper.map(categoryDto, Category.class);

            return modelMapper.map(categoryRepository.save(categoryEntity), CategoryDto.class);
        }
    }

    // Delete category from category id
    public void deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category Id Not Exist!");
        } else {

            categoryRepository.deleteById(id);
        }
    }
}
