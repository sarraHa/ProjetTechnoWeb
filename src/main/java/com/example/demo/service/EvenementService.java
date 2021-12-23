package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.EvenementRepository;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Entity.Serie;  
import com.example.demo.Entity.Evenement;


/*
INSERT INTO serie (id, description, last_modif, titre) VALUES 
  (1, 'une description', now(), 'un titre'),
  (2, 'une description', now(), 'un titre'),
  (3, 'une description', now(), 'un titre'),
  (4, 'une description', now(), 'un titre');



*/ 
@Service 
public class EvenementService {
 
    @Autowired  
    EvenementRepository evenementRepository;  

    /*
    public List<Evenement> getEvenementByIdSerie(Long idSerie)   
    {  
        return evenementRepository.findByIdSerie(idSerie); 
    }  
    */

    



}
