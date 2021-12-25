package com.example.demo.repository;

import java.util.List;

import com.example.demo.Entity.Event;

import org.springframework.data.repository.CrudRepository;




public interface EventRepository extends CrudRepository<Event,Long> {

    //List<Evenement> findByIdSerie(Long idSerie);

    /*
        @Modifying
    @Transactional
    @Query(value = "update evenement set date = :date, valeur =:valeur, commentaire = :commentaire WHERE id= :id ", nativeQuery = true)
    void updateEvenement(@Param("id") Long id, @Param("date") Date date, @Param("valeur") Float valeur, @Param("commentaire") String commentaire);

    */


}
