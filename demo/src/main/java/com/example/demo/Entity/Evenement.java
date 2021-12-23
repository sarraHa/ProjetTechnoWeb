package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id")
    private Serie serieConteneur;


    //id serie
    //list de tags
    private float valeur;
    private Date date;
    private String commentaire;

    public Evenement(){}

    public Evenement(Date date, float valeur, String commentaire){
        this.date = date;
        this.valeur = valeur;
        this.commentaire = commentaire;
    }

    public Evenement(Date date, float valeur, String commentaire, Serie serie){
        this.date = date;
        this.valeur = valeur;
        this.commentaire = commentaire;
        this.serieConteneur = serie;
    }

    /**
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 
     */
    public float getValeur() {
        return valeur;
    }

    /**
     * @param valeur 
     */
    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    /**
     * @return 
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return 
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire 
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

}
