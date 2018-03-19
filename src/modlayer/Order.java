package modlayer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

	private int id;
	private Date purchaseDate;
	private Date deliveryDate;
	private int deliveryStatus;
	private Customer customer;
	private int paymentStatus;
	private LocalDate paymentDate;
	private double price;
	private double discount;
	private double deliveryPrice;
	private ArrayList<OrderProduct> orderProducts; 
	
	public Order() {	
		deliveryStatus = 0;
		paymentStatus = 0;
		paymentDate = null;
		price = 0.0;
		discount = 0.0;
		deliveryPrice = 0.0;
		orderProducts = new ArrayList<>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
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
	public int getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
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
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
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
