package com.noCountry.C815MJavaReact.repository;

import com.noCountry.C815MJavaReact.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuyerRepository extends JpaRepository<Buyer, String> {
}