package modlayer;

import modlayer.*;

public class OrderProduct {
	
	private Product product;
	private Order order;
	private int amount;

	public OrderProduct(Order order, Product product, int amount) {
		this.order = order;
		this.product = product;
		this.amount = amount;
	}
	public OrderProduct(Order order, Product product) {
		this(order, product, 1);
	}
	
	public Product getProduct() {
		return product;
	}
	public Order getOrder() {
		return order;
	}
	public int getAmount() {
		return amount;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
