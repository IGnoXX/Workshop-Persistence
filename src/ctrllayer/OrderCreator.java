package ctrllayer;

import java.util.ArrayList;
import modlayer.*;
import dblayer.*;

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
		
		order.setCustomer(customerCtrl.getCustomer(2));
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
	public ArrayList<OrderProduct> getOrderProducts() {
		return order.getOrderProducts();
	}
	
	private OrderProduct getOrderProduct(Product product) {
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			if (orderProduct.getProduct().equals(product)) {
				return orderProduct;
			}
		}
		
		return null;
	}
	public boolean addProduct(int productId) {
		Product product = productCtrl.getProduct(productId);
		if (product == null)
			return false;
		
		if (getOrderProduct(product) != null)
			return false;
		
		order.addOrderProduct(new OrderProduct(product, 1));
		
		calculatePrice();
		
		return true;
	}
	public boolean updateProduct(int productId, int amount) {
		Product product = productCtrl.getProduct(productId);
		if (product == null)
			return false;
		
		OrderProduct orderProduct = getOrderProduct(product);
		if (orderProduct == null)
			return false;
		
		if (amount < 1 || product.getStock() < amount)
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
		
		calculatePrice();
		
		return true;
	}
	private void calculatePrice() {
		double price = 0.0;
		double discount = 0.0;
		double deliveryPrice = OrderCreator.deliveryPrice;
		
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			price += orderProduct.getProduct().getSalesPrice() * orderProduct.getAmount();
		}
		
		if (order.getCustomer() != null) {
			if (order.getCustomer().isPrivate()) {
				if (price >= 2500.0) {
					deliveryPrice = 0.0;
				}
			} else if (price >= 1500.0){
				discount = price * (OrderCreator.clubDiscountPercentage / 100);
			}
		}
		
		order.setPrice(price);
		order.setDiscount(discount);
		order.setDeliveryPrice(deliveryPrice);
	}
	public Customer selectCustomer(int customerId) {
		Customer customer = customerCtrl.getCustomer(customerId);
		if (customer == null)
			return null;
		
		order.setCustomer(customer);
		
		calculatePrice();
		System.out.println(customer.isPrivate());
		
		return customer;
	}
	
	public boolean finalizeOrder() {
		if (order.getCustomer() == null)
			return false;
		if (order.getOrderProducts().size() == 0)
			return false;
		
		calculatePrice();
		
		int id = 0;
		DBOrder dbOrder = new DBOrder();
		if ((id = dbOrder.insertOrder(order)) < 1)
			return false;
		
		System.out.println("Order was created!");
		order.setId(id);
		return true;
	}
}
