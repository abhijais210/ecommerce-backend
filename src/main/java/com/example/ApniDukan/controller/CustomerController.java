package com.example.ApniDukan.controller;

import com.example.ApniDukan.DTOs.requestDto.customer.CustomerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.customer.CustomerResponse;
import com.example.ApniDukan.service.CustomerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("register")
    ResponseEntity registerCustomer(@RequestBody CustomerRegisterRequest customerRegisterRequest){
        try{
            CustomerResponse customerResponse = customerService.registerCustomer(customerRegisterRequest);
            return new ResponseEntity(customerResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
