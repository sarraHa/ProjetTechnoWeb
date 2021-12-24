package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String accueil(Model model){
        return "index"; 
    }
    @RequestMapping("/logout")
    public String logout(){
        return "logout"; 
    }
}