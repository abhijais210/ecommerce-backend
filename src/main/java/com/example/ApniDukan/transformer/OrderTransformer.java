package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.responsedto.order.OrderResponseDto;
import com.example.ApniDukan.model.Ordered;
import com.example.ApniDukan.repository.OrderRepository;

import java.security.PublicKey;

public class OrderTransformer {
    public static OrderResponseDto orderToResponse(Ordered ordered){
        return OrderResponseDto.builder()
                .customerName(ordered.getCustomer().getName())
                .orderNo(ordered.getOrderNo())
                .orderDate(ordered.getOrderDate())
                .cardUsed(ordered.getCardUsed())
                .totalValue(ordered.getTotalValue())
                .build();
    }
}
