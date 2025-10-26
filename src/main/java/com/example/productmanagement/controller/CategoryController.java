package com.example.productmanagement.controller;

import com.example.productmanagement.entity.Category;
import com.example.productmanagement.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for category management.  This controller demonstrates
 * request mapping, path variables, request bodies and structured responses.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns all categories.
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    /**
     * Returns a single category by id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    /**
     * Creates a new category.  The request body is automatically bound to a
     * {@link Category} object via data binding【314475834642519†L245-L248】.
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category created = categoryService.create(category);
        return ResponseEntity.status(201).body(created);
    }

    /**
     * Updates an existing category.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }

    /**
     * Deletes a category by id.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}