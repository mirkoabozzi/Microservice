package com.mirkoabozzi.product_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private String description;
    private int quantityAvailable;

    public Product(String name, String description, int quantityAvailable) {
        this.name = name;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
    }
}
