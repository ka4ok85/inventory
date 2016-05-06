package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "stores")
public class Store implements Persistable<Long> {

    private static final long serialVersionUID = -3349982366942999969L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @JsonView(com.example.entity.Store.class)
    @Column(name = "name", unique = false, nullable = false, length = 255)
    private String name;

    @JsonView(com.example.entity.Store.class)
    @Column(name = "address", unique = false, nullable = false, length = 255)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="store_id")
    private Set<Productinstore> productinstores = new HashSet<Productinstore>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="store_id")
    private Set<Storelocation> storelocationes = new HashSet<Storelocation>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="store_id")
    private Set<Userstore> userstores = new HashSet<Userstore>();

    public Store() {
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

    public Set<Storelocation> getStorelocationes() {
        return storelocationes;
    }

    public void setStorelocationes(Set<Storelocation> storelocationes) {
        this.storelocationes = storelocationes;
    }

    public Set<Userstore> getUserstores() {
        return userstores;
    }

    public void setUserstores(Set<Userstore> userstores) {
        this.userstores = userstores;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store [name=" + name + ", address=" + address + "]";
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
}
