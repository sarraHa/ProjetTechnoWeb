package com.example.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.example.demo.Entity.Event;
import com.example.demo.Entity.Serie;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController  
public class EventController {

    @Autowired  
    EventRepository eventRepository;  
    @Autowired  
    SerieRepository serieRepository;  


}
