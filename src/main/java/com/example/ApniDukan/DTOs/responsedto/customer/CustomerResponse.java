package com.example.ApniDukan.DTOs.responsedto.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponse {
    String name;
    int age;
    String email;
}
