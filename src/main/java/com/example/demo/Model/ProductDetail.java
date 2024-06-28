package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor


public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", length = 3000)
    private String category;
    private boolean isNew;
    @Column(columnDefinition = "TEXT", length = 3000)
    private Double price;
    @Column(columnDefinition = "TEXT", length = 3000)
    private String description;
    @Column(columnDefinition = "TEXT", length = 3000)
    private String features;

    @OneToOne(mappedBy = "productDetail")
    @JsonIgnore
    private Product product;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInclude> includes;


}
