package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.requestDto.product.RegisterProductRequest;
import com.example.ApniDukan.DTOs.responsedto.product.DeletedProductResponse;
import com.example.ApniDukan.DTOs.responsedto.product.ProductResponse;
import com.example.ApniDukan.enums.ProductStatus;
import com.example.ApniDukan.model.Product;
import lombok.NonNull;

public class ProductTransformer {
    public static Product requestToProduct(RegisterProductRequest registerProductRequest){
        return Product.builder()
                .productStatus(ProductStatus.AVAILABLE)
                .name(registerProductRequest.getProductName())
                .productCategory(registerProductRequest.getProductCategory())
                .price(registerProductRequest.getPrice())
                .quantity(registerProductRequest.getQuantity())
                .build();
    }
    public static ProductResponse productToResponse(Product product){
        return ProductResponse.builder()
                .productName(product.getName())
                .productStatus(product.getProductStatus())
                .quantity(product.getQuantity())
                .sellerId(product.getSeller().getId())
                .build();
    }
    public static DeletedProductResponse productToDeletedResponse(Product product){
        return DeletedProductResponse.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .build();
    }
}
