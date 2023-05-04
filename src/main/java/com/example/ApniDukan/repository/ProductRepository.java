package com.example.ApniDukan.repository;

import java.util.List;
import com.example.ApniDukan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public List<Product> findBySellerId(Integer sellerId);
    @Query(value = "select * from product p where p.quantity < :quantity",nativeQuery = true)
    public List<Product> getProductsLessthanQuantity(Integer quantity);
}
