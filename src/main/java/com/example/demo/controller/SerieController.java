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
import com.example.demo.Entity.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SerieController {

    //autowired the StudentService class  
    @Autowired  
    SerieService serieService; 
    @Autowired 
    SerieRepository serieRepository;


    @Autowired
    UserRepository utilisateurRepository;



    @RequestMapping("/series")
    public String getListeSeries(Model model, HttpServletRequest servlet, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUser = authentication.getName();
            Optional<User> userOpt = utilisateurRepository.findByUsername(currentUser);

          
            User user = userOpt.get();
            System.out.println(user.getID());

            long currentUserID = user.getID();
            List<Serie> createdSeries = serieRepository.getSerieByUserId(currentUserID);
            System.out.println(createdSeries);
            model.addAttribute("series", createdSeries);
            List<Serie> readSeries = serieRepository.getReadSerieByUserId(currentUserID);
            System.out.println(readSeries);
            model.addAttribute("readSeries", readSeries);

            List<Serie> editSeries = serieRepository.getEditSerieByUserId(currentUserID);
            System.out.println(editSeries);
            model.addAttribute("editSeries", editSeries);
            return "seriesTemplate";
        }
        response.setStatus(401);
        return "logout";
    } 

       /********************************************** Creat Serie **********************************************/

    
    @RequestMapping("/serie/createSeries")
    public String creatSerie(Model model, HttpServletRequest servlet, HttpServletResponse response){
        System.out.println("creationSeriecreationSeriecreationSerie");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return "createSeriesTemplate";
        }
        response.setStatus(401);
        return "logout";
    }

    @RequestMapping(value = "/serie/createSeries",method = RequestMethod.POST)
    public String creatSerie(Model model, @RequestParam("title") String title, @RequestParam("description") String description, HttpServletRequest servlet, HttpServletResponse response ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if(title.isEmpty()){
                title = "";
            }
            if(description.isEmpty()){
                description = "";
            }
            String currentUser = authentication.getName();
            Optional<User> userOpt = utilisateurRepository.findByUsername(currentUser);
            User user = userOpt.get();
            System.out.println(user.getID());
            Serie s = new Serie(title,description,user);
            serieRepository.save(s);
            response.setStatus(201);
            return "redirect:/series";
        }
        response.setStatus(401);
        return "logout";


       /*
        String loginUserCourant = servlet.getRemoteUser();
        System.out.println(loginUserCourant);
        Optional<User> userOpt = utilisateurRepository.findByUsername(loginUserCourant);
        User user = userOpt.orElseThrow();
        Serie s = new Serie(titre,description,user);
        serieRepository.save(s);
        response.setStatus(201);
        return "OK";
    */
    }

    
    /********************************************** UPDATE **********************************************/

    @RequestMapping(value="/serie/updateSeries")
    public String updateSerie(Model model,  @RequestParam("id") Long id, HttpServletRequest servlet, HttpServletResponse response){
        System.out.println("updateSeriesupdateSeriesupdateSeries");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Optional<Serie> serie = serieRepository.findById(id);
            String title = serie.orElseThrow().getTitle();
            String description = serie.get().getDescription();
            model.addAttribute("id",id);
            model.addAttribute("title",title);
            model.addAttribute("description",description);
            System.out.println(description);
            return "updateSeriesTemplate";
        }
        response.setStatus(401);
        return "logout";
    }



    @RequestMapping(value="/serie/updateSeries/{id}", method = RequestMethod.POST)
    public String updateSerie(Model model, @PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("description") String description, HttpServletRequest servlet, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            serieRepository.updateSerie(id,title,description);
            response.setStatus(200);
            return "redirect:/series";
        }
        response.setStatus(401);
        return "logout";
    }


        /*********************************** delete ***********************************/

        @RequestMapping(value="/serie/deleteSeries/{id}", method = RequestMethod.POST)
        public String deleteSerie(Model model, @PathVariable("id") Long id ,HttpServletResponse response ){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                serieRepository.deleteById(id); // gererer l'expection ou cas ou le telet ne se passe pas bien
                response.setStatus(202);
                return "redirect:/series";
            }
            response.setStatus(401);
            return "logout";
        }
    




    /********************************************** Open Series **********************************************/
    @RequestMapping(value="/serie/openSeries")
    public String openSerie(Model model,   @RequestParam("id") Long id, HttpServletRequest servlet, HttpServletResponse response){
        System.out.println("openSeriesopenSeriesopenSeries");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
           
            Optional<Serie> serie = serieRepository.findById(id);
            List<Event> events = serie.orElseThrow().getListEvent();
            System.out.println(events);
            model.addAttribute("serieID", serie.orElseThrow().getId());
            model.addAttribute("serieTitle", serie.orElseThrow().getTitle());
            model.addAttribute("events", events);
            return "openSeriesTemplate";
        }
        response.setStatus(401);
        return "logout";     
    }

        /********************************************** Share Series **********************************************/
        //th:href=@{/serie/shareSeries(id=${serie.id})} 
        @RequestMapping(value="/serie/shareSeries")
        public String shareSerie(Model model,   @RequestParam("serieID") Long serieID, HttpServletRequest servlet, HttpServletResponse response){
            System.out.println("shareSeriesshareSeries");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                model.addAttribute("serieID", serieID);
                return "shareSeriesTemplate";
            }
            response.setStatus(401);
            return "logout";     
        }
        
        @RequestMapping(value="/serie/shareSeries/{serieID}", method = RequestMethod.POST)
        public String shareSerie(Model model, @PathVariable("serieID") Long serieID, @RequestParam("username") String username, @RequestParam("status") int status, HttpServletRequest servlet, HttpServletResponse response){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Optional<User> userOpt = utilisateurRepository.findByUsername(username);
                User user = userOpt.get();
                long userID = user.getID();
                System.out.println("serieIDserieIDshareSeriesshareSeries");
                serieRepository.shareSerie(serieID, userID,status);

                response.setStatus(200);
                return "redirect:/series";
            }
            response.setStatus(401);
            return "logout";
        }

       




    /******** */
     //creating a get mapping that retrieves the detail of a specific student  
     @GetMapping("/serie/{id}")  
     private Serie getSerie(@PathVariable("id") Long id)   
     {  System.out.println("iciiiiiiiii");
         return serieService.getSerieById(id);  
     }  
 
     @GetMapping("/getEvents/{idSerie}")  
     private List<Event> getEvents(@PathVariable("idSerie") Long idSerie)   
     {  
         System.out.println("iciiiiiiiii");
         return serieService.getEventsBySerieId(idSerie);  
     } 

     

}
