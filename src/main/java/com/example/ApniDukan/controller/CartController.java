package com.example.ApniDukan.controller;

import com.example.ApniDukan.DTOs.requestDto.cart.CheckOutCartRequestDto;
import com.example.ApniDukan.DTOs.requestDto.item.ItemRequestDto;
import com.example.ApniDukan.DTOs.responsedto.cart.CartResponseDto;
import com.example.ApniDukan.DTOs.responsedto.order.OrderResponseDto;
import com.example.ApniDukan.model.Item;
import com.example.ApniDukan.service.CartService;
import com.example.ApniDukan.service.ItemService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;
    @PostMapping("add")
    ResponseEntity addItemToCart(@RequestBody ItemRequestDto itemRequestDto){
        Item item;
        try{
            item = itemService.addItem(itemRequestDto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        //now we have save item now put in to cart
        return new ResponseEntity(cartService.saveItem(item),HttpStatus.OK);
    }
    @PostMapping("place")
    ResponseEntity checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto){
        try{
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkOutCartRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
        }
    }
}
