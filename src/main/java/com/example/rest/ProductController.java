package com.example.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Productlocation;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.entity.Userstore;
import com.example.repository.ProductRepository;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;
import com.example.service.ProductService;
import com.example.service.ProductlocationService;
import com.example.service.Productstore;
import com.example.wrappers.ProductWrapperShort;
import com.example.wrappers.UserWrapperShort;
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
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StorelocationRepository storelocationRepository;

    @RequestMapping(value = "/api/sellProduct/{productId}/{storeId}/{quantity}/{shelf}/{slot}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productinstore.class)
    @Transactional
    public Boolean sellProduct(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("quantity") Long quantity, @PathVariable("shelf") Long shelf, @PathVariable("slot") Long slot) {
        productstore.sellProduct(productId, storeId, quantity);
        productService.sellProduct(productId, quantity);
        productlocationService.sellProduct(productId, storeId, quantity, shelf, slot);
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

    @RequestMapping(value = "/api/findProductLocation/{productId}/{storeId}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productlocation.class)
    @Transactional
    public Productlocation findProductLocation(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId) {
        Productlocation productlocation = productlocationService.findProduct(productId, storeId);

        return productlocation;
    }

    
    @RequestMapping(value = "/api/getproductsbystore/{storeId}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.wrappers.ProductWrapperShort.class)
    public List<ProductWrapperShort> getProductsByStoreId(@PathVariable("storeId") Long storeId) {
        ArrayList<ProductWrapperShort> productList = new ArrayList<ProductWrapperShort>();
        Iterable<Product> iterable = productService.getAllProductsByStore(storeId);
        for (Product product : iterable) {
            ProductWrapperShort productWrapperShort = new ProductWrapperShort(product);
            productList.add(productWrapperShort);
        }        

        return productList;
    }
    
    @RequestMapping(value = "/api/addData", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Storelocation.class)
    @Transactional
    public int addTestData() {

        Store store = storeRepository.findByName("Branch Store #3");

        int newProductsCount = 10;
        int newProductsCountAdded = 0;
        Product newProduct;
        String sku;
        String name;
        long quantity;
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < newProductsCount; i++) {
            sku = "sku_" + i;
            name = "product name" + i;
            newProduct = productService.addProduct(sku, name);
            quantity = i + 1;

            HashSet<Productinstore> productinstores = new HashSet<>();
            Productinstore productinstore = new Productinstore();
            productinstore.setProduct(newProduct.getId());
            productinstore.setStore(store.getId());
            productinstore.setQuantity(quantity);
            productinstores.add(productinstore);
            newProduct.setProductinstores(productinstores);


            
            productRepository.save(newProduct);
            //products.add(newProduct);
        }


        int newStorelocationsCount = 10;

        Long shelf;
        Long slot = (long) 1;
        String barcode;
        ArrayList<Storelocation> storelocationes = new ArrayList<>();
        for (int i = 0; i < newStorelocationsCount; i++) {
            shelf = (long) (i+1);
            barcode = "barcode" + store.getId() + "_" + shelf + "_" + slot;
            Storelocation storelocation = productlocationService.addStorelocation(store.getId(), shelf, slot, barcode);
            storelocationes.add(storelocation);
        }


        return newProductsCountAdded;

    }

}
