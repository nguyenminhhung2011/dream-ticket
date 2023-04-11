package controller;

import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
        return null;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return null;
    }

}
