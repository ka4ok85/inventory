package com.example.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Productlocation;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.repository.ProductRepository;
import com.example.repository.ProductlocationRepository;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;

@ComponentScan
@Service
public class ProductlocationService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductlocationRepository productlocationRepository;

    @Autowired
    private StorelocationRepository storelocationRepository;

    public ProductlocationService() {
    }

    public Product scanProduct(Long productId, Long storeId, Long quantity, Long shelf, Long slot) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Storelocation existedStorelocation = storelocationRepository.findByStoreAndShelfAndSlot(store, shelf, slot);
        if (existedStorelocation == null) {
            throw new NotFoundException("There is no such store location");
        }

        System.out.println(existedStorelocation);
        Productlocation existedProductlocation = productlocationRepository.findByProductAndStorelocation(product, existedStorelocation);
        if (existedProductlocation == null) {
            Productlocation productlocation = new Productlocation();
            productlocation.setProduct(product);
            productlocation.setStorelocation(existedStorelocation);
            productlocation.setQuantity(quantity);
            Set<Productlocation> productlocationes = product.getProductlocationes();
            productlocationes.add(productlocation);
        } else {
            existedProductlocation.setQuantity(existedProductlocation.getQuantity() + quantity);
        }

        productRepository.save(product);

        return product;
    }

    public Product sellProduct(Long productId, Long storeId, Long quantity, Long shelf, Long slot) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Storelocation existedStorelocation = storelocationRepository.findByStoreAndShelfAndSlot(store, shelf, slot);
        if (existedStorelocation == null) {
            throw new NotFoundException("There is no such store location");
        }

        Productlocation existedProductlocation = productlocationRepository.findByProductAndStorelocation(product, existedStorelocation);
        if (existedProductlocation == null) {
            throw new NotFoundException("Productlocation not found: " + productId.toString() + "-" + storeId.toString());
        } else {
            if (existedProductlocation.getQuantity() < quantity) {
                throw new NotFoundException("Not enough " + productId.toString());
            } else {
                existedProductlocation.setQuantity(existedProductlocation.getQuantity() - quantity);
            }
        }

        productRepository.save(product);

        return product;
    }

}
