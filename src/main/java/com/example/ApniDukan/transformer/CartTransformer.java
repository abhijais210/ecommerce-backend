package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.responsedto.cart.CartResponseDto;
import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.model.Cart;
import com.example.ApniDukan.model.Item;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CartTransformer {
    public static void addItemtoCart(Item item){
        Cart cart = item.getCart();
        cart.setCartTotal(cart.getCartTotal()+(item.getRequiredQuantity()*item.getProduct().getPrice()));
        cart.getItemList().add(item);
        cart.setNumberOfItems(cart.getItemList().size());
    }
    public static CartResponseDto cartToResponse(Cart cart){
        List<Item> itemList = cart.getItemList();
        //now generate itemResponseList
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item : itemList){
            itemResponseDtos.add(ItemTransformer.itemToResponse(item));
        }
        //now generate cart response
        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .numberOfItem(cart.getNumberOfItems())
                .itemList(itemResponseDtos)
                .build();
    }
}
