package com.example.ApniDukan.controller;

import com.example.ApniDukan.DTOs.requestDto.card.CardRequestDto;
import com.example.ApniDukan.DTOs.responsedto.card.CardResponseDto;
import com.example.ApniDukan.enums.CardType;
import com.example.ApniDukan.service.CardService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("card")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("add")
    ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
    @GetMapping("find_cards_by_type")
    List<CardResponseDto> findCardByCardType(@RequestParam CardType cardType){
        return cardService.findCardByCardType(cardType);
    }
    @GetMapping("find_cards_by_type_and_expirydate_greterthan_date")
    List<CardResponseDto> findCardsByDate(@RequestParam Date date,@RequestParam CardType cardType){
        return cardService.findCardsByDate(date,cardType);
    }
}
