package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.product.RegisterProductRequest;
import com.example.ApniDukan.DTOs.requestDto.product.UpdatePriceRequest;
import com.example.ApniDukan.DTOs.requestDto.product.UpdateQuantityRequest;
import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.DTOs.responsedto.product.DeletedProductResponse;
import com.example.ApniDukan.DTOs.responsedto.product.ProductResponse;
import com.example.ApniDukan.Exceptions.InvalidSellerException;
import com.example.ApniDukan.Exceptions.ProductAlreadyRegisteredException;
import com.example.ApniDukan.Exceptions.ProductNotFoundException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.enums.ProductStatus;
import com.example.ApniDukan.model.Item;
import com.example.ApniDukan.model.Product;
import com.example.ApniDukan.model.Seller;
import com.example.ApniDukan.repository.ProductRepository;
import com.example.ApniDukan.repository.SellerRepository;
import com.example.ApniDukan.service.ItemService;
import com.example.ApniDukan.service.ProductService;
import com.example.ApniDukan.transformer.ItemTransformer;
import com.example.ApniDukan.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ItemService itemService;
    @Override
    public ProductResponse addNewProduct(RegisterProductRequest registerProductRequest) throws ProductAlreadyRegisteredException, InvalidSellerException {
        //first check if seller exist with this seller Id
        Seller seller;
        try {
            seller = sellerRepository.findById(registerProductRequest.getSellerId()).get();
        }catch (Exception e){
            throw new InvalidSellerException();
        }
        //now, we search in DB if this product already exists or not for this seller
        List<Product> products = productRepository.findBySellerId(seller.getId());

        //now we will search name of the product in products
        for(Product p : products){
            if(p.getName().equals(registerProductRequest.getProductName()))
                throw new ProductAlreadyRegisteredException();
        }

        //since this product not register in DB we add this in DB
        //requestDTO to product entity using transformer layer
        Product product = ProductTransformer.requestToProduct(registerProductRequest);
        product.setSeller(seller);

        //update the list of products for seller
        seller.getProducts().add(product);
        sellerRepository.save(seller);

        //now generate response using transformer layer
        return ProductTransformer.productToResponse(product);
    }

    @Override
    public ProductResponse updateQuantity(UpdateQuantityRequest updateQuantityRequest) throws ProductNotFoundException, InvalidSellerException {
        //first check if seller exist with this seller Id
        Seller seller;
        try {
            seller = sellerRepository.findById(updateQuantityRequest.getSellerId()).get();
        }catch (Exception e){
            throw new InvalidSellerException();
        }
        //first we search this product already Exists or not in DB
        Product product;
        try{
            product = productRepository.findById(updateQuantityRequest.getProductId()).get();
        }catch (Exception e) {
            throw new ProductNotFoundException();
        }

        //now we have product exist in DB, update the Quantity of Product and save in DB
        product.setQuantity(product.getQuantity()+updateQuantityRequest.getQuantity());
        Product savedProduct = productRepository.save(product);

        //generate response
        return ProductTransformer.productToResponse(savedProduct);
    }

    @Override
    public ProductResponse updatePrice(UpdatePriceRequest updatePriceRequest) throws ProductNotFoundException {
        Product product;
        try {
            product = productRepository.findById(updatePriceRequest.getProductId()).get();
        }catch (Exception e){
            throw new ProductNotFoundException();
        }
        product.setPrice(updatePriceRequest.getNewPrice());
        Product savedProduct = productRepository.save(product);

        //now generate response
        return ProductTransformer.productToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> topFiveCostlyProduct() {
        //get all the products from the lists
        List<Product> products = productRepository.findAll();

        //now create a priority queue for storing the top five costly products
        PriorityQueue<Product> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.getPrice(),o2.getPrice()));

        for(Product product : products){
            if(pq.size() < 5){
                pq.add(product);
            }else if(pq.peek().getPrice() < product.getPrice()){
                pq.poll();
                pq.add(product);
            }
        }
        //now generate response
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product product : pq){
            productResponseList.add(ProductTransformer.productToResponse(product));
        }
        return productResponseList;
    }
    public List<ProductResponse> topFiveCheapestProduct() {
        //get all the products from the lists
        List<Product> products = productRepository.findAll();

        //now create a priority queue for storing the top five costly products
        PriorityQueue<Product> pq = new PriorityQueue<>(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.compare(o2.getPrice(), o1.getPrice());
            }
        });

        for(Product product : products){
            if(pq.size() < 5){
                pq.add(product);
            }else if(pq.peek().getPrice() > product.getPrice()){
                pq.poll();
                pq.add(product);
            }
        }
        //now generate response
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product product : pq){
            productResponseList.add(ProductTransformer.productToResponse(product));
        }
        return productResponseList;
    }

    @Override
    public List<ProductResponse> getProductsLessthanQuantity(Integer quantity) {
        List<Product> products = productRepository.getProductsLessthanQuantity(quantity);

        //now generate response
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : products){
            productResponseList.add(ProductTransformer.productToResponse(product));
        }
        return productResponseList;
    }

    @Override
    public DeletedProductResponse deleteById(Integer id) throws Exception {
        //validate product
        Product product;
        try {
            product = productRepository.findById(id).get();
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }

        //now remove item associated with this product and generate deleted item response
        List<Item> itemList = product.getItemList();
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();

        for (Item item : itemList) {
            itemResponseDtoList.add(itemService.deleteById(item.getId()));
        }

        //now delete this product from db
        product.getSeller().getProducts().remove(product);
        productRepository.deleteById(id);

        //now generate the deleted product response
        DeletedProductResponse deletedProductResponse = ProductTransformer.productToDeletedResponse(product);
        deletedProductResponse.setItemResponseList(itemResponseDtoList);

        return deletedProductResponse;
    }
    public void decreaseProductQuantity(Item item) throws ProductOutOfStockException {
        int currentQuantity = item.getProduct().getQuantity();
        int requiredQuantity = item.getRequiredQuantity();

        if(requiredQuantity > currentQuantity)
            throw new ProductOutOfStockException();

        item.getProduct().setQuantity(currentQuantity-requiredQuantity);

        if(item.getProduct().getQuantity() == 0)
            item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
    }
}
