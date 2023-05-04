package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.requestDto.customer.CustomerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.customer.CustomerResponse;
import com.example.ApniDukan.model.Customer;

public class CustomerTransformer {
    public static Customer requestToCustomer(CustomerRegisterRequest customerRegisterRequest){
        return Customer.builder()
                .age(customerRegisterRequest.getAge())
                .address(customerRegisterRequest.getAddress())
                .mobNo(customerRegisterRequest.getMobNo())
                .name(customerRegisterRequest.getName())
                .emailId(customerRegisterRequest.getEmailId())
                .build();
    }
    public static CustomerResponse customerToResponse(Customer customer){
        return CustomerResponse.builder()
                .age(customer.getAge())
                .email(customer.getEmailId())
                .name(customer.getName())
                .build();
    }
}
