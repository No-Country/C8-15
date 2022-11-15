package com.nocountry.java_react.repository;

import com.nocountry.java_react.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, String> {
}