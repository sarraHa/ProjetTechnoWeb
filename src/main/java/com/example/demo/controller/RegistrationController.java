package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(Model model){
        System.out.println("registrationGETregistrationGETregistrationGET");

        User user = new User();
        model.addAttribute("user",user);
        return "registration"; // Le nom du fichier html du dossier template
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(@ModelAttribute User user, Model model){
        System.out.println("iciiiiiiiiiiiiiiiiiiiiiii");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("iciiiiiiiiiiiiiiiiiiiiiii");

        if(user.getMdp().isEmpty() || user.getLogin().isEmpty()){
            // Ici si l'utilisateur a passer outre la vérification Js de la Vue, on redirige sur la page d'erreur? il faudrai definir un code 400.
            return "error";
        }
        System.out.println("iciiiiiiiiiiiiiiiiiiiiiii");

        user.setMdp(passwordEncoder.encode(user.getMdp()));

        userRepository.save(user);
        return "redirect:/login"; // Le nom du fichier html du dossier template
    }
}