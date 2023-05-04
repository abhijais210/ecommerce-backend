package com.example.ApniDukan.DTOs.responsedto.seller;

import com.example.ApniDukan.DTOs.responsedto.product.DeletedProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeletedSellerResponse {
    String emailId;
    List<DeletedProductResponse> deletedProductResponseList;
}
