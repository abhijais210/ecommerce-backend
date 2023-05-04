package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.order.OrderRequestDto;
import com.example.ApniDukan.DTOs.responsedto.order.OrderResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCardException;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.ProductNotFoundException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.model.Card;
import com.example.ApniDukan.model.Customer;
import com.example.ApniDukan.model.Ordered;

public interface OrderService {
    public Ordered placeOrder(Customer customer, Card card) throws ProductOutOfStockException;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws InvalidCustomerException, InvalidCardException, ProductNotFoundException, ProductOutOfStockException;
}
