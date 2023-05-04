package com.example.ApniDukan.transformer;

import com.example.ApniDukan.DTOs.requestDto.seller.SellerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.seller.SellerResponse;
import com.example.ApniDukan.model.Seller;
public class SellerTransformer {

    //created object using @Builder
    public static Seller requestToSeller(SellerRegisterRequest sellerRegisterRequest){
        return Seller.builder()
                .age(sellerRegisterRequest.getAge())
                .emailId(sellerRegisterRequest.getEmail())
                .name(sellerRegisterRequest.getName())
                .mobNo(sellerRegisterRequest.getMobile())
                .build();
    }
    public static SellerResponse sellerToResponse(Seller seller){
        return SellerResponse.builder()
                .name(seller.getName())
                .email(seller.getEmailId())
                .build();
    }
}
