package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ctrllayer.OrderCreator;
import dblayer.DBOrder;
import modlayer.Order;

public class TestOrder {
	
	@Test
	public void testOrderCreation() {
		OrderCreator testOrder = new OrderCreator();
		
		assertTrue("Prodcut added",testOrder.addProduct(2));
		assertTrue("Prodcut added",testOrder.addProduct(1));
		assertTrue("Prodcut updated",testOrder.updateProduct(1,5));
		assertTrue("Prodcut removed",testOrder.removeProduct(2));
		System.out.println();
		assertEquals("Customer added",13,testOrder.selectCustomer(13).getId());
		assertTrue(testOrder.finalizeOrder());
		
		
		
		DBOrder orderDB = new DBOrder();
		ArrayList<Order> orders = new ArrayList<>();
		orders = orderDB.getOrders();
		
		
		boolean deleted = false;
		
		for(Order order : orders) {
			if(order.getCustomer().getId() == 13)
				deleted = orderDB.deleteOrder(order);
		}
		
		assertTrue("Order deleted",deleted);
	}
	
	@Test
	public void productNotFound() {
		OrderCreator testOrder = new OrderCreator();
		assertEquals("Customer added",13,testOrder.selectCustomer(13).getId());
		assertFalse("Prodcut not found",testOrder.addProduct(999));
	}
	
	@Test
	public void customerNotFound() {
		OrderCreator testOrder = new OrderCreator();
		assertTrue("Prodcut added",testOrder.addProduct(1));
		assertNull("Customer null",testOrder.selectCustomer(99));
	}
}
