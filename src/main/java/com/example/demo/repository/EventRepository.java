package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Entity.Event;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;




public interface EventRepository extends CrudRepository<Event,Long> {

    //List<Evenement> findByIdSerie(Long idSerie);
    
    @Modifying
    @Transactional
    @Query(value = "update event set date = :date, value =:value, comment = :comment WHERE id= :id ", nativeQuery = true)
    void updateEvent(@Param("id") Long id,  @Param("value") Float value, @Param("date") Date date, @Param("comment") String comment);




}
