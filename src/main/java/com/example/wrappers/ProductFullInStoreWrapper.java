package com.example.wrappers;

import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonView;

public class ProductFullInStoreWrapper {

    private ProductWrapperShort product;

    @JsonView(com.example.wrappers.ProductFullInStoreWrapper.class)
    private Long totalQuantity;

    private ArrayList<StorelocationQuantityWrapper> storelocationQuantityWrappers;
    
	public ProductWrapperShort getProduct() {
		return product;
	}

	public void setProduct(ProductWrapperShort product) {
		this.product = product;
	}

	public Long getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public ArrayList<StorelocationQuantityWrapper> getStorelocationQuantityWrappers() {
		return storelocationQuantityWrappers;
	}

	public void setStorelocationQuantityWrappers(ArrayList<StorelocationQuantityWrapper> storelocationQuantityWrappers) {
		this.storelocationQuantityWrappers = storelocationQuantityWrappers;
	}

	@Override
	public String toString() {
		return "ProductFullInStoreWrapper [product=" + product + ", totalQuantity=" + totalQuantity
				+ ", storelocationQuantityWrappers=" + storelocationQuantityWrappers + "]";
	}



	

    
}
