package com.kilany.LibraryManagementSystem.security;

import com.kilany.LibraryManagementSystem.security.model.MyUser;
import com.kilany.LibraryManagementSystem.security.model.MyUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserRepository userRepository;

    @PostMapping("/register_user")
    public RedirectView createUser(@RequestParam("username")String username , @RequestParam("password") String password){
        MyUser user = new MyUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        // Redirect to the login page
        return new RedirectView("/login", true);
    }



}
