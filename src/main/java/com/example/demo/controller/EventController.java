package com.example.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.Entity.Event;
import com.example.demo.Entity.Serie;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class EventController {

    @Autowired  
    EventRepository eventRepository;  
    @Autowired  
    SerieRepository serieRepository;  

        /**
     * Solution: Failed to convert value of type 'java.lang.String' to required type 'java.util;
     * The main reason for this error is that the required type in the Controller class is Date, but the type passed on the page side is String, which eventually leads to this error.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //convert the date Note that the conversion here should always be in the same format as the string passed in, e.g. 2015-9-9 should be yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor is a custom date editor
    }
    

    /********************************************** Creat Event **********************************************/

    @RequestMapping("/event/createEvent")
    public String createEvent(Model model,  @RequestParam("id") Long serieID, HttpServletRequest servlet, HttpServletResponse response){
        System.out.println("creationSeriecreationSeriecreationSerie");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("serieID",serieID);
            return "createEventTemplate";
        }
        response.setStatus(401);
        return "logout";
    }

    //yyyy-MM-dd HH:mm:ss.SSS
    @RequestMapping(value="/event/createEvent/{serieID}", method = RequestMethod.POST)
    public String updateSerie(Model model, @PathVariable("serieID") Long serieID, @RequestParam("value") float value,  @RequestParam("date") String date, @RequestParam("comment") String comment, HttpServletRequest servlet, HttpServletResponse response) throws ParseException{
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            System.out.println(serieID);
            System.out.println(value);
            System.out.println(date);
            System.out.println(comment);           

            
            Optional<Serie> serie = serieRepository.findById(serieID);

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date d = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(d));

            /*********************
             * I have to manage date 
             * ********************/
            Event newEvent = new Event(d, value, comment, serie.orElseThrow());
            eventRepository.save(newEvent);
            response.setStatus(201);

          
            return "redirect:/serie/openSeries?id="+serieID;
        }
        response.setStatus(401);
        return "logout";
    }

        /********************************************** Update Event **********************************************/

        ///event/updateEvent
        @RequestMapping(value="/event/updateEvent")
        public String updateEvent(Model model,  @RequestParam("eventID") Long eventID, HttpServletRequest servlet, HttpServletResponse response){
            System.out.println("updateEventupdateEventupdateEvent");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Optional<Event> event = eventRepository.findById(eventID);
                float value = event.orElseThrow().getValue();
                String comment = event.orElseThrow().getComment();
                Date date = event.orElseThrow().getDate();

                model.addAttribute("eventID",eventID);
                model.addAttribute("value",value);
                model.addAttribute("comment",comment);
                model.addAttribute("date",date);

                return "updateEventTemplate";
            }
            response.setStatus(401);
            return "logout";
        }

        @RequestMapping(value="/serie/updateEvent/{eventID}", method = RequestMethod.POST)
        public String updateSerie(Model model, @PathVariable("eventID") Long eventID, @RequestParam("value") Float value, @RequestParam("date") String date, @RequestParam("comment") String comment,  HttpServletRequest servlet, HttpServletResponse response){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {

                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date d = new Date(System.currentTimeMillis());
                eventRepository.updateEvent(eventID, value,  d, comment);
                response.setStatus(200);
                /*********************
                 * redirect to the right serie page 
                 * ********************/
                return "redirect:/series";
            }
            response.setStatus(401);
            return "logout";
        }
        /*********************************** delete ***********************************/
        //"@{'/event/deleteEvent/'+${event.id}}
        /*********************
         * change methode to delete
         * ********************/
        @RequestMapping(value="/event/deleteEvent/{eventID}", method = RequestMethod.POST)
        public String deleteSerie(Model model, @PathVariable("eventID") Long eventID ,HttpServletResponse response ){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                eventRepository.deleteById(eventID);
                response.setStatus(202);
                return "redirect:/series";
            }
            response.setStatus(401);
            return "logout";
        }
    


}
