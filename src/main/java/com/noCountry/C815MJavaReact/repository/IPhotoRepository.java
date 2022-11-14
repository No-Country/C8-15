package com.noCountry.C815MJavaReact.repository;

import com.noCountry.C815MJavaReact.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, String> {
}