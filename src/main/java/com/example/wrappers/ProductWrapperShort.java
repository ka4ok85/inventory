package com.example.wrappers;

import com.example.entity.Product;
import com.fasterxml.jackson.annotation.JsonView;

public class ProductWrapperShort {
    @JsonView(com.example.wrappers.ProductWrapperShort.class)
    private Long id;

    @JsonView(com.example.wrappers.ProductWrapperShort.class)
    private String name;
    
    @JsonView(com.example.wrappers.ProductWrapperShort.class)
    private String sku;

    public ProductWrapperShort(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.sku = product.getSku();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return "ProductWrapperShort [id=" + id + ", name=" + name + ", sku=" + sku + "]";
	}
    

}
