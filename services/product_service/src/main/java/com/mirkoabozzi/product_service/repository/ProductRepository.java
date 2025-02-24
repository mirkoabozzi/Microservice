package com.mirkoabozzi.product_service.repository;

import com.mirkoabozzi.product_service.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
