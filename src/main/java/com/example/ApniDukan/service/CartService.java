package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.cart.CheckOutCartRequestDto;
import com.example.ApniDukan.DTOs.responsedto.cart.CartResponseDto;
import com.example.ApniDukan.DTOs.responsedto.order.OrderResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCardException;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.model.Item;

public interface CartService {
    public CartResponseDto saveItem(Item item);
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception;
}
