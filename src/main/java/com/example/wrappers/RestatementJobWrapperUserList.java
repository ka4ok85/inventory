package com.example.wrappers;

import com.example.entity.Restatementjob;
import com.fasterxml.jackson.annotation.JsonView;

public class RestatementJobWrapperUserList {

    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    private Long id;

    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    private String productName;

    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    private Long expectedQuantity;

    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    private String status;

    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    private String dateAdded;

    @JsonView(com.example.wrappers.RestatementJobWrapperUserList.class)
    private String dateProcessed;

    public RestatementJobWrapperUserList(Restatementjob restatementjob) {
        this.id = restatementjob.getId();
        this.productName = restatementjob.getProduct().getName();
        this.expectedQuantity = restatementjob.getExpectedQuantity();
        this.status = restatementjob.getStatus();
        this.dateAdded = restatementjob.getDateAdded();
        this.dateProcessed = restatementjob.getDateProcessed();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(String dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Override
    public String toString() {
        return "RestatementJobWrapperUserList [id=" + id + ", productName=" + productName + ", expectedQuantity="
                + expectedQuantity + ", status=" + status + ", dateAdded=" + dateAdded + ", dateProcessed="
                + dateProcessed + "]";
    }

}
