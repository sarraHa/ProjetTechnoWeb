package com.example.demo.repository;

import java.util.List;

import com.example.demo.Entity.Evenement;

import org.springframework.data.repository.CrudRepository;




public interface EvenementRepository extends CrudRepository<Evenement,Long> {

    //List<Evenement> findByIdSerie(Long idSerie);


}
