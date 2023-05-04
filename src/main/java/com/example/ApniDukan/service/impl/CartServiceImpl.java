package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.cart.CheckOutCartRequestDto;
import com.example.ApniDukan.DTOs.responsedto.cart.CartResponseDto;
import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.DTOs.responsedto.order.OrderResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCardException;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.model.*;
import com.example.ApniDukan.repository.*;
import com.example.ApniDukan.service.CartService;
import com.example.ApniDukan.service.CustomerService;
import com.example.ApniDukan.service.OrderService;
import com.example.ApniDukan.transformer.CartTransformer;
import com.example.ApniDukan.transformer.ItemTransformer;
import com.example.ApniDukan.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public CartResponseDto saveItem(Item item) {
        //first get the cart
        CartTransformer.addItemtoCart(item);

        //now generate response Dto
        itemRepository.save(item);
        return CartTransformer.cartToResponse(item.getCart());
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws Exception {
        //validate customer
        Customer customer;
        try{
            customer = customerRepository.findById(checkOutCartRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("customer not registered in portal");
        }
        //validate card
        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());

        //now validate CVV and if the customer belongs to this card
        if(card == null || checkOutCartRequestDto.getCvv() != card.getCvv() || card.getCustomer() != customer)
            throw new InvalidCardException();
        //check if cart has item to order
        if(customer.getCart().getNumberOfItems() == 0)
            throw new Exception("Cart is empty!");

        //now place the order
        try{
            Ordered ordered = orderService.placeOrder(customer,card);
            customer.getOrderedList().add(ordered);
            orderRepository.save(ordered);

            //now generate order ResponseDto
            OrderResponseDto orderResponseDto = OrderTransformer.orderToResponse(ordered);
            //now generate item response list for order
            List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
            for(Item item : ordered.getItemList()){
                itemResponseDtoList.add(ItemTransformer.itemToResponse(item));
            }
            orderResponseDto.setItems(itemResponseDtoList);

            //now we will  send mail to customer regarding order the items
//            String text = "congrats kunal jinadal you have successfully ordered 5 items of cost"+ordered.getTotalValue();
//
//            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setFrom("LibraryManage210698@gmail.com");
//            simpleMailMessage.setTo(customer.getEmailId());//assumed this as mail
//            simpleMailMessage.setSubject("Return Book");
//            simpleMailMessage.setText(text);
//
//            javaMailSender.send(simpleMailMessage);
            return orderResponseDto;
        }catch (Exception e){
            throw new ProductOutOfStockException();
        }
    }
}
