package com.example.ApniDukan.DTOs.requestDto.seller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRegisterRequest {
    String name;
    String email;
    String mobile;
    int age;
}
