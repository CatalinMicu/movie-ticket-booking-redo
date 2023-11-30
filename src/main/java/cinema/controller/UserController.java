package cinema.controller;

import cinema.entity.user;
import cinema.service.AuthoritiesService;
import cinema.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService serviciu) {
        userService = serviciu;
    }

    @Autowired
    AuthoritiesService authoritiesService;

    @GetMapping("/users")
    @ResponseBody
    public List<user> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{user_id}")
    @ResponseBody
    public user getUser(@PathVariable int user_id) {
        user theUser = userService.findById(user_id);
        if (theUser == null) {
            throw new RuntimeException("User id not found -" + user_id);
        }
        return theUser;
    }

    @Transactional
    @PostMapping("/users")
    @ResponseBody
    public user addUser(@RequestBody user newUser) {
        newUser.setEnabled(true);
        user savedUser = userService.save(newUser);
        authoritiesService.saveAuthorityForUser(savedUser);
        return savedUser;
    }

    @DeleteMapping("users/{user_id}")
    public String deleteUser(@PathVariable int user_id) {
        user tempUser = userService.findById(user_id);
        if (tempUser == null) {
            throw new RuntimeException("User id not found - " + user_id);
        }
        userService.deleteById(user_id);
        return "Deleted user id - " + user_id;
    }


}
