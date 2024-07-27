package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userService.findByName(username);                                    //dao.findByLogin(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                myUser.getUsername(),
                myUser.getPassword(),
                myUser.getAuthorities());


//        UserDetails user =
//                org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
//                .username(myUser.getUsername())
//                .password(myUser.getPassword())
//                .roles(myUser.getAuthorities())
//                .build();
        return userDetails;
    }
}
