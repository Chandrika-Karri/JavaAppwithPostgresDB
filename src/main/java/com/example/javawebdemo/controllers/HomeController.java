package com.example.javawebdemo.controllers;

import com.example.javawebdemo.service.FizzBuzzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController extends BaseController {

    @Autowired
    private FizzBuzzService fizzBuzzService;
    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            model.addAttribute("email", user.getAttribute("email"));
        }

        return "home";
    }
    @PostMapping("/")
    String homePost(@RequestParam int number, Model model,Authentication authentication) {
        String fizzBuzz = fizzBuzzService.fizzBuzz(number);
        model.addAttribute("number", number);
        model.addAttribute("fizzBuzz", fizzBuzz);

        // Set isAuthenticated and email just like in GET
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            model.addAttribute("email", user.getAttribute("email"));
        }

        return "home";


    }
}

