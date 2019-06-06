package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.CustomOrders;
import com.thebedshop.thebedshop.Repositories.CustomOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customOrders")
public class CustomOrdersController {

    @Autowired
    CustomOrdersRepository customOrdersRepository;

    @GetMapping(path = "/{id}")
    public Optional<CustomOrders> findProduct(@PathVariable String id){
        return customOrdersRepository.findById(id);
    }

    @GetMapping
    public List<CustomOrders> findAllProducts(){

        return (List<CustomOrders>) customOrdersRepository.findAll();
    }

    @GetMapping(path = "/user/{userId}")
    public Iterable<CustomOrders> findOrdersByUserId(@PathVariable String userId){
        return customOrdersRepository.findAllByUserId(userId);
    }

    @PostMapping(path = "/add")
    public CustomOrders saveProduct(@RequestBody CustomOrders orderProduct){
        System.out.println("method triggered");
        return customOrdersRepository.save(orderProduct);
    }

    @PutMapping
    public CustomOrders updateProduct(@RequestBody CustomOrders orderProduct)
    {
        return customOrdersRepository.save(orderProduct);
    }

    @DeleteMapping(path="/{id}")
    public boolean deleteProduct(@PathVariable String id){
        if(customOrdersRepository.existsById(id)){
            customOrdersRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
