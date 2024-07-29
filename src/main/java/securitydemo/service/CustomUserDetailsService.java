package securitydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//import securitydemo.model.User;
import securitydemo.model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // версия вторая, которая вроде работает но дает ошибку получения ИД стринга вместо ожидаемого лонга
//        User myUser = userService.findByName(username);
//        if (myUser == null) {
//            throw new UsernameNotFoundException("Unknown user: " + username);
//        }
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                myUser.getUsername(),
//                myUser.getPassword(),
//                myUser.getAuthorities());
//                return userDetails;

        // версия первая, которая не захотела работать из-за красного getAuthorities()
//        UserDetails user =
//                org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
//                .username(myUser.getUsername())
//                .password(myUser.getPassword())
//                .roles(myUser.getAuthorities())
//                .build();
//        return user;


        // версия дефолтного inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("usertest")
                        .password("usertest")
                        .roles("USER", "ADMIN")
                        .build();

//        userService.add((ru.kata.spring.boot_security.demo.model.User) user);

//        Set<Role> testroles = new HashSet<>();
//        List<securitydemo.model.User> testusers = new ArrayList<>();
//        securitydemo.model.User usertest = new securitydemo.model.User("usertest","usertest",13,testroles);
//        testusers.add(usertest);
//        testroles.add(new Role("ROLE_USER", testusers));
//        usertest.setRoleList(testroles);
//
//
//
        securitydemo.model.User myUser = userService.findByName("asd");
//
//        if (myUser == null) {
//            throw new UsernameNotFoundException("Unknown user: " + username);
//        }
//
//
        System.out.println(user);
        System.out.println(username);
        System.out.println(myUser.getAuthorities());
        System.out.println(myUser.getRoleList().toString());

//        List<GrantedAuthority> authorities = myUser.getRoleList().stream()
//                .map(roleList -> new SimpleGrantedAuthority(roleList.getRoleName()))
//                .collect(Collectors.toList());


        return user;


    }
}
