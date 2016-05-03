package com.example.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "restatement_jobs")
public class Restatementjob implements Persistable<Long> {

    public static final String STATUS_NEW = "new";
    public static final String STATUS_COMPLETE = "complete";

    private static final long serialVersionUID = -2245681212129182950L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView(com.example.entity.Restatementjob.class)
    private Long id;

    @JsonView(com.example.entity.Restatementjob.class)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonView(com.example.entity.Restatementjob.class)
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonView(com.example.entity.Restatementjob.class)
    @ManyToOne
    @JoinColumn(name = "store_location_id")
    private Storelocation storelocation;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "expected_quantity", nullable = false)
    private Long expectedQuantity;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "status", nullable = false)
    private String status;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "date_added", nullable = false)
    private Date dateAdded;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "date_processed", nullable = false)
    private Date dateProcessed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(com.example.entity.Restatementjob.class)
    private User user;

    public Restatementjob() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Storelocation getStorelocation() {
        return storelocation;
    }

    public void setStorelocation(Storelocation storelocation) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Restatementjob [id=" + id + ", product=" + product + ", store=" + store.getId() + ", storelocation="
                + storelocation.getId() + ", expectedQuantity=" + expectedQuantity + ", status=" + status + ", user="
                + user.getId() + ", userLogin=" + user.getLogin() 
                + ", dateAdded=" + dateAdded
                + ", dateProcessed=" + dateProcessed + "]";
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
        Restatementjob that = (Restatementjob) o;
        return Objects.equals(product, that.product) &&
               Objects.equals(expectedQuantity, that.expectedQuantity) &&
               Objects.equals(storelocation, that.storelocation) &&
               Objects.equals(status, that.status) &&
               Objects.equals(user, that.user) &&
               Objects.equals(dateAdded, that.dateAdded) &&
               Objects.equals(dateProcessed, that.dateProcessed) &&
               Objects.equals(store, that.store);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(product, store, storelocation, expectedQuantity, status, user, dateAdded, dateProcessed);
    }
}
