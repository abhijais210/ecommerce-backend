package com.example.ApniDukan.repository;

import com.example.ApniDukan.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {
}
