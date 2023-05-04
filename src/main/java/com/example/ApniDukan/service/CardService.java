package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.card.CardRequestDto;
import com.example.ApniDukan.DTOs.responsedto.card.CardResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.enums.CardType;

import java.util.Date;
import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
    public List<CardResponseDto> findCardByCardType(CardType cardType);
    public List<CardResponseDto> findCardsByDate(Date date,CardType cardType);
}
