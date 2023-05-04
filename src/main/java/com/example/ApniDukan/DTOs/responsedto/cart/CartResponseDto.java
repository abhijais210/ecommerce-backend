package com.example.ApniDukan.DTOs.responsedto.cart;

import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    String customerName;
    int numberOfItem;
    int cartTotal;
    List<ItemResponseDto> itemList;
}
