package com.alten.shop.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReqDto {
    @NotNull(message = "Code cannot be null")
    @NotEmpty(message = "Code cannot be empty")
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Positive(message = "Price must be a positive number")
    private double price;

    @PositiveOrZero(message = "Quantity must be a non-negative number")
    private int quantity;

    @NotNull(message = "Inventory cannot be null")
    @NotEmpty(message = "Inventory cannot be empty")
    @NotBlank(message = "Inventory status cannot be blank")
    private String inventoryStatus;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotBlank(message = "Image URL or path cannot be blank")
    private String image;

    @Min(value = 1, message = "Rating must be a positive integer")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private int rating;

}
