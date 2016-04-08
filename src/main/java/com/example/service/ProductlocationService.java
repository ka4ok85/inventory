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

        Storelocation existedStorelocation = storelocationRepository.findByStoreAndShelfAndSlot(store.getId(), shelf, slot);
        if (existedStorelocation == null) {
            throw new NotFoundException("There is no such store location");
        }

        System.out.println(existedStorelocation);
        Productlocation existedProductlocation = productlocationRepository.findByProductAndStorelocation(product.getId(), existedStorelocation.getId());
        if (existedProductlocation == null) {
            Productlocation productlocation = new Productlocation();
            productlocation.setProduct(product.getId());
            productlocation.setStorelocation(existedStorelocation.getId());
            productlocation.setStore(store.getId());
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

        Storelocation existedStorelocation = storelocationRepository.findByStoreAndShelfAndSlot(store.getId(), shelf, slot);
        if (existedStorelocation == null) {
            throw new NotFoundException("There is no such store location");
        }

        Productlocation existedProductlocation = productlocationRepository.findByProductAndStorelocation(product.getId(), existedStorelocation.getId());
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

    public Productlocation findProduct(Long productId, Long storeId) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Productlocation existedProductlocation = productlocationRepository.findByProductAndStore(product.getId(), store.getId());
        if (existedProductlocation == null) {
            throw new NotFoundException("There is no such product location");
        }

        System.out.println(existedProductlocation);
        return existedProductlocation;
        //Productlocation existedProductlocation = productlocationRepository.findByProductAndStorelocation(product.getId(), existedStorelocation.getId());
    }

    public Storelocation addStorelocation(Long store, Long shelf, Long slot, String barcode) {
        Storelocation storelocation = new Storelocation();
        storelocation.setStore(store);
        storelocation.setShelf(shelf);
        storelocation.setSlot(slot);
        storelocation.setBarcode(barcode);

        storelocationRepository.save(storelocation);

        return storelocation;
    }
}
