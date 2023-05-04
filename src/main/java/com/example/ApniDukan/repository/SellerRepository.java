package com.example.ApniDukan.repository;

import com.example.ApniDukan.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    public Seller findByEmailId(String emailId);
    public void deleteByEmailId(String emailId);
    @Query(value = "select * from seller s where s.age < :age",nativeQuery = true)
    public List<Seller> findAllSellerLessThanAge(Integer age);
}
