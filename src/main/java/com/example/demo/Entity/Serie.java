package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Date lastModif = new Date();

    @OneToMany(mappedBy = "seriesContainer" , cascade = {CascadeType.ALL})
    private List<Event> listEvent = new ArrayList<>();

    // faire en sort de creer une autre table droit pour enlever le createur de la table serie
    @ManyToOne(fetch = FetchType.LAZY)
    private User createur;


    public Serie(){}
    
    public Serie(String title, String description, User createur ){
        this.title = title;
        this.description = description;
        this.createur = createur;
    }

     /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


        /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
     /**
     * @return the title
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param title the title to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


     /**
     * @return the title
     */
    public Date getLastModif() {
        return lastModif;
    }

    /**
     * @param title the title to set
     */
    public void setLastModif(Date lastModif) {
        this.lastModif = lastModif;
    }

     /**
     * @return the listEvent
     */
    public List<Event> getListEvent() {
        return listEvent;
    }

    /**
     * @param listEvent the listEvent to set
     */
    public void setListEvent(List<Event> listEvent) {
        this.listEvent = listEvent;
    }

         /**
     * @return the listEvent
     */
    public User getCreateur() {
        return this.createur;
    }





}