package com.alten.shop.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResDto {
    private Long id;
    private String code;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String inventoryStatus;
    private String category;
    private String image;
    private int rating;
}
