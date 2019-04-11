package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Product;
import com.thebedshop.thebedshop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/getall")
    public @ResponseBody Iterable<Product> getProducts(){
        System.out.println("Get all products");
        return productRepository.findAll();
    }

    @GetMapping(path = "/{product_id}")
    public Product getProduct(@PathVariable String product_id){

        return productRepository.findById(product_id).get();
    }

    @PostMapping(path = "/new_product")
    public Product newProduct(@RequestBody Product product){


        return  productRepository.save(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product){

            productRepository.save(product);
    }

    @DeleteMapping(path = "/{product_id}")
    public void deleteProduct(@PathVariable String product_id) {


        productRepository.deleteById(product_id);

    }
}
