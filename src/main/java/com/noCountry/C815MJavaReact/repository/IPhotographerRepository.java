package com.noCountry.C815MJavaReact.repository;

import com.noCountry.C815MJavaReact.model.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotographerRepository extends JpaRepository<Photographer, String> {
}