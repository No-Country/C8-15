package com.nocountry.java_react.repository;

import com.nocountry.java_react.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuyerRepository extends JpaRepository<Buyer, String> {
}