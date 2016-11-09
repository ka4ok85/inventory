package com.example.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Productlocation;
import com.example.entity.Restatementjob;
import com.example.entity.Store;
import com.example.entity.Storelocation;
import com.example.entity.Userstore;
import com.example.repository.ProductRepository;
import com.example.repository.StoreRepository;
import com.example.repository.StorelocationRepository;
import com.example.security.JwtAuthenticationBadLoginResponse;
import com.example.security.MemberServiceImpl;
import com.example.service.ProductService;
import com.example.service.ProductlocationService;
import com.example.service.Productstore;
import com.example.service.StorelocationService;
import com.example.wrappers.ProductFullInStoreWrapper;
import com.example.wrappers.ProductLocationChangeWrapper;
import com.example.wrappers.ProductWrapperShort;
import com.example.wrappers.RestatementJobWrapperAdd;
import com.example.wrappers.StorelocationQuantityWrapper;
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
    private StorelocationService storelocationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MemberServiceImpl userDetailsService;

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

    @RequestMapping(value = "/api/addProductToLocation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @Transactional
    public Boolean addProductToLocation(@RequestBody ProductLocationChangeWrapper productLocationChangeWrapper) {
    	Long storeId = Long.parseLong(userDetailsService.getStoreId());
        productstore.scanProduct(productLocationChangeWrapper.getProductId(), storeId, productLocationChangeWrapper.getQuantity());
        productService.scanProduct(productLocationChangeWrapper.getProductId(), productLocationChangeWrapper.getQuantity());
        productlocationService.addProduct(productLocationChangeWrapper.getProductId(), storeId, productLocationChangeWrapper.getQuantity(), productLocationChangeWrapper.getLocationId());
     	
        return true;
    }

    @RequestMapping(value = "/api/removeProductFromLocation", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @Transactional
    public Object removeProductFromLocation(@RequestBody ProductLocationChangeWrapper productLocationChangeWrapper) {
    	Long storeId = Long.parseLong(userDetailsService.getStoreId());
        
    	try {
        	productstore.sellProduct(productLocationChangeWrapper.getProductId(), storeId, productLocationChangeWrapper.getQuantity());
            productService.sellProduct(productLocationChangeWrapper.getProductId(), productLocationChangeWrapper.getQuantity());
            productlocationService.removeProduct(productLocationChangeWrapper.getProductId(), storeId, productLocationChangeWrapper.getQuantity(), productLocationChangeWrapper.getLocationId());
         	
            return true;			
		} catch (Exception e) {
			// should be generic message class
			JwtAuthenticationBadLoginResponse jwtAuthenticationBadLoginResponse = new JwtAuthenticationBadLoginResponse();
			jwtAuthenticationBadLoginResponse.setMessage(e.getMessage());
			System.out.println(jwtAuthenticationBadLoginResponse);
			return ResponseEntity.ok(jwtAuthenticationBadLoginResponse);
		}

    }
    
    @RequestMapping(value = "/api/findProductLocation/{productId}/{storeId}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productlocation.class)
    @Transactional
    public Productlocation[] findProductLocation(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId) {
        Productlocation[] productlocation = productlocationService.findProduct(productId, storeId);

        return productlocation;
    }

    
    @RequestMapping(value = "/api/getproductsbystore", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.wrappers.ProductWrapperShort.class)
    public List<ProductWrapperShort> getProductsByStoreId() {
        ArrayList<ProductWrapperShort> productList = new ArrayList<ProductWrapperShort>();

        Long storeId = Long.parseLong(userDetailsService.getStoreId());

        Iterable<Product> iterable = productService.getAllProductsByStore(storeId);
        for (Product product : iterable) {
            ProductWrapperShort productWrapperShort = new ProductWrapperShort(product);
            productList.add(productWrapperShort);
        }        

        return productList;
    }
    
    @RequestMapping(value = "/api/getProductDetails/{productId}", method = RequestMethod.GET, produces = "application/json")
    public ProductFullInStoreWrapper getProductDetails(@PathVariable("productId") Long productId) {
    	Long storeId = Long.parseLong(userDetailsService.getStoreId());

    	Product product = productService.findProduct(productId);
    	Productinstore productinstore = productstore.findProductinstore(productId, storeId);
    	Productlocation[] productlocations = productlocationService.findProduct(productId, storeId);
    	
    	ProductFullInStoreWrapper productFullInStoreWrapper = new ProductFullInStoreWrapper();
    	productFullInStoreWrapper.setProduct(new ProductWrapperShort(product));
    	productFullInStoreWrapper.setTotalQuantity(productinstore.getQuantity());
    	
    	ArrayList<StorelocationQuantityWrapper> storelocationQuantityWrappers = new ArrayList<StorelocationQuantityWrapper>();
    	StorelocationQuantityWrapper storelocationQuantityWrapper;
    	Storelocation storelocation;
    	for (Productlocation productlocation : productlocations) {
        	System.out.println(productlocation.getQuantity());
        	System.out.println(productlocation.getStorelocation());
        	storelocationQuantityWrapper = new StorelocationQuantityWrapper();
        	storelocationQuantityWrapper.setQuantity(productlocation.getQuantity());
        	// should be changed to single query instead of loop
        	storelocation = storelocationService.findStorelocation(productlocation.getStorelocation());
        	storelocationQuantityWrapper.setStorelocation(storelocation);
        	storelocationQuantityWrappers.add(storelocationQuantityWrapper);
		}
    	
    	productFullInStoreWrapper.setStorelocationQuantityWrappers(storelocationQuantityWrappers);

    	return productFullInStoreWrapper;
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
