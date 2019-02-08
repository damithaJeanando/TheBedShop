package com.thebedshop.thebedshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Product> getProducts(){
        return productRepository.findAll();
    }

    @GetMapping(path = "/{productId}")
    public Product getProduct(@PathVariable String productId){
        return productRepository.findById(productId).get();
    }

    @PostMapping(path = "/newProduct")
    public Product newProduct(@RequestBody Product product){
        productRepository.save(product);
        System.out.println(product.getName() + "is added");

        return product;
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        productRepository.save(product);

        return product;
    }

    @DeleteMapping(path = "/productId")
    public void deleteProduct(@PathVariable String productId) {

        productRepository.deleteById(productId);
    }

}
