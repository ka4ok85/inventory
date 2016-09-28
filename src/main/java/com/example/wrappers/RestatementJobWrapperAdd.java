package com.example.wrappers;

import com.example.entity.Restatementjob;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestatementJobWrapperAdd {

	    @JsonView(com.example.wrappers.RestatementJobWrapperAdd.class)
	    private Long id;

	    @JsonView(com.example.wrappers.RestatementJobWrapperAdd.class)
	    private Long productId;

	    @JsonView(com.example.wrappers.RestatementJobWrapperAdd.class)
	    private Long storeId;

	    @JsonView(com.example.wrappers.RestatementJobWrapperAdd.class)
	    private Long userId;
	    
	    @JsonView(com.example.wrappers.RestatementJobWrapperAdd.class)
	    private Long expectedQuantity;

	    @JsonView(com.example.wrappers.RestatementJobWrapperAdd.class)
	    private Long storelocationId;

	    public RestatementJobWrapperAdd() {
	    	
	    }
	    
	    public RestatementJobWrapperAdd(Restatementjob restatementjob) {
	        this.id = restatementjob.getId();
	        this.productId = restatementjob.getProduct().getId();
	        this.expectedQuantity = restatementjob.getExpectedQuantity();
	        this.storeId = restatementjob.getStore().getId();
	        this.storelocationId = restatementjob.getStorelocation().getId();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public Long getStoreId() {
			return storeId;
		}

		public void setStoreId(Long storeId) {
			this.storeId = storeId;
		}

		public Long getExpectedQuantity() {
			return expectedQuantity;
		}

		public void setExpectedQuantity(Long expectedQuantity) {
			this.expectedQuantity = expectedQuantity;
		}

		public Long getStorelocationId() {
			return storelocationId;
		}

		public void setStorelocationId(Long storelocationId) {
			this.storelocationId = storelocationId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "RestatementJobWrapperAdd [id=" + id + ", productId=" + productId + ", storeId=" + storeId
					+ ", userId=" + userId + ", expectedQuantity=" + expectedQuantity + ", storelocationId="
					+ storelocationId + "]";
		}



	    
}
