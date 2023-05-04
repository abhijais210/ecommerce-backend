package com.example.ApniDukan.DTOs.responsedto.product;

import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeletedProductResponse {
    String productName;
    String sellerName;
    List<ItemResponseDto> ItemResponseList;
}
