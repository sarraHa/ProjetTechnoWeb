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
    private String titre;
    private String description;
    private Date lastModif = new Date();

    @OneToMany(mappedBy = "serieConteneur" , cascade = {CascadeType.ALL})
    private List<Evenement> listEvenement = new ArrayList<>();

    // faire en sort de creer une autre table droit pour enlever le createur de la table serie
    @ManyToOne(fetch = FetchType.LAZY)
    private User createur;


    public Serie(){}
    
    public Serie(String titre, String description, User createur ){
        this.titre = titre;
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
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
     /**
     * @return the titre
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param titre the titre to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


     /**
     * @return the titre
     */
    public Date getLastModif() {
        return lastModif;
    }

    /**
     * @param titre the titre to set
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