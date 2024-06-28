package com.example.demo.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartItemDto {

    private String slug;
    private String name;
    private String image;
    private Long quantity;
    private Double price;

}
