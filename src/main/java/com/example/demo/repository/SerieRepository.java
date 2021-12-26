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

    
    @Query(value = "select id, description, last_modif, title, createur_id from serie where id in (select id_serie from status t where t.id_user = :userId and  t.id_status = 0)", nativeQuery = true)
    List<Serie> getReadSerieByUserId(@Param("userId") Long userId);
//  INSERT INTO status(ID_serie, id_user, id_status ) VALUES(2, 3, 0);
//select id, description, last_modif, title, createur_id from serie s, status t where t.id_user = 3 and  t.id_status = 0
    // select * from serie where id in (select id_serie from status t where t.id_user = 3 and  t.id_status = 0)

        
    @Query(value = "select id, description, last_modif, title, createur_id from serie where id in (select id_serie from status t where t.id_user = :userId and  t.id_status = 1)", nativeQuery = true)
    List<Serie> getEditSerieByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "update serie set title = :title, description =:description WHERE id= :id ", nativeQuery = true)
    void updateSerie(@Param("id") Long id,@Param("title") String titre,@Param("description") String description);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO status(id_serie, id_user, id_status) VALUES(:id_serie, :id_user, :id_status)", nativeQuery = true)
    void shareSerie(@Param("id_serie") Long id_serie, @Param("id_user") Long id_user, @Param("id_status") int id_status);
    

}
