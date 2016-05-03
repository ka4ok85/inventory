package com.example.wrappers;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.entity.Restatementjob;
import com.example.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

public class RestatementjobWrapperFull {

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private Long id;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private String productName;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private String storeName;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private Long storeId;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private Long storelocationShelf;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private Long storelocationSlot;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private Long expectedQuantity;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private String status;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    private String userLogin;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date dateAdded;

    @JsonView(com.example.wrappers.RestatementjobWrapperFull.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date dateProcessed;

    public RestatementjobWrapperFull(Restatementjob restatementjob) {
        this.id = restatementjob.getId();
        this.productName = restatementjob.getProduct().getName();
        this.storeName = restatementjob.getStore().getName();
        this.storeId = restatementjob.getStore().getId();
        this.storelocationShelf = restatementjob.getStorelocation().getShelf();
        this.storelocationSlot = restatementjob.getStorelocation().getSlot();
        this.expectedQuantity = restatementjob.getExpectedQuantity();
        this.status = restatementjob.getStatus();
        this.userLogin = restatementjob.getUser().getLogin();
        this.dateAdded = restatementjob.getDateAdded();
        this.dateProcessed = restatementjob.getDateProcessed();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProduct(String productName) {
        this.productName = productName;
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

    public Long getStorelocationShelf() {
        return storelocationShelf;
    }

    public void setStorelocationShelf(Long storelocationShelf) {
        this.storelocationShelf = storelocationShelf;
    }

    public Long getStorelocationSlot() {
        return storelocationSlot;
    }

    public void setStorelocationSlot(Long storelocationSlot) {
        this.storelocationSlot = storelocationSlot;
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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Override
    public String toString() {
        return "RestatementjobWrapper [id=" + id + ", productName=" + productName + ", storeName=" + storeName + ", storeId="
                + storeId + ", storelocationShelf=" + storelocationShelf + ", storelocationSlot=" + storelocationSlot
                + ", expectedQuantity=" + expectedQuantity + ", status="
                + status + ", userLogin=" + userLogin
                + ", dateAdded=" + dateAdded + ", dateProcessed=" + dateProcessed
                + "]";
    }

}
