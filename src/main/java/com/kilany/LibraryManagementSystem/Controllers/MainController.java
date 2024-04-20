package com.kilany.LibraryManagementSystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping({"/home","/"})
    public String homePage(){
        System.out.println("home");
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        System.out.println("login page");
        return "custom_login";
    }


}
