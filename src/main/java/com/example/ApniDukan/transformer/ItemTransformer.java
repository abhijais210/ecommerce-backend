package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.model.Item;

public class ItemTransformer {
    public static ItemResponseDto itemToResponse(Item item){
        return ItemResponseDto.builder()
                .ProductName(item.getProduct().getName())
                .priceOfOneProduct(item.getProduct().getPrice())
                .requiredQuantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }
}
