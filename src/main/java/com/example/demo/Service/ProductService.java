package com.example.demo.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        Product existingProduct = productRepository.findByName(product.getName()).stream().findFirst().orElse(null);
        if (existingProduct == null) {
            productRepository.save(product);
        }
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
