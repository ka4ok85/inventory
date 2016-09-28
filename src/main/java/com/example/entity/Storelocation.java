package com.example.entity;

import java.util.HashSet;
import java.util.Objects;
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
@Table(name = "store_locations")
public class Storelocation implements Persistable<Long> {

    private static final long serialVersionUID = -2245681212129182930L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView(com.example.entity.Storelocation.class)
    private Long id;

    @JsonView(com.example.entity.Storelocation.class)
    @Column(name="store_id")
    private Long store;

    @JsonView(com.example.entity.Storelocation.class)
    @Column(name = "slot", nullable = false)
    private Long slot;

    @JsonView(com.example.entity.Storelocation.class)
    @Column(name = "shelf", nullable = false)
    private Long shelf;

    @JsonView(com.example.entity.Storelocation.class)
    @Column(name = "barcode", nullable = false)
    private String barcode;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="store_location_id")
    private Set<Productlocation> productlocationes = new HashSet<Productlocation>();

    public Storelocation() {
    }

    public Storelocation(Long store, Long slot, Long shelf, String barcode) {
        this.store = store;
        this.slot = slot;
        this.shelf = shelf;
        this.barcode = barcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getStore() {
        return store;
    }

    public void setStore(Long store) {
        this.store = store;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }

    public Long getShelf() {
        return shelf;
    }

    public void setShelf(Long shelf) {
        this.shelf = shelf;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Set<Productlocation> getProductlocationes() {
        return productlocationes;
    }

    public void setProductlocationes(Set<Productlocation> productlocationes) {
        this.productlocationes = productlocationes;
    }

    @Override
	public String toString() {
		return "Storelocation [id=" + id + ", store=" + store + ", slot=" + slot + ", shelf=" + shelf + ", barcode="
				+ barcode + "]";
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
        Storelocation that = (Storelocation) o;
        return Objects.equals(slot, that.slot) &&
               Objects.equals(shelf, that.shelf) &&
               Objects.equals(barcode, that.barcode) &&
               Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store, slot, shelf, barcode);
    }
}
