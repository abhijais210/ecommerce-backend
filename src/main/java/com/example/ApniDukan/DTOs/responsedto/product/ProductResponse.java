package com.example.ApniDukan.DTOs.responsedto.product;

import com.example.ApniDukan.enums.ProductCategory;
import com.example.ApniDukan.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {
    String productName;
    int sellerId;
    int quantity;
    ProductStatus productStatus;
}
