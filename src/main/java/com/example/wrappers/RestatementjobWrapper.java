package com.example.wrappers;


import com.example.entity.Restatementjob;
import com.fasterxml.jackson.annotation.JsonView;

public class RestatementjobWrapper {

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private Long id;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private Long product;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private String storeName;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private Long storeId;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private Long storelocation;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private Long expectedQuantity;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private String status;

    @JsonView(com.example.wrappers.RestatementjobWrapper.class)
    private String username;


    public RestatementjobWrapper(Restatementjob restatementjob) {
        this.id = restatementjob.getId();
        this.product = restatementjob.getProduct();
        this.storeName = restatementjob.getStore().getName();
        this.storeId = restatementjob.getStore().getId();
        this.storelocation = restatementjob.getStorelocation();
        this.expectedQuantity = restatementjob.getExpectedQuantity();
        this.status = restatementjob.getStatus();
        this.username = restatementjob.getUsername();
	}

	public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStorelocation() {
        return storelocation;
    }

    public void setStorelocation(Long storelocation) {
        this.storelocation = storelocation;
    }

    public Long getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(Long expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "RestatementjobWrapper [id=" + id + ", product=" + product + ", storeName=" + storeName + ", storeId="
                + storeId + ", storelocation=" + storelocation + ", expectedQuantity=" + expectedQuantity + ", status="
                + status + ", username=" + username + "]";
    }

}
