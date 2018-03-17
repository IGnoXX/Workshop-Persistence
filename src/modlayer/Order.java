package modlayer;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

	private int id;
	private LocalDate purchaseDate;
	private LocalDate deliveryDate;
	private int deliveryStatus;
	private Customer customer;
	private LocalDate paymentDate;
	private double price;
	private ArrayList<OrderProduct> orderProducts; 
	
	public Order() {	
		deliveryStatus = 0;
		orderProducts = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ArrayList<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	
	public void addOrderProduct(OrderProduct orderProduct) {
		orderProducts.add(orderProduct);
	}
	public void removeOrderProduct(OrderProduct orderProduct) {
		orderProducts.remove(orderProduct);
	}
}
