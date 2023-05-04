package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.item.ItemRequestDto;
import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.ProductNotFoundException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.model.Item;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, ProductOutOfStockException;
    public ItemResponseDto deleteById(Integer id) throws Exception;
}
