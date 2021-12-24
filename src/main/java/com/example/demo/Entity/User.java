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
    
    private String username;
    private String password;

    @OneToMany(mappedBy = "createur",cascade = {CascadeType.ALL})
    private List<Serie> listSerie  = new ArrayList<>();
    
    public User(){}
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * @return ID
    */
    public Long getID() {
        return this.id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

     /**
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
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