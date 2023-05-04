package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.order.OrderRequestDto;
import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.DTOs.responsedto.order.OrderResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCardException;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.ProductNotFoundException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.model.*;
import com.example.ApniDukan.repository.CardRepository;
import com.example.ApniDukan.repository.CustomerRepository;
import com.example.ApniDukan.repository.OrderRepository;
import com.example.ApniDukan.repository.ProductRepository;
import com.example.ApniDukan.service.OrderService;
import com.example.ApniDukan.service.ProductService;
import com.example.ApniDukan.transformer.ItemTransformer;
import com.example.ApniDukan.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Ordered placeOrder(Customer customer, Card card) throws ProductOutOfStockException {
        Cart cart = customer.getCart();
        Ordered ordered = new Ordered();

        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));
        String maskedCardNo = generateMaskedCard(card.getCardNo());
        ordered.setCardUsed(maskedCardNo);
        ordered.setCustomer(customer);

        List<Item> orderedItems = new ArrayList<>();
        for(Item item : cart.getItemList()){
            //now decrease the items from the product and validate if product has required quantity or not
            try{
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }catch (Exception e){
                throw new ProductOutOfStockException();
            }
        }
        ordered.setItemList(orderedItems);
        //set the order for each item also
        for(Item item : orderedItems)
            item.setOrdered(ordered);

        ordered.setTotalValue(cart.getCartTotal());
        return ordered;
    }

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws InvalidCustomerException, InvalidCardException, ProductNotFoundException, ProductOutOfStockException {
        //validate customer
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("customer not registered in portal");
        }
        //validate product
        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new ProductNotFoundException();
        }

        //validate card details
        //validate CVV and if the customer belongs to this card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());

        if(card == null || orderRequestDto.getCvv() != card.getCvv() || card.getCustomer() != customer)
            throw new InvalidCardException();

        //now generate new item
        Item item = Item.builder()
                .name(product.getName())
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .product(product)
                .build();
        //validate if required quantity available or not
        try{
            productService.decreaseProductQuantity(item);
        }catch (Exception e){
            throw new ProductOutOfStockException();
        }
        //now generate order
        Ordered ordered  = new Ordered();
        ordered.getItemList().add(item);
        ordered.setOrderNo(String.valueOf(UUID.randomUUID()));
        ordered.setCardUsed(generateMaskedCard(card.getCardNo()));
        ordered.setTotalValue(item.getRequiredQuantity()*product.getPrice());
        ordered.setCustomer(customer);

        customer.getOrderedList().add(ordered);
        item.setOrdered(ordered);
        product.getItemList().add(item);

        Ordered savedOrder = orderRepository.save(ordered);

        //now generate order response
        OrderResponseDto orderResponseDto = OrderTransformer.orderToResponse(ordered);

        //now also generate item response for this order
        ItemResponseDto itemResponseDto = ItemTransformer.itemToResponse(item);
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        itemResponseDtoList.add(itemResponseDto);
        orderResponseDto.setItems(itemResponseDtoList);

        return orderResponseDto;
    }

    public String generateMaskedCard(String cardNo){
        StringBuilder maskedCardNo = new StringBuilder();
        int n = cardNo.length();
        maskedCardNo.append("x".repeat(Math.max(0, n - 4)));
        maskedCardNo.append(cardNo.substring(n-4));
        return maskedCardNo.toString();
    }

}
