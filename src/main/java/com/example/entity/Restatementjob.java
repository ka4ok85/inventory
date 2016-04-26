package com.example.entity;

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
    @Column(name = "product_id")
    private Long product;

    @JsonView(com.example.entity.Restatementjob.class)
    //@Column(name = "store_id")
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name="store_location_id")
    private Long storelocation;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "expected_quantity", nullable = false)
    private Long expectedQuantity;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "status", nullable = false)
    private String status;

    @JsonView(com.example.entity.Restatementjob.class)
    @Column(name = "username", nullable = true)
    private String username;

    public Restatementjob() {
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
        return "Restatementjob [id=" + id + ", product=" + product + ", store=" + store.getId() + ", storelocation="
                + storelocation + ", expectedQuantity=" + expectedQuantity + ", status=" + status + ", username="
                + username + "]";
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
               Objects.equals(username, that.username) &&
               Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, store, storelocation, expectedQuantity, status, username);
    }
}
