package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;
    private Double price;
    private Long quantity;
    private String image;

    @OneToOne(mappedBy = "cartItemProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private CartItem cartItem;
}
