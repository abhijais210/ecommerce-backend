package com.example.ApniDukan.model;

import com.example.ApniDukan.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "card")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true,nullable = false)
    String cardNo;
    int cvv;
    @CreationTimestamp
    Date issueDate;
    Date expiryDate;
    @Enumerated(EnumType.STRING)
    CardType cardType;
    @ManyToOne
    @JoinColumn
    Customer customer;
}
