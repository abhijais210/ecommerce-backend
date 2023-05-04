package com.example.ApniDukan.repository;

import com.example.ApniDukan.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    public Customer findByMobNo(String mobNo);
    public Customer findByEmailId(String email);
}
