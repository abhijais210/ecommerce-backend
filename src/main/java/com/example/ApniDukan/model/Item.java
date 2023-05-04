package com.example.ApniDukan.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int requiredQuantity;
    @ManyToOne
    @JoinColumn
    Cart cart;
    @ManyToOne
    @JoinColumn
    Ordered ordered;
    @ManyToOne
    @JoinColumn
    Product product;
}
