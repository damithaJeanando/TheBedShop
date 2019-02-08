package com.thebedshop.thebedshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Category> getCategory(){
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/{categoryId}")
    public Category getCategory(@PathVariable String categoryId){
        return categoryRepository.findById(categoryId).get();
    }

    @PostMapping(path = "/newCategory")
    public Category newCategory(@RequestBody Category category){
        categoryRepository.save(category);
        System.out.println(category.getCategoryName() + "is added");

        return category;
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category){
        categoryRepository.save(category);

        return category;
    }

    @DeleteMapping(path = "/categoryId")
    public void deleteCategory(@PathVariable String categoryId) {

        categoryRepository.deleteById(categoryId);
    }

}
