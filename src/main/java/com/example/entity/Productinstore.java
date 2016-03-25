package com.example.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonView;


@IdClass(Productinstore.class)
@Entity
@Table(name = "products_in_stores")
//public class Productinstore implements Persistable<Long> {
public class Productinstore implements Serializable {

    private static final long serialVersionUID = -2245681212129182920L;

    //@Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //private Long id;

    @Id
    @ManyToOne
    //@Column(name = "product_id", nullable = false)
    //@AttributeOverride(name="product", column=@Column(name="product_id"))
    @JoinColumn(name="product_id")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name="store_id")
    //@Column(name = "store_id", nullable = false)
    private Store store;

    @JsonView(com.example.entity.Productinstore.class)
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public Productinstore() {
    }


    public Productinstore(Product product, Store store) {
        this.product = product;
        this.store = store;
        this.quantity = new Long(0);
        System.out.println("CONSTRUCTOR");
    }

    public Productinstore(Product product, Store store, Long quantity) {
        this.product = product;
        this.store = store;
        this.quantity = quantity;
        System.out.println("NORMAL CONSTRUCTOR");
    }

    //public Long getId() {
        //return id;
    //}

    //public void setId(final Long id) {
        //this.id = id;
    //}

    @Column(name = "product_id")
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

    //@Override
    //@Transient
    //public boolean isNew() {
        //return null == getId();
    //}

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
               Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, store);
    }
}

/*

@Entity
@Table(name = "products_in_stores")
//public class Productinstore implements Persistable<Long> {
public class Productinstore implements Serializable {

    private static final long serialVersionUID = -2245681212129182920L;

    //@Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    //private Long id;
    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    //@Column(name = "product_id", nullable = false)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    //@Column(name = "store_id", nullable = false)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    @JsonView(com.example.entity.Productinstore.class)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Productinstore() {
    }


    public Productinstore(Product product, Store store) {
        this.product = product;
        this.store = store;
        this.quantity = 0;
    }


	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 5289143125641260396L;
		@Column(name = "product_id")
		private Long productId;
		@Column(name = "store_id")
		private Long storeId;
		
		public Id() {
			System.out.println("BLANK constructor");
		}
		
		public Id(Long productId, Long storeId) {
			System.out.println("KEYED constructor");
			this.productId = productId;
			this.storeId = storeId;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				Id that = (Id)obj;
				return this.productId.equals(that.productId) && this.storeId.equals(that.storeId);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return storeId.hashCode() + 
					productId.hashCode();
		}
		
		
	}
    
    
    
    public Id getId() {
        return id;
    }

    public void setId(Id id) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Productinstore [quantity=" + quantity + "]";
    }

    //@Override
    //@Transient
    //public boolean isNew() {
        //return null == getId();
    //}

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
               Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, store);
    }
    
}


*/