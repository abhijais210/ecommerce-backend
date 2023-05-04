package com.example.ApniDukan.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seller")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int age;
    String mobNo;
    @Column(unique = true,nullable = false)
    String emailId;
    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
}
