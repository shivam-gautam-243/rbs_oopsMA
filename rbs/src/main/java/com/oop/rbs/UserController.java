package com.oop.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest signupRequest) {
        String email = signupRequest.getEmail();


        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            ;
        }
        else {
            return "{\"Error\": \"Forbidden, Account already exists\"}";
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(signupRequest.getPassword());
        newUser.setName(signupRequest.getName());

        userRepository.save(newUser);

        return "Account Creation Successful";
    }

    @GetMapping("/user")
    public Object getUserDetails(@RequestParam("userID") Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "{\"Error\": \"User does not exist\"}";
        }

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("name", user.getName());
        userDetails.put("userID", user.getUserID());
        userDetails.put("email", user.getEmail());

        return userDetails;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmail(email);

        if (user != null) {
            ;
        }
        else {
            return "{\"Error\": \"User does not exist\"}";
        }


        if (!user.getPassword().equals(password)) {
            return "{\"Error\": \"Username/Password Incorrect\"}";
        }

        return "Login Successful";
    }


    @GetMapping("/users")
    public List<Map<String, Object>> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> users = new ArrayList<>();
        usersIterable.forEach(users::add);

        return users.stream()
                .map(user -> {
                    Map<String, Object> userDetails = new HashMap<>();
                    userDetails.put("name", user.getName());
                    userDetails.put("userID", user.getUserID());
                    userDetails.put("email", user.getEmail());
                    return userDetails;
                })
                .collect(Collectors.toList());
    }

}
