package com.example.ApniDukan.DTOs.requestDto.card;

import com.example.ApniDukan.enums.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String customerMobile;
    String cardNo;
    int cvv;
    Date expiryDate;
    CardType cardType;
}
