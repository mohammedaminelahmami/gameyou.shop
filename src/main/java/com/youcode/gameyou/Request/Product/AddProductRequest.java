package com.youcode.gameyou.Request.Product;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddProductRequest {
    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    private String price;

    @NotNull(message = "categoryName is required")
    private String categoryName;
}