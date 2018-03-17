package modlayer;

import modlayer.*;

public class OrderProduct {
	
	private Product product;
	private int amount;

	public OrderProduct(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}
	public OrderProduct(Product product) {
		this(product, 1);
	}
	
	public Product getProduct() {
		return product;
	}

	public int getAmount() {
		return amount;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
