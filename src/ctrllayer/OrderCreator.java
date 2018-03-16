package ctrllayer;

import java.time.LocalDate;
import java.util.ArrayList;

import modlayer.*;

public class OrderCreator {
	
	private static final double deliveryPrice = 40.0;
	private static final double clubDiscount = 10.0;
	private Order order;
	
	public OrderCreator() {
		order = new Order();
	}
	
	public boolean addProducts(int productId, int amount) {
		int prevAmount = 0;
		
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			if (orderProduct.getProduct() == productId) {
				order.removeOrderProduct(orderProduct);
				prevAmount = orderProduct.getAmount();

				break;
			}
		}
		
		order.addOrderProduct(new OrderProduct(order, productId, prevAmount + amount));
		
		return true;
	}	
	public boolean removeProducts(int productId, int amount) {
		
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			if (orderProduct.getProduct() == productId) {
				order.removeOrderProduct(orderProduct);
				int prevAmount = orderProduct.getAmount();
				
				if (prevAmount > amount) {
					order.addOrderProduct(new OrderProduct(order, productId, prevAmount - amount));
				}
				return true;
			}
		}
		
		return false;
	}
	public double calculatePrice() {
		double price = 0;
		
		for (OrderProduct orderProduct : order.getOrderProducts()) {
			price += orderProduct.getProduct() * orderProduct.getAmount();
		}
		
		//if (order.getCustomer().getType() != 0) {
		//	if(price < 2500.0) {
				price += deliveryPrice;
		//}} else if (price >= 1500.0) {
		//	price *= 1 - (clubDiscount / 100);
		//}
		
		
		return price;
	}
	public void finalizeOrder() {
		Invoice invoice = order.getInvoice();
		
	}
}
