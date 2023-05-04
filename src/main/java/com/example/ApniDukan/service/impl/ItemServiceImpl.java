package com.example.ApniDukan.service.impl;

import com.example.ApniDukan.DTOs.requestDto.item.ItemRequestDto;
import com.example.ApniDukan.DTOs.responsedto.item.ItemResponseDto;
import com.example.ApniDukan.Exceptions.InvalidCustomerException;
import com.example.ApniDukan.Exceptions.ProductNotFoundException;
import com.example.ApniDukan.Exceptions.ProductOutOfStockException;
import com.example.ApniDukan.enums.ProductStatus;
import com.example.ApniDukan.model.Customer;
import com.example.ApniDukan.model.Item;
import com.example.ApniDukan.model.Product;
import com.example.ApniDukan.repository.CustomerRepository;
import com.example.ApniDukan.repository.ItemRepository;
import com.example.ApniDukan.repository.ProductRepository;
import com.example.ApniDukan.service.ItemService;
import com.example.ApniDukan.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, ProductNotFoundException, ProductOutOfStockException {
        //validate customer
        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("customer not exists in DB!");
        }

        //validate product
        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new ProductNotFoundException();
        }

        //check if we have enough quantity,
        if(itemRequestDto.getQuantity() > product.getQuantity() || product.getProductStatus()
                .equals(ProductStatus.OUT_OF_STOCK))
            throw new ProductOutOfStockException();

        //now generate item from
        Item item = Item.builder()
                .name(product.getName())
                .requiredQuantity(itemRequestDto.getQuantity())
                .product(product)
                .cart(customer.getCart())
                .build();
        product.getItemList().add(item);


        return item;
    }
    @Override
    public ItemResponseDto deleteById(Integer id) throws Exception {
        Item item;
        try {
            item = itemRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception("Item not exists in Db");
        }

        item.getProduct().getItemList().remove(item);
        itemRepository.deleteById(id);
        //generate deleted item response
        return ItemTransformer.itemToResponse(item);
    }
}
