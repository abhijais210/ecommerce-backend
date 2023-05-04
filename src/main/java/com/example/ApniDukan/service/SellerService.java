package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.seller.SellerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.seller.DeletedSellerResponse;
import com.example.ApniDukan.DTOs.responsedto.seller.SellerResponse;
import com.example.ApniDukan.Exceptions.InvalidEmailException;

import java.util.List;

public interface SellerService {
    public SellerResponse registerSeller(SellerRegisterRequest sellerRegisterRequest) throws InvalidEmailException;
    public SellerResponse findSellerByEmail(String emailId) throws InvalidEmailException;
    public List<SellerResponse> findAllSeller();
    public SellerResponse updateName(String emailId, String newName) throws InvalidEmailException;
    public DeletedSellerResponse deleteByEmail(String emailId) throws Exception;
    public List<SellerResponse> findAllSellerLessThanAge(Integer age);
}
