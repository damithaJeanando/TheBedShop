package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Cart;
import com.thebedshop.thebedshop.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth/cart")
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

        return cartRepository.findAllByUserEmail(user_Id);
    }

    @GetMapping(path = "/{cart_id}")
    public Cart getCartItem(@PathVariable String cart_id){

        return cartRepository.findById(cart_id).get();
    }

    @PostMapping(path = "/add")
    public Cart AddNewCartItem(@RequestBody Cart newCart){

        Cart cart = null;
        for(Cart cartItem : getCartItems()){
            if(cartItem.getProduct().getProductId().equals(newCart.getProduct().getProductId()) && cartItem.getUserEmail().equals(newCart.getUserEmail())){
                cart = cartItem;
            }
        }

        if(cart == null){
            cart = newCart;
        }else {
            int qty = cart.getQuantity() + newCart.getQuantity();
            cart.setQuantity(qty);
        }
        cartRepository.save(cart);
        System.out.println(cart.getCartId() + " item added");
        return  cart;
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

//    @DeleteMapping("/delete")
//    public Iterable<Cart> deleteCartItems(@RequestBody Iterable<Cart> cartItems) {
//
//        cartRepository.deleteAll(cartItems);
//        return cartRepository.findAll();
//    }

    @Transactional
    @DeleteMapping("/user/{email}")
    public void deleteCartItems(@PathVariable String email ) {

        cartRepository.deleteAllByUserEmail(email);

    }
}
