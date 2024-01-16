package com.alten.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", unique = true, nullable = false)
    private String code;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description", nullable = false, length = 1000)
    private String description;

    @Column(name = "product_price", nullable = false)
    private double price;

    @Column(name = "product_quantity", nullable = false)
    private int quantity;

    @Column(name = "inventory_status", nullable = false)
    private String inventoryStatus;

    @Column(name = "product_category", nullable = false)
    private String category;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_rating")
    private int rating;
}
