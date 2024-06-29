package com.example.demo.Service;

import com.example.demo.Model.ProductImage;
import com.example.demo.Repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;

    public void save(ProductImage productImage) {
        Optional<ProductImage> existingProductImage = Optional.ofNullable(productImageRepository.findById(productImage.getId()).orElse(null));
        if (existingProductImage.isEmpty()) {
            productImageRepository.save(productImage);
        }
    }

}
