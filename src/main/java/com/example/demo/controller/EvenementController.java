package com.example.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.example.demo.Entity.Evenement;
import com.example.demo.Entity.Serie;
import com.example.demo.repository.EvenementRepository;
import com.example.demo.repository.SerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController  
public class EvenementController {

    @Autowired  
    EvenementRepository evenementRepository;  
    @Autowired  
    SerieRepository serieRepository;  

    @RequestMapping(value = "/ajoutEvenement/{id}",method = RequestMethod.POST)
    public String addEvent(@PathVariable("id") Long id, @RequestParam("valeur") Float valeur,  @RequestParam("commentaire") String commentaire){

        //@RequestParam("date")@DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss.SSS")  Date date,
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date(1);
        System.out.println(formatter.format(date));

        
        Optional<Serie> s = serieRepository.findById(id);

        Evenement evenement = new Evenement(date,valeur,commentaire,s.orElseThrow());
        evenementRepository.save(evenement);
        //response.setStatus(201);
        return "ok";
    }

}
