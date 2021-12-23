package com.example.demo.controller;

import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired  
    UserRepository userRepository; 


    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    /*
    @RequestMapping(value = "/inscription")
    public String inscription(){
        System.out.println("iciiiiiiiiiiiiiiiiiiiiiii");
        return "inscription";
    }

    @RequestMapping(value = "/inscription",method = RequestMethod.POST)
    public String inscription(@ModelAttribute Utilisateur utilisateur, Model model){
        System.out.println("dansinscriptioninscription ");
        System.out.println(utilisateur.getMdp());
        System.out.println("dansinscriptioninscription ");


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));

        utilisateurRepository.save(utilisateur);
        System.out.println("dans utilisateurRepositoryutilisateurRepository");

        return "redirect:/login"; 
    }
    */


    
}

