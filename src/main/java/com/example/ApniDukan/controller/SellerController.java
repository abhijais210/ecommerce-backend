package com.example.ApniDukan.controller;

import com.example.ApniDukan.DTOs.requestDto.seller.SellerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.seller.DeletedSellerResponse;
import com.example.ApniDukan.DTOs.responsedto.seller.SellerResponse;
import com.example.ApniDukan.service.SellerService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seller")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("register")
    ResponseEntity registerSeller(@RequestBody SellerRegisterRequest sellerRegisterRequest){

        try{
            SellerResponse sellerRegisterResponse = sellerService.registerSeller(sellerRegisterRequest);
            return new ResponseEntity(sellerRegisterResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("find_by_email")
    ResponseEntity findSellerByEmail(@RequestParam String emailId){
        try{
            SellerResponse sellerRegisterResponse = sellerService.findSellerByEmail(emailId);
            return new ResponseEntity(sellerRegisterResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("find_all")
    ResponseEntity findAllSeller() {
            List<SellerResponse> sellerRegisterResponses = sellerService.findAllSeller();
            return new ResponseEntity(sellerRegisterResponses,HttpStatus.OK);
    }
    @PutMapping("update_name_by_email")
    ResponseEntity updateName(@RequestParam String emailId,@RequestParam String newName){
        try{
            SellerResponse sellerRegisterResponse = sellerService.updateName(emailId,newName);
            return new ResponseEntity(sellerRegisterResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("delete_by_email")
    DeletedSellerResponse deleteByEmail(@RequestParam String emailId) throws Exception {
        DeletedSellerResponse deletedSellerResponse = sellerService.deleteByEmail(emailId);
        return deletedSellerResponse;
    }
    @GetMapping("find_all_lessthan_age")
    List<SellerResponse> findAllSellerLessThanAge(@RequestParam Integer age) {
        return sellerService.findAllSellerLessThanAge(age);
    }
}
