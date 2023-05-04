package com.example.ApniDukan.controller;

import com.example.ApniDukan.DTOs.requestDto.product.RegisterProductRequest;
import com.example.ApniDukan.DTOs.requestDto.product.UpdatePriceRequest;
import com.example.ApniDukan.DTOs.requestDto.product.UpdateQuantityRequest;
import com.example.ApniDukan.DTOs.responsedto.product.DeletedProductResponse;
import com.example.ApniDukan.DTOs.responsedto.product.ProductResponse;
import com.example.ApniDukan.service.ProductService;
import com.sun.source.doctree.IndexTree;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("register")
    ResponseEntity addNewProduct(@RequestBody RegisterProductRequest registerProductRequest){
        try{
            ProductResponse productResponse = productService.addNewProduct(registerProductRequest);
            return new ResponseEntity(productResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("update_quantity")
    ResponseEntity updateQuantity(@RequestBody UpdateQuantityRequest updateQuantityRequest){
        try{
            ProductResponse productResponse = productService.updateQuantity(updateQuantityRequest);
            return new ResponseEntity(productResponse,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("update_price")
    ResponseEntity updatePrice(@RequestBody UpdatePriceRequest updatePriceRequest){
        try {
            ProductResponse productResponse = productService.updatePrice(updatePriceRequest);
            return new ResponseEntity(productResponse,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("top_five_costly")
    List<ProductResponse> topFiveCostlyProduct(){
        return productService.topFiveCostlyProduct();
    }
    @GetMapping("top_five_cheapest")
    List<ProductResponse> topFiveCheapestProduct(){
        return productService.topFiveCheapestProduct();
    }
    @GetMapping("get_products_lessthan_quantity")
    List<ProductResponse> getProductsLessthanQuantity(@RequestParam Integer quantity){
        return productService.getProductsLessthanQuantity(quantity);
    }
    @DeleteMapping("delete_by_id")
    ResponseEntity deleteById(@RequestParam Integer id){
        try{
            DeletedProductResponse deletedProductResponse = productService.deleteById(id);
            return new ResponseEntity(deletedProductResponse,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
