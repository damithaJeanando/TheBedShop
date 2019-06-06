package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Product;
import com.thebedshop.thebedshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/public/all")
    public @ResponseBody Iterable<Product> getProducts(){
        System.out.println("Get all products");
        return productRepository.findAll();
    }

    @GetMapping(path = "/{product_id}")
    public Product getProduct(@PathVariable String product_id){

        return productRepository.findById(product_id).get();
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/auth/add")
    public Product newProduct(@RequestBody Product product){


        return  productRepository.save(product);
    }

    @GetMapping(path = "/public/category/{category_id}")
    public Iterable getProductsFromCategory(@PathVariable String category_id){
        System.out.println("Fetching products from category "+category_id);
        return productRepository.findAllByCategoryId(category_id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/auth")
    public void updateProduct(@RequestBody Product product){

            productRepository.save(product);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/auth/{product_id}")
    public void deleteProduct(@PathVariable String product_id) {


        productRepository.deleteById(product_id);

    }
}
