package com.example.ApniDukan.DTOs.responsedto.card;

import com.example.ApniDukan.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String customerName;
    int cvv;
    CardType cardType;
    Date ExpiryDate;
}
