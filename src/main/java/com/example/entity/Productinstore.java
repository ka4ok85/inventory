package com.example.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "products_in_stores")
public class Productinstore implements Persistable<Long> {

    private static final long serialVersionUID = -2245681212129182920L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @JsonView(com.example.entity.Productinstore.class)
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public Productinstore() {
    }

    public Productinstore(Product product, Store store, Long quantity) {
        this.product = product;
        this.store = store;
        this.quantity = quantity;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Productinstore [quantity=" + quantity + "]";
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
        Productinstore that = (Productinstore) o;
        return Objects.equals(product, that.product) &&
               Objects.equals(quantity, that.quantity) &&
               Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, store, quantity);
    }
}
