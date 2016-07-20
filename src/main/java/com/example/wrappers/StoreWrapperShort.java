package com.example.wrappers;

import com.fasterxml.jackson.annotation.JsonView;

public class StoreWrapperShort {
    @JsonView(com.example.wrappers.StoreWrapperShort.class)
    private Long id;

    @JsonView(com.example.wrappers.StoreWrapperShort.class)
    private String name;

	public StoreWrapperShort(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "StoreWrapperShort [id=" + id + ", name=" + name + "]";
	}
    
    
}
