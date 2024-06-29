package com.example.demo.Repository;

import com.example.demo.Model.CartItemProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CartItemProductRepository extends JpaRepository<CartItemProduct, Long> {
    Optional<CartItemProduct> findBySlug(String slug);
}
