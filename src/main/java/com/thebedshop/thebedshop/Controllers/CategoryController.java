package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Repositories.CategoryRepository;
import com.thebedshop.thebedshop.Models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/public/getall")
    public @ResponseBody Iterable<Category> getCategories(){

        return categoryRepository.findAll();
    }

    @GetMapping(path = "/{category_id}")
    public Category getCategory(@PathVariable String category_id){

        return categoryRepository.findById(category_id).get();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/auth/new_category")
    public Category newCategory(@RequestBody Category category){

        System.out.println(category.getCategoryName() + "is added");

        return categoryRepository.save(category);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/auth")
    public void updateCategory(@RequestBody Category category){


        categoryRepository.save(category);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = "/auth/{category_id}")
    public void deleteCategory(@PathVariable String category_id) {

        categoryRepository.deleteById(category_id);
    }

}
