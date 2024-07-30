package securitydemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import securitydemo.model.User;
import securitydemo.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PrevController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/admin/")
    public String printAllUsersWithForm(@ModelAttribute("user") User user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);
        List<User> usersFromDB = new ArrayList<>();
        usersFromDB = userService.listUsers();
        modelMap.addAttribute("users", usersFromDB);
        return "users";
    }

    @RequestMapping(value = "/admin/useradd", method = RequestMethod.POST)
    public String printAllUsersWithNewAdded(@ModelAttribute("user") User user, ModelMap modelMap) {
        modelMap.addAttribute("user", user);		//геттеры для формы
        String name = user.getName();
        String password = user.getPassword();
        int age = user.getAge();
        User userForAdd = new User(name,password,age);
        userService.add(userForAdd);
        List<User> usersFromDB = new ArrayList<>();
        usersFromDB = userService.listUsers();
        modelMap.addAttribute("users", usersFromDB);
        return "users";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String printAllUsersAfterDelete(@ModelAttribute("user") User user, ModelMap modelMap, @PathVariable Long id){
        modelMap.addAttribute("user", user);		//геттеры для формы
        Optional<User> optionalUser = userService.read(id);
        if (optionalUser.isPresent()) {userService.delete(optionalUser.get());
        } else {
            System.out.println("ERROR when try to delete User with ID=" + id);
        }
        List<User> usersFromDB = new ArrayList<>();
        usersFromDB = userService.listUsers();
        modelMap.addAttribute("users", usersFromDB);
        return "users";
    }

    @GetMapping(value = "/admin/userchange/{id}")
    public String printFormForUserChange(@ModelAttribute("user") User user, ModelMap modelMap, @PathVariable Long id) {
        modelMap.addAttribute("user", user);		//геттеры для формы
        modelMap.addAttribute("IDforChange", id);
        return "userChangePage";
    }

    @PostMapping(value = "/admin/useredit/{id}")
    public String printAllUsersWithChanged(@ModelAttribute("user") User user, ModelMap modelMap, @PathVariable Long id) {
        modelMap.addAttribute("user", user);		//геттеры для формы
        Optional<User> opt = userService.read(id);
        if (opt.isPresent()) {
            User userFromOpt = opt.get();
            userFromOpt.setName(user.getName());
            userFromOpt.setPassword(user.getPassword());
            userFromOpt.setAge(user.getAge());
            userService.update(userFromOpt);
        } else {
            System.out.println("ERROR CHANGE USER: no User with this ID");
        }
        List<User> usersFromDB = new ArrayList<>();
        usersFromDB = userService.listUsers();
        modelMap.addAttribute("users", usersFromDB);
        return "users";
    }
}
