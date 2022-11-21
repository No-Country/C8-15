package com.nocountry.java_react.repository;

import com.nocountry.java_react.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, String> {

    @Query("SELECT (count(p) > 0) FROM Photo p WHERE p.fileName = :fileName")
    boolean existsByPhotoName(@Param("fileName") String fileName);

    @Query("SELECT p FROM Photo p WHERE p.fileName = :fileName")
    Photo searchPhotoByName(@Param("fileName") String fileName);

    @Query("SELECT p FROM Photo p ORDER BY p.fileName ASC")
    List<Photo> searchPhotoAll();
}