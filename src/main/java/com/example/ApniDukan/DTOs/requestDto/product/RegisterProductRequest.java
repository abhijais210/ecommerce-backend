package com.example.ApniDukan.DTOs.requestDto.product;

import com.example.ApniDukan.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterProductRequest {
    int sellerId;
    String productName;
    int price;
    int quantity;
    ProductCategory productCategory;
}
