package com.example.ApniDukan.DTOs.responsedto.item;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {
    String ProductName;
    int requiredQuantity;
    int priceOfOneProduct;
    int totalPrice;
}
