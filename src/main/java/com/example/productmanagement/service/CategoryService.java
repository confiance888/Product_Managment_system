package com.example.productmanagement.service;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.exception.ResourceNotFoundException;
import com.example.productmanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service layer for {@link Category} operations.  Encapsulates business logic
 * and delegates persistence to the {@link CategoryRepository}.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories.
     *
     * @return list of categories
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by its identifier.  Throws
     * {@link ResourceNotFoundException} if none exists.
     *
     * @param id the category id
     * @return the category
     */
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    /**
     * Persists a new category.
     *
     * @param category the category to create
     * @return the created category
     */
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    /**
     * Updates an existing category.
     *
     * @param id       the id of the category to update
     * @param details  the updated category details
     * @return the updated category
     */
    public Category update(Long id, Category details) {
        Category category = findById(id);
        category.setName(details.getName());
        return categoryRepository.save(category);
    }

    /**
     * Deletes a category.
     *
     * @param id the id of the category to delete
     */
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}