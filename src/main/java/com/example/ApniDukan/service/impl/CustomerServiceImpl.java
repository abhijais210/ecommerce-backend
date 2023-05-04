package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.customer.CustomerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.customer.CustomerResponse;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.InvalidEmailException;
import com.example.ApniDukan.model.Cart;
import com.example.ApniDukan.model.Customer;
import com.example.ApniDukan.repository.CustomerRepository;
import com.example.ApniDukan.service.CustomerService;
import com.example.ApniDukan.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse registerCustomer(CustomerRegisterRequest customerRegisterRequest) throws InvalidCustomerException, InvalidEmailException {
        //first we validate if customer mobNo and email already exists
        if(customerRepository.findByMobNo(customerRegisterRequest.getMobNo()) != null)
            throw new InvalidCustomerException("Mobile Number already in use! enter another");

        if(customerRepository.findByEmailId(customerRegisterRequest.getEmailId()) != null)
            throw new InvalidEmailException("Email id already in use! enter another");

        //now we have a unique Customer, just add this to DB
        //generate customer from requestDTO
        Customer customer = CustomerTransformer.requestToCustomer(customerRegisterRequest);

        //now every time new user register in portal a cart already associate with that user
        Cart cart = Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();
        customer.setCart(cart);
        customerRepository.save(customer);

        //now generate response
        return CustomerTransformer.customerToResponse(customer);
    }
}
