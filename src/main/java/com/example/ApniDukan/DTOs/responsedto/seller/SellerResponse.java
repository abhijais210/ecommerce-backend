package com.example.ApniDukan.DTOs.responsedto.seller;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerResponse {
    String name;
    String email;
}
