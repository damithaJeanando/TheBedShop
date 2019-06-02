package com.thebedshop.thebedshop.Services;


import com.thebedshop.thebedshop.Repositories.UserRepository;
import com.thebedshop.thebedshop.Models.CustomUser;
import com.thebedshop.thebedshop.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(s);
        if(user.isPresent()) {
            return new CustomUser(user.get());
        }else {
            throw new UsernameNotFoundException("email not found");
        }
    }
}
