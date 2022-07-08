package com.jdc.query.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "product_sale")
public class ProductSale implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductSalePk id;

	@ManyToOne
	@MapsId("productId")
	private Product product;
	@ManyToOne
	@MapsId("saleId")
	private Sale sale;

	private int quantity;

	public ProductSale() {
		id = new ProductSalePk();
	}

	public ProductSalePk getId() {
		return id;
	}

	public void setId(ProductSalePk id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.id.setProductId(product.getId());
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
		this.id.setSaleId(sale.getId());
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
