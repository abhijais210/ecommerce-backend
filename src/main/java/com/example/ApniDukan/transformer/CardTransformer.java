package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.requestDto.card.CardRequestDto;
import com.example.ApniDukan.DTOs.responsedto.card.CardResponseDto;
import com.example.ApniDukan.model.Card;

import java.util.UUID;

public class CardTransformer {
    public static Card requestToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .expiryDate(cardRequestDto.getExpiryDate())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
    public static CardResponseDto cardToResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cvv(card.getCvv())
                .cardType(card.getCardType())
                .ExpiryDate(card.getExpiryDate())
                .build();
    }
}
