package com.example.ApniDukan.service;

import com.example.ApniDukan.DTOs.requestDto.product.RegisterProductRequest;
import com.example.ApniDukan.DTOs.requestDto.product.UpdatePriceRequest;
import com.example.ApniDukan.DTOs.requestDto.product.UpdateQuantityRequest;
import com.example.ApniDukan.DTOs.responsedto.product.DeletedProductResponse;
import com.example.ApniDukan.DTOs.responsedto.product.ProductResponse;
import com.example.ApniDukan.Exceptions.InvalidSellerException;
import com.example.ApniDukan.Exceptions.ProductAlreadyRegisteredException;
import com.example.ApniDukan.Exceptions.ProductNotFoundException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.model.Item;

import java.util.List;

public interface ProductService {
    public ProductResponse addNewProduct(RegisterProductRequest addProductRequest) throws ProductAlreadyRegisteredException, InvalidSellerException;
    public ProductResponse updateQuantity(UpdateQuantityRequest updateQuantityRequest) throws ProductNotFoundException, InvalidSellerException;
    public ProductResponse updatePrice(UpdatePriceRequest updatePriceRequest) throws ProductNotFoundException;
    public List<ProductResponse> topFiveCostlyProduct();
    public List<ProductResponse> topFiveCheapestProduct();
    public List<ProductResponse> getProductsLessthanQuantity(Integer quantity);
    public DeletedProductResponse deleteById(Integer id) throws Exception;
    public void decreaseProductQuantity(Item item) throws ProductOutOfStockException;
}
