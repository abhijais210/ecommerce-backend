package com.example.ApniDukan.DTOs.requestDto.cart;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckOutCartRequestDto {
    int customerId;
    int cvv;
    String cardNo;
}
