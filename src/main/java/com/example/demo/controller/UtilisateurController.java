package com.example.demo.controller;

import com.example.demo.service.SerieService;

import java.util.List;

import com.example.demo.Entity.Serie;
import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.Entity.Evenement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class UtilisateurController {

    //autowired the StudentService class  
    @Autowired  
    UserRepository utilisateurRepository;  
    /*
    @RequestMapping(value = "/inscription",method = RequestMethod.POST)
    public String inscription(@ModelAttribute Utilisateur utilisateur){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(utilisateur.getMdp().isEmpty() || utilisateur.getLogin().isEmpty()){
            // Ici si l'utilisateur a passer outre la vérification Js de la Vue, on redirige sur la page d'erreur? il faudrai definir un code 400.
            return "error";
        }
        utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));

        utilisateurRepository.save(utilisateur);
        return "ok"; // Le nom du fichier html du dossier template
    }
    */
}
