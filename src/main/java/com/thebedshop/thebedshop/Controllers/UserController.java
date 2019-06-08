package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Auth;
import com.thebedshop.thebedshop.Models.Role;
import com.thebedshop.thebedshop.Models.UserForm;
import com.thebedshop.thebedshop.Models.User;
import com.thebedshop.thebedshop.Repositories.RoleRepository;
import com.thebedshop.thebedshop.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/register")
    public User addNewUser(@RequestBody UserForm user){

        Role role = roleRepository.findById(user.role).get();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User newUser = new User();
        newUser.firstName = user.firstName;
        newUser.lastName = user.lastName;
        newUser.gender = user.gender;
        newUser.address = user.address;
        newUser.contactNumber = user.contactNumber;
        newUser.email = user.email;
        newUser.userImage = user.userImage;
        newUser.password = user.password;
        newUser.role = roleSet;
        userRepository.save(newUser);
        System.out.println(newUser.lastName + " is added");

        return newUser;
    }

   // @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/auth/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        System.out.println("Get all users");
        return userRepository.findAll();
    }

    @GetMapping(path = "/auth/{user_id}")
    public User getUser(@PathVariable String user_id){

        return userRepository.findById(user_id).get();
    }

    @PostMapping("/public/authenticate")
    public User findByEmail (@RequestBody Auth auth){
        User user;
        if(userRepository.existsByEmail(auth.getEmail())){
            if(userRepository.findUserByEmail(auth.getEmail()).get().getPassword().equals(auth.getPassword())){
                user = userRepository.findUserByEmail(auth.getEmail()).get();
            }else {
                user = null;
            }
        }else{
            user = null;
        }

        return user;
    }
    @GetMapping("check/{email}")
    public Boolean isEmailExist(@PathVariable String email){
        System.out.println("Checking email is available");

        return userRepository.existsByEmail(email);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail (@PathVariable String email){
        System.out.println("Fetching all products");
        return userRepository.findUserByEmail(email).get();
    }

//    @PostMapping(path = "/new_user")
//    public User newUser(@RequestBody User user){
//
//
//        return  userRepository.save(user);
//    }

    @PutMapping(path = "/auth")
    public void updateUser(@RequestBody User user){

        userRepository.save(user);

    }

    @DeleteMapping(path = "/{user_id}")
    public void deleteUser(@PathVariable String user_id) {

        userRepository.deleteById(user_id);
    }
}
