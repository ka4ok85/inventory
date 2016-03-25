package com.example.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "product_locations")

//@NamedQuery(name = "Productlocation.findByStoreIdAndShelfAndSlot",
    //query = "select pl from Productlocation pl where pl.id = ?1")

public class Productlocation implements Persistable<Long> {

    private static final long serialVersionUID = -2245681212129182940L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="store_location_id")
    private Storelocation storelocation;

    @JsonView(com.example.entity.Productlocation.class)
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public Productlocation() {
    }
/*
    public Productlocation(Product product, Storelocation storelocation, Long quantity) {
        this.product = product;
        this.storelocation = storelocation;
        this.quantity = quantity;
    }
*/
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
        return "Productlocation [quantity=" + quantity + "]";
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
        Productlocation that = (Productlocation) o;
        return Objects.equals(product, that.product) &&
               Objects.equals(quantity, that.quantity) &&
               Objects.equals(storelocation, that.storelocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, storelocation, quantity);
    }
}
