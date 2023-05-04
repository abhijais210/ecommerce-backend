package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.seller.SellerRegisterRequest;
import com.example.ApniDukan.DTOs.responsedto.product.DeletedProductResponse;
import com.example.ApniDukan.DTOs.responsedto.seller.DeletedSellerResponse;
import com.example.ApniDukan.DTOs.responsedto.seller.SellerResponse;
import com.example.ApniDukan.Exceptions.InvalidEmailException;
import com.example.ApniDukan.model.Item;
import com.example.ApniDukan.model.Product;
import com.example.ApniDukan.model.Seller;
import com.example.ApniDukan.repository.ItemRepository;
import com.example.ApniDukan.repository.ProductRepository;
import com.example.ApniDukan.repository.SellerRepository;
import com.example.ApniDukan.service.ProductService;
import com.example.ApniDukan.service.SellerService;
import com.example.ApniDukan.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductService productService;
    @Override
    public SellerResponse registerSeller(SellerRegisterRequest sellerRegisterRequest) throws InvalidEmailException {
        //now first we validate if this user email already exist or not
        if(sellerRepository.findByEmailId(sellerRegisterRequest.getEmail()) != null)
            throw new InvalidEmailException();

        //now if this user email is Unique we add this to DB
        //now convert this DTO to entity (using @Builder) in Transformer Package
        Seller seller = SellerTransformer.requestToSeller(sellerRegisterRequest);
        sellerRepository.save(seller);

        //now generate response using transformer
        return SellerTransformer.sellerToResponse(seller);
    }

    @Override
    public SellerResponse findSellerByEmail(String emailId) throws InvalidEmailException {
        Seller seller = sellerRepository.findByEmailId(emailId);

        if(seller == null)
            throw new InvalidEmailException("No user found, enter a valid email!");

        //now we have seller exists with this email
        //now generate our response

        return SellerTransformer.sellerToResponse(seller);
    }

    @Override
    public List<SellerResponse> findAllSeller() {
        //find all the seller from the DB
        List<Seller> sellers = sellerRepository.findAll();

        //now generate response for every seller and store it in new list
        List<SellerResponse> sellerRegisterResponses = new ArrayList<>();

        for(Seller seller : sellers)
            sellerRegisterResponses.add(SellerTransformer.sellerToResponse(seller));

        return sellerRegisterResponses;
    }

    @Override
    public SellerResponse updateName(String emailId, String newName) throws InvalidEmailException {
        //validate email
        Seller seller = sellerRepository.findByEmailId(emailId);
        if(seller == null)
            throw new InvalidEmailException("No user found, enter a valid email!");

        //now we have valid seller
        seller.setName(newName);
        Seller updatedSeller = sellerRepository.save(seller);

        //generate updated response
        return SellerTransformer.sellerToResponse(updatedSeller);
    }

    @Override
    public DeletedSellerResponse deleteByEmail(String emailId) throws Exception {
        //validate if seller exists in DB with this emailId
        Seller seller = sellerRepository.findByEmailId(emailId);
        if(seller == null)
            throw new InvalidEmailException("Seller not registered with this email!");

        //we also remove all the products added by seller
        List<Product> productList = seller.getProducts();

        //now for each product we have list of items so remove item also from DB
        List<DeletedProductResponse> deletedProductResponseList = new ArrayList<>();
        for (Product p : productList) {
            deletedProductResponseList.add(productService.deleteById(p.getId()));
        }
        //now delete seller response
        sellerRepository.deleteById(seller.getId());

        //now generate deleted seller response
        return new DeletedSellerResponse(emailId,deletedProductResponseList);
    }

    @Override
    public List<SellerResponse> findAllSellerLessThanAge(Integer age){
        //now query the find sellers whose age less than age
        List<Seller> sellerList = sellerRepository.findAllSellerLessThanAge(age);

        //now generate response
        List<SellerResponse> sellerResponseList = new ArrayList<>();
        for (Seller s: sellerList) {
            sellerResponseList.add(SellerTransformer.sellerToResponse(s));
        }
        return sellerResponseList;
    }
}
