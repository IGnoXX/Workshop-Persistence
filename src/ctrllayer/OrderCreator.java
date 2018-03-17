package ctrllayer;

import modlayer.*;

public class OrderCreator {
	
	private static final double deliveryPrice = 40.0;
	private static final double clubDiscountPercentage = 10.0;
	private Order order;
	private Invoice invoice;
	private ProductController productCtrl;
	private CustomerController customerCtrl;
	
	public OrderCreator() {
		order = new Order();
		invoice = new Invoice(order);
		productCtrl = new ProductController();
		customerCtrl = new CustomerController();
	}
	
	public double getPrice() {
		return invoice.getPrice();
	}
	public double getDiscount() {
		return invoice.getDiscount();
	}
	public double getDeliveryPrice() {
		return invoice.getDeliveryPrice();
	}
	public double getFinalPrice() {
		return invoice.getPrice() + invoice.getDeliveryPrice() - invoice.getDiscount();
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
		
		order.addOrderProduct(new OrderProduct(order, product, amount));
		
		return true;
	}	
	public boolean removeProducts(int productId, int amount) {
		Product product = productCtrl.getProduct(productId);
		if (product == null)
			return false;
		
		OrderProduct orderProduct = getOrderProduct(product);
		if (orderProduct != null) {
			order.removeOrderProduct(orderProduct);
			int prevAmount = orderProduct.getAmount();
			
			if (prevAmount > amount) {
				order.addOrderProduct(new OrderProduct(order, product, prevAmount - amount));
			}
			return true;
		}
		
		return false;
	}
	private void calculatePrice() {
		double price = 0;
		double discount = 0;
		double deliveryPrice = 0;
		
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			price += orderProduct.getProduct().getSalesPrice() * orderProduct.getAmount();
		}
		
		//if (order.getCustomer().getType() != 0) {
		//	if(price < 2500.0) {
				deliveryPrice = OrderCreator.deliveryPrice;
		//}} else if (price >= 1500.0) {
		//	discount = price * (1 - (clubDiscount / 100);
		//}
		
		invoice.setPrice(price);
		invoice.setDiscount(discount);
		invoice.setDeliveryPrice(deliveryPrice);
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
