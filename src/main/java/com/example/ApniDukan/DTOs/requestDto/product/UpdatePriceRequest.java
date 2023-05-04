package com.example.ApniDukan.DTOs.requestDto.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePriceRequest {
    Integer sellerId;
    Integer productId;
    Integer newPrice;
}
