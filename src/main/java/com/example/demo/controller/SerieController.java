package com.example.demo.controller;

import com.example.demo.service.SerieService;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.Entity.Serie;
import com.example.demo.Entity.User;
import com.example.demo.repository.SerieRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.Entity.Evenement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  
public class SerieController {

    //autowired the StudentService class  
    @Autowired  
    SerieService serieService; 
    @Autowired 
    SerieRepository serieRepository;


    @Autowired
    UserRepository utilisateurRepository;

    //creating a get mapping that retrieves all the students detail from the database   
    @GetMapping("/series")  
    private List<Serie> getAllSeries()   
    {  
        return serieService.getAllSeries();  
    }  

    //creating a get mapping that retrieves the detail of a specific student  
    @GetMapping("/serie/{id}")  
    private Serie getSerie(@PathVariable("id") Long id)   
    {  System.out.println("iciiiiiiiii");
        return serieService.getSerieById(id);  
    }  

    @GetMapping("/getEvents/{idSerie}")  
    private List<Evenement> getEvents(@PathVariable("idSerie") Long idSerie)   
    {  
        System.out.println("iciiiiiiiii");
        return serieService.getEventsBySerieId(idSerie);  
    } 

    
    @RequestMapping(value = "/serie/creation",method = RequestMethod.POST)
    public String creationSerie(Model model, @RequestParam("titre") String titre, @RequestParam("description") String description, HttpServletRequest servlet, HttpServletResponse response ){
        if(titre.isEmpty()){
            titre = "vide";
        }
        if(description.isEmpty()){
            description = "vide";
        }
        String loginUserCourant = servlet.getRemoteUser();
        System.out.println(loginUserCourant);
        Optional<User> userOpt = utilisateurRepository.findByUsername(loginUserCourant);
        User user = userOpt.orElseThrow();
        Serie s = new Serie(titre,description,user);
        serieRepository.save(s);
        response.setStatus(201);
        return "OK";
    }

}
