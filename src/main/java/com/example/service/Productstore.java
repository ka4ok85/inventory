package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Store;
import com.example.repository.ProductRepository;
import com.example.repository.StoreRepository;

@ComponentScan
@Service
public class Productstore {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Productstore() {
    }

    public Product scanProduct(Long productId, Long storeId, Long quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        product.addToStore(store, quantity);
        productRepository.save(product);

        return product;
    }

    public Product sellProduct(Long productId, Long storeId, Long quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        product.removeFromStore(store, quantity);
        productRepository.save(product);

        return product;
    }
}
