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

    @OneToMany(mappedBy = "serieConteneur" , cascade = {CascadeType.ALL})
    private List<Evenement> listEvenement = new ArrayList<>();

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
     * @return the listEvenement
     */
    public List<Evenement> getListEvenement() {
        return listEvenement;
    }

    /**
     * @param listEvenement the listEvenement to set
     */
    public void setListEvenement(List<Evenement> listEvenement) {
        this.listEvenement = listEvenement;
    }




}