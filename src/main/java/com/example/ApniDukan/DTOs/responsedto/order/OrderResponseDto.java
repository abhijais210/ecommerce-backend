package com.example.ApniDukan.DTOs.responsedto.order;

import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.model.Customer;
import com.example.ApniDukan.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String customerName;
    String orderNo;
    int totalValue;
    Date orderDate;
    String cardUsed;
    List<ItemResponseDto> items;
}
