package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private float value;
    private Date date;
    private String comment;

   

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id")
    private Serie seriesContainer;


    //id serie
    //list de tags
 public Event(){}

    public Event(Date date, float value, String comment){
        this.date = date;
        this.value = value;
        this.comment = comment;
    }

    public Event(Date date, float value, String comment, Serie serie){
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.seriesContainer = serie;
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
    public float getValue() {
        return value;
    }

    /**
     * @param value 
     */
    public void setValue(float value) {
        this.value = value;
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
    public String getComment() {
        return comment;
    }

    /**
     * @param comment 
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

}
