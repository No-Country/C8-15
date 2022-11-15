package com.nocountry.java_react.repository;

import com.nocountry.java_react.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotographerRepository extends JpaRepository<Photographer, String> {

    @Query("select p from Photographer p where p.id = id")
    Photographer searchById(String id);
}