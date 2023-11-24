package cinema.controller;

import cinema.entity.user;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService serviciu) {
        userService = serviciu;
    }

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

    @PostMapping("/users")
    @ResponseBody
    public user addUser(@RequestBody user theUser) {
        theUser.setId(0);
        user dbUser = userService.save(theUser);
        return dbUser;
    }


}
