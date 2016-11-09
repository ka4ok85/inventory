package com.example.wrappers;

import com.example.entity.Storelocation;
import com.fasterxml.jackson.annotation.JsonView;

public class StorelocationQuantityWrapper {

    private Storelocation storelocation;

    @JsonView(com.example.wrappers.StorelocationQuantityWrapper.class)
    private Long quantity;

	public Storelocation getStorelocation() {
		return storelocation;
	}

	public void setStorelocation(Storelocation storelocation) {
		this.storelocation = storelocation;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StorelocationQuantityWrapper [storelocation=" + storelocation.toString() + ", quantity=" + quantity + "]";
	}
    
}
