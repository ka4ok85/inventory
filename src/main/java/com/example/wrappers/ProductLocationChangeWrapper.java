package com.example.wrappers;

import com.fasterxml.jackson.annotation.JsonView;

public class ProductLocationChangeWrapper {

    @JsonView(com.example.wrappers.ProductLocationChangeWrapper.class)
    private Long productId;

    @JsonView(com.example.wrappers.ProductLocationChangeWrapper.class)
    private Long locationId;
    
    @JsonView(com.example.wrappers.ProductLocationChangeWrapper.class)
    private Long quantity;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ProductLocationChangeWrapper [productId=" + productId + ", locationId=" + locationId + ", quantity="
				+ quantity + "]";
	}
    
    
}
