package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Cart;
import com.thebedshop.thebedshop.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "user/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(path = "/getall")
    public @ResponseBody Iterable<Cart> getCartItems(){
        System.out.println("Get all cart items");
        return cartRepository.findAll();
    }

    @GetMapping(path = "/userId/{user_Id}")
    public @ResponseBody Iterable<Cart> getAllCartItemsByUserId(@PathVariable String user_Id){
        System.out.println("Fetching all cart items");

        return cartRepository.findAllByUid(user_Id);
    }

    @GetMapping(path = "/{cart_id}")
    public Cart getCartItem(@PathVariable String cart_id){

        return cartRepository.findById(cart_id).get();
    }

    @PostMapping(path = "/add")
    public Cart AddNewCartItem(@RequestBody Cart newCart){
        Optional cartItemOptional = cartRepository.findById(newCart.getCartId());
        Cart cartItem;
        if(cartItemOptional.isPresent()){
            cartItem = (Cart) cartItemOptional.get();
            int quantity = cartItem.getQuantity();
            cartItem.setQuantity(quantity + newCart.getQuantity());
            updateCartItem(cartItem);
        }else {
            cartItem = cartRepository.save(newCart);
        }
        return  cartItem;
    }

    @PostMapping(path = "add/items")
    public Iterable<Cart> AddItemsToCart(@RequestBody Iterable<Cart> cartItems){
        return cartRepository.saveAll(cartItems);
    }

    @PutMapping(path = "/update_item")
    public void updateCartItem(@RequestBody Cart cart){

        cartRepository.save(cart);
    }

    @DeleteMapping(path = "/{cart_id}")
    public void deleteCartItem(@PathVariable String cart_id) {

        cartRepository.deleteById(cart_id);
    }

    @DeleteMapping("/delete")
    public Iterable<Cart> deleteCartItems(@RequestBody Iterable<Cart> cartItems) {

        cartRepository.deleteAll(cartItems);
        return cartRepository.findAll();
    }
}
