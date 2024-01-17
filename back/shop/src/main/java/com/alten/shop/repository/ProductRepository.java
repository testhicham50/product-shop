package com.alten.shop.repository;

import com.alten.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findByCode(String id);

}
