package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Persistable;

import com.example.repository.ProductlocationRepository;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "products")
public class Product implements Persistable<Long> {

    @Autowired
    @Transient
    private ProductlocationRepository productlocationRepository;

    private static final long serialVersionUID = 7019159189994722047L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonView(com.example.entity.Product.class)
    @Column(name = "name", unique = false, nullable = false, length = 255)
    private String name;

    @JsonView(com.example.entity.Product.class)
    @Column(name = "sku", unique = false, nullable = false, length = 255)
    private String sku;

    @JsonView(com.example.entity.Product.class)
    @Column(name = "quantity", unique = false, nullable = false)
    private Long quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Productinstore> productinstores = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Productlocation> productlocationes = new HashSet<>();

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Productinstore> getProductinstores() {
        return productinstores;
    }

    public void setProductinstores(Set<Productinstore> productinstores) {
        this.productinstores = productinstores;
    }

    public void addToStore(Store store, Long quantity) {
        Productinstore existedProductinstore = null;
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(store.getId()) == true) {
                existedProductinstore = productinstore;
                break;
            }
        }

        if (existedProductinstore == null) {
            Productinstore productinstore = new Productinstore(this, store, quantity);
            productinstores.add(productinstore);
        } else {
            existedProductinstore.setQuantity(existedProductinstore.getQuantity() + quantity);
            productinstores.add(existedProductinstore);
        }
    }

    public void removeFromStore(Store store, Long quantity) {
        Productinstore existedProductinstore = null;
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(store.getId()) == true) {
                existedProductinstore = productinstore;
                break;
            }
        }

        if (existedProductinstore == null) {
            // log error?
        } else {
            existedProductinstore.setQuantity(existedProductinstore.getQuantity() - quantity);
            productinstores.add(existedProductinstore);
        }
    }

    public Productinstore getSingleProductinstore(Store store) {
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(store.getId()) == true) {
                return productinstore;
            }
        }

        return null;
    }

    public Set<Productlocation> getProductlocationes() {
        return productlocationes;
    }

    public void setProductlocationes(Set<Productlocation> productlocationes) {
        this.productlocationes = productlocationes;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", sku=" + sku + ", quantity=" + quantity + "]";
    }

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public boolean isNew() {
        return null == getId();
    }

    public void addToLocation(Store store, Long quantity, Long shelf, Long slot) {
System.out.println(store);
        Productlocation existedProductlocation = productlocationRepository.findByStoreIdAndShelfAndSlot(store.getId(), shelf, slot);
System.out.println(existedProductlocation);
        
        //for (Productlocation productlocation : productlocationes) {
            //if (
                    //productlocation.getStorelocation().
        			//)
        	
        //}
/*
        Productinstore existedProductinstore = null;
        for (Productinstore productinstore : productinstores) {
            if (productinstore.getStore().getId().equals(store.getId()) == true) {
                existedProductinstore = productinstore;
                break;
            }
        }

        if (existedProductinstore == null) {
            Productinstore productinstore = new Productinstore(this, store, quantity);
            productinstores.add(productinstore);
        } else {
            existedProductinstore.setQuantity(existedProductinstore.getQuantity() + quantity);
            productinstores.add(existedProductinstore);
        } 
 */
    }

}
