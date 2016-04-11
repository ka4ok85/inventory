package com.example.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "orphans")
public class Orphan implements Persistable<Long> {

    public static final String STATUS_NEW = "new";
    public static final String STATUS_COMPLETE = "complete";

    private static final long serialVersionUID = -2245681212129182950L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonView(com.example.entity.Orphan.class)
    @Column(name = "product_id")
    private Long product;

    @JsonView(com.example.entity.Orphan.class)
    @Column(name = "store_id")
    private Long store;

    @JsonView(com.example.entity.Orphan.class)
    @Column(name="restatement_job_id")
    private Long restatementjob;

    @JsonView(com.example.entity.Orphan.class)
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @JsonView(com.example.entity.Orphan.class)
    @Column(name = "status", nullable = false)
    private String status;

    public Orphan() {
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

    public Long getStore() {
        return store;
    }

    public void setStore(Long store) {
        this.store = store;
    }

    public Long getRestatementjob() {
        return restatementjob;
    }

    public void setRestatementjob(Long restatementjob) {
        this.restatementjob = restatementjob;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orphan [id=" + id + ", product=" + product + ", store=" + store + ", restatementjob=" + restatementjob
                + ", quantity=" + quantity + ", status=" + status + "]";
    }

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Orphan that = (Orphan) o;
        return Objects.equals(product, that.product) &&
               Objects.equals(quantity, that.quantity) &&
               Objects.equals(restatementjob, that.restatementjob) &&
               Objects.equals(status, that.status) &&
               Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, store, restatementjob, quantity, status);
    }
}
