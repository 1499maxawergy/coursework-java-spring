package ru.max.coursework.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class redirectController {

    @GetMapping("/index")
    public String getHome(){
        return "index";
    }
    @GetMapping("/contacts")
    public String getContacts(){
        return "contacts";
    }
    @GetMapping("/error")
    public String getError(){
        return "error";
    }
    @GetMapping("/masters")
    public String getMasters(){
        return "masters";
    }
    @GetMapping("/login")
    public String getLogin() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "login";
    }
    @GetMapping("/shop")
    public String getShop(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "shop";
        }
        return "shop_unregistered";
    }
    @GetMapping("/lk")
    public String getLK(){
        return "lk";
    }
}
