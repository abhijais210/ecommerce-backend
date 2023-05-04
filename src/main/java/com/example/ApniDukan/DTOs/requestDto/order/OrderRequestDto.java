package com.example.ApniDukan.DTOs.requestDto.order;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {
    int customerId;
    int cvv;
    String cardNo;
    int productId;
    int requiredQuantity;
}
