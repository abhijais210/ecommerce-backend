package com.example.ApniDukan.repository;

import com.example.ApniDukan.enums.CardType;
import com.example.ApniDukan.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    public List<Card> findByCardType(CardType cardType);
    @Query(value = "select * form card c where c.card_type = :cardType and c.expiry_date > :date",nativeQuery = true)
    public List<Card> findCardsByExpiryDateGreaterThanDate(Date date, String cardType);
    public Card findByCardNo(String cardNo);
}
