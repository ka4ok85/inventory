package com.example.rest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.repository.ProductlocationRepository;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;
import com.example.service.ProductService;
import com.example.service.ProductlocationService;
//import com.example.entity.Productlocation;
//import com.example.service.Productlocationservice;
import com.example.service.Productstore;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ProductController {

    @Autowired
    private Productstore productstore;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductlocationService productlocationService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StorelocationRepository storelocationRepository;

    @Autowired
    private ProductlocationRepository productlocationRepository;

    @RequestMapping(value = "/api/sellProduct/{productId}/{storeId}/{quantity}/{shelf}/{slot}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productinstore.class)
    @Transactional
    public Boolean sellProduct(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("quantity") Long quantity, @PathVariable("shelf") Long shelf, @PathVariable("slot") Long slot) {
        //productlocationservice.sellProduct(productId, storeId, shelf, slot);
        productstore.sellProduct(productId, storeId, quantity);
        productService.sellProduct(productId, quantity);
        return true;
    }

    @RequestMapping(value = "/api/scanProduct/{productId}/{storeId}/{quantity}/{shelf}/{slot}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Product.class)
    @Transactional
    public Product scanProduct(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("quantity") Long quantity, @PathVariable("shelf") Long shelf, @PathVariable("slot") Long slot) {
        Product productAddedToStore = productstore.scanProduct(productId, storeId, quantity);
        productService.scanProduct(productId, quantity);
        productlocationService.scanProduct(productId, storeId, quantity, shelf, slot);

        return productAddedToStore;
    }


    @RequestMapping(value = "/api/test", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Store.class)
    @Transactional
    public Store test() {
        Store store = storeRepository.findByName("Main Store #1");

        return store;
    }


    @RequestMapping(value = "/api/test2", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Storelocation.class)
    @Transactional
    public Storelocation test2() {
        Store store = storeRepository.findByName("Main Store #1");
        Storelocation storelocation = storelocationRepository.findByStore(store);

        return storelocation;
    }

    
}
