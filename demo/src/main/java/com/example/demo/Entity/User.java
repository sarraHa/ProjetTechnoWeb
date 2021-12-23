package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String login;
    private String mdp;

    @OneToMany(mappedBy = "createur",cascade = {CascadeType.ALL})
    private List<Serie> listSerie  = new ArrayList<>();
    
    public User(){}
    
    public User(String login, String mdp){
        this.login = login;
        this.mdp = mdp;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

     /**
     * @param login 
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return 
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp 
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return 
     */
    public List<Serie> getListSerie() {
        return listSerie;
    }

    /**
     * @param listSerie 
     */
    public void setListSerie(List<Serie> listSerie) {
        this.listSerie = listSerie;
    }





}