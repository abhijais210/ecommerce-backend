package com.example.ApniDukan.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.annotations.Nullability;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int age;
    @Column(unique = true,nullable = false)
    String mobNo;
    @Column(unique = true,nullable = false)
    String emailId;
    String address;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Ordered> orderedList = new ArrayList<>();

}
