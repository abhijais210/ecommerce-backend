package com.example.ApniDukan.DTOs.requestDto.item;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {
    Integer productId;
    Integer customerId;
    Integer quantity;
}
