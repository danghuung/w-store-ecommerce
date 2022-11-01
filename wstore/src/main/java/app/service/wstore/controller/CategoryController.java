package app.service.wstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.service.wstore.dto.CategoryDto;
import app.service.wstore.exception.DuplicateRecordException;
import app.service.wstore.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Get All category
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getListCategory() {
        return new ResponseEntity<>(categoryService.getListCategory(), HttpStatus.OK);
    }

    // Create category
    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        if (categoryService.checkCategoryName(categoryDto.getName())) {
            throw new DuplicateRecordException("Category already is taken!");
        } else {

            return new ResponseEntity<>(categoryService.create(categoryDto), HttpStatus.CREATED);
        }
    }

    // Update category
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable int id, @RequestBody CategoryDto categoryDto) {

        if (categoryService.checkCategoryName(categoryDto.getName())) {
            throw new DuplicateRecordException("Category already is taken!");
        } else {

            return new ResponseEntity<>(categoryService.updateCategory(id, categoryDto), HttpStatus.OK);
        }
    }

    // Delete category
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCategoryy(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }
}
