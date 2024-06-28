package com.example.demo.Repository;

import com.example.demo.Model.ProductInclude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductIncludesRepository extends JpaRepository<ProductInclude,Long>{
}
