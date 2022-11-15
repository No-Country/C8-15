package com.nocountry.java_react.repository;

import com.nocountry.java_react.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuyerRepository extends JpaRepository<Buyer, String> {

    @Query("select b from Buyer b where b.id = id")
    Buyer searchById(String id);
}