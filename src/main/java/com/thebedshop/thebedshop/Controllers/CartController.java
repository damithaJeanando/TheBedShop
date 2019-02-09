package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Cart;
import com.thebedshop.thebedshop.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(path = "/getcart")
    public @ResponseBody Iterable<Cart> getCartItems(){
        System.out.println("Get all cart items");
        return cartRepository.findAll();
    }

    @GetMapping(path = "/{cart_id}")
    public Cart getCartItem(@PathVariable String cart_id){

        return cartRepository.findById(cart_id).get();
    }

    @PostMapping(path = "/new_item")
    public Cart newCartItem(@RequestBody Cart cart){

        System.out.println(cart.getName() + "is added");

        return  cartRepository.save(cart);
    }

    @PutMapping(path = "/update_item")
    public void updateCartItem(@RequestBody Cart cart){

        cartRepository.save(cart);
    }

    @DeleteMapping(path = "/{cart_id}")
    public void deleteCartItem(@PathVariable String cart_id) {

        cartRepository.deleteById(cart_id);
    }
}
