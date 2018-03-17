package ctrllayer;

import java.util.ArrayList;
import modlayer.*;

public class CustomerController {
	private ArrayList<Customer> customers;
	
	public CustomerController() {
		customers = new ArrayList<>();
	}
	
	public Customer getCustomer(int customerId) {

		for (Customer customer : customers) {
			if (customer.getId() == customerId)
				return customer;
		}
		
		return null;
	}
}
