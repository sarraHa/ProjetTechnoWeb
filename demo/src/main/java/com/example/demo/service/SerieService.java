package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.SerieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Evenement;
import com.example.demo.Entity.Serie;  

/*
INSERT INTO serie (id, description, last_modif, titre) VALUES 
  (1, 'une description', now(), 'un titre'),
  (2, 'une description', now(), 'un titre'),
  (3, 'une description', now(), 'un titre'),
  (4, 'une description', now(), 'un titre');



*/ 
@Service 
public class SerieService {
 
    @Autowired  
    SerieRepository serieRepository;  

    //getting all student records  
    public List<Serie> getAllSeries()   
    {  
        List<Serie> series = new ArrayList<Serie>();  
        serieRepository.findAll().forEach(serie -> series.add(serie));  
        return series;  
    }  

    public Serie getSerieById(Long id)   
    {  
        System.out.println("hounaaaaaaaaaaa");
        return serieRepository.findById(id).get(); 
    }  

    

    public List<Evenement> getEventsBySerieId(Long idSerie)   
    {  
        Optional<Serie> s = serieRepository.findById(idSerie);
        return s.orElseThrow().getListEvenement();
    }  

}
