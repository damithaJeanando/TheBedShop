package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.User;
import com.thebedshop.thebedshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/allusers")
    public @ResponseBody Iterable<User> getAllUsers(){
        System.out.println("Get all users");
        return userRepository.findAll();
    }

    @GetMapping(path = "/{user_id}")
    public User getUser(@PathVariable String user_id){

        return userRepository.findById(user_id).get();
    }

    @PostMapping(path = "/new_user")
    public User newUser(@RequestBody User user){


        return  userRepository.save(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){

            userRepository.save(user);

    }

    @DeleteMapping(path = "/{user_id}")
    public void deleteUser(@PathVariable String user_id) {

        userRepository.deleteById(user_id);
    }
}
