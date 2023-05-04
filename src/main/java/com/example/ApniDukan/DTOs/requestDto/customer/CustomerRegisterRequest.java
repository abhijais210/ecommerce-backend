package com.example.ApniDukan.DTOs.requestDto.customer;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRegisterRequest {
    String name;
    int age;
    String mobNo;
    String emailId;
    String address;
}
