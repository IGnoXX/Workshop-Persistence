package ctrllayer;

import modlayer.*;

public class OrderCreator {
	
	private static final double deliveryPrice = 40.0;
	private static final double clubDiscountPercentage = 10.0;
	private Order order;
	private ProductController productCtrl;
	private CustomerController customerCtrl;
	
	public OrderCreator() {
		order = new Order();
		productCtrl = new ProductController();
		customerCtrl = new CustomerController();
	}
	
	public double getPrice() {
		return order.getPrice();
	}
	public double getDiscount() {
		return order.getDiscount();
	}
	public double getDeliveryPrice() {
		return order.getDeliveryPrice();
	}
	public double getFinalPrice() {
		return order.getPrice() + order.getDeliveryPrice() - order.getDiscount();
	}
	
	private OrderProduct getOrderProduct(Product product) {
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			if (orderProduct.getProduct() == product) {
				return orderProduct;
			}
		}
		
		return null;
	}
	public boolean addProducts(int productId, int amount) {
		Product product = productCtrl.getProduct(productId);
		if (product == null)
			return false;
		
		OrderProduct orderProduct = getOrderProduct(product);
		if (orderProduct != null) {
			order.removeOrderProduct(orderProduct);
			amount += orderProduct.getAmount();
		}
		
		order.addOrderProduct(new OrderProduct(product, amount));
		
		calculatePrice();
		
		return true;
	}
	public boolean updateProducts(int productId, int amount) {
		Product product = productCtrl.getProduct(productId);
		if (product == null)
			return false;
		
		OrderProduct orderProduct = getOrderProduct(product);
		if (orderProduct == null)
			return false;
		
		order.removeOrderProduct(orderProduct);
		orderProduct.setAmount(amount);
		order.addOrderProduct(orderProduct);
		
		calculatePrice();
		
		return true;
	}
	public boolean removeProduct(int productId) {
		Product product = productCtrl.getProduct(productId);
		if (product == null)
			return false;
		
		OrderProduct orderProduct = getOrderProduct(product);
		if (orderProduct == null)
			return false;
		
		order.removeOrderProduct(orderProduct);
		
		return true;
	}
	private void calculatePrice() {
		double price = 0;
		double discount = 0;
		double deliveryPrice = 0;
		
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			price += orderProduct.getProduct().getSalesPrice() * orderProduct.getAmount();
		}
		
		if (order.getCustomer().isPrivate()) {
			if(price < 2500.0) {
				deliveryPrice = OrderCreator.deliveryPrice;
		}} else if (price >= 1500.0) {
			discount = price * (1 - (OrderCreator.clubDiscountPercentage / 100));
		}
		
		order.setPrice(price);
		order.setDiscount(discount);
		order.setDeliveryPrice(deliveryPrice);
	}
	public boolean selectCustomer(int customerId) {
		Customer customer = customerCtrl.getCustomer(customerId);
		if (customer == null)
			return false;
		
		order.setCustomer(customer);
		return true;
	}
	
	
	public boolean finalizeOrder() {
		if (order.getCustomer() == null)
			return false;
		if (order.getOrderProducts().size() == 0)
			return false;
		
		calculatePrice();
		
		//DB stuff
		
		return true;
	}
}
