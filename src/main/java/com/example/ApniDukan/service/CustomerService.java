package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.customer.CustomerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.customer.CustomerResponse;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.InvalidEmailException;

public interface CustomerService {
    public CustomerResponse registerCustomer(CustomerRegisterRequest customerRegisterRequest) throws InvalidCustomerException, InvalidEmailException;
}
