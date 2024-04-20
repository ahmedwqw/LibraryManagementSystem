package com.kilany.LibraryManagementSystem.Controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping({"/home","/"})
    public String homePage(Model model){
        boolean isLoggedIn = isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "custom_login";
    }

    @GetMapping("/register_user")
    public String registerPage() {
        return "/register_user";
    }


//check if user is logged in or not
public boolean isLoggedIn() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
}


}
