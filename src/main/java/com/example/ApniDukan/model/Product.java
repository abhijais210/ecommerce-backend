package com.example.ApniDukan.model;

import com.example.ApniDukan.enums.ProductCategory;
import com.example.ApniDukan.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int price;
    int quantity;
    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> itemList;
    @ManyToOne
    @JoinColumn
    Seller seller;
}
