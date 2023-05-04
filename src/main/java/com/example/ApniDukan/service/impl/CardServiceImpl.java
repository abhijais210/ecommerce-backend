package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.card.CardRequestDto;
import com.example.ApniDukan.DTOs.responsedto.card.CardResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.enums.CardType;
import com.example.ApniDukan.model.Card;
import com.example.ApniDukan.model.Customer;
import com.example.ApniDukan.repository.CardRepository;
import com.example.ApniDukan.repository.CustomerRepository;
import com.example.ApniDukan.service.CardService;
import com.example.ApniDukan.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        //validate if this customer is valid
        Customer customer = customerRepository.findByMobNo(cardRequestDto.getCustomerMobile());
        if(customer == null)
            throw new InvalidCustomerException("register first with this mobile number");

        //now first generate card from requestDto
        Card card = CardTransformer.requestToCard(cardRequestDto);
        card.setCustomer(customer);
        //set the card in customer list
        customer.getCardList().add(card);
        customerRepository.save(customer);

        //now generate response
        return CardTransformer.cardToResponseDto(card);
    }

    @Override
    public List<CardResponseDto> findCardByCardType(CardType cardType) {
        List<Card> cardList =  cardRepository.findByCardType(cardType);

        //generate response List
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card card : cardList){
            cardResponseDtoList.add(CardTransformer.cardToResponseDto(card));
        }
        return cardResponseDtoList;
    }

    @Override
    public List<CardResponseDto> findCardsByDate(Date date, CardType cardType) {
        List<Card> cardList = cardRepository.findCardsByExpiryDateGreaterThanDate(date,String.valueOf(cardType));

        //generate response List
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card card : cardList){
            cardResponseDtoList.add(CardTransformer.cardToResponseDto(card));
        }
        return cardResponseDtoList;
    }
}
