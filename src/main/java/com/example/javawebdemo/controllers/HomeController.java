package com.example.javawebdemo.controllers;

import com.example.javawebdemo.model.FizzBuzzResult;
import com.example.javawebdemo.repository.FizzBuzzResultRepository;
import com.example.javawebdemo.s3.BucketCreation;
import com.example.javawebdemo.s3.Xmlfile;
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

    @Autowired
    private BucketCreation bucketCreation;


    @Autowired
    private Xmlfile xmlfile;

    @Autowired
    private FizzBuzzResultRepository fizzBuzzResultRepository;

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken);
        model.addAttribute("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            String email = user.getAttribute("email");
            model.addAttribute("email", email);

//            String xml = xmlfile.createLoginXml(email);
//            System.out.println("xml: " + xml);
            xmlfile.createLoginXml(email);
        }

        return "home";
    }
    @PostMapping("/")
    public String handlePost(@RequestParam int number, Model model, Authentication authentication) {
        String fizzBuzz = fizzBuzzService.fizzBuzz(number);

        // Save the result to the database
        fizzBuzzResultRepository.save(new FizzBuzzResult(number, fizzBuzz));

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

