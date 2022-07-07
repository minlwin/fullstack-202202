package com.jdc.query.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductSalePk implements Serializable{

	private static final long serialVersionUID = 1L;

	private int productId;
	private int saleId;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, saleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSalePk other = (ProductSalePk) obj;
		return productId == other.productId && saleId == other.saleId;
	}

}
