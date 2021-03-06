package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.Entity.Serie;
import com.example.demo.Entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;  



public interface  SerieRepository extends CrudRepository<Serie, Long>{

    List<Serie> findAll();

    List<Serie> findByCreateur(User createur);

    @Query(value = "SELECT * FROM serie WHERE createur_id= :createurID ", nativeQuery = true)
    List<Serie> getSerieByUserId(@Param("createurID") Long userId);

    @Modifying
    @Transactional
    @Query(value = "update serie set title = :title, description =:description WHERE id= :id ", nativeQuery = true)
    void updateSerie(@Param("id") Long id,@Param("title") String titre,@Param("description") String description);


}
