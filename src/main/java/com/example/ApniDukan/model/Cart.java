package com.example.ApniDukan.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer cartTotal;
    Integer numberOfItems;
    @OneToOne
    @JoinColumn
    Customer customer;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
}
