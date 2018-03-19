package ctrllayer;

import java.util.ArrayList;
import modlayer.*;

public class CustomerController {
	private ArrayList<Customer> customers;
	
	public CustomerController() {
		customers = new ArrayList<>();
		customers.add(new Customer(1, "Márton Juhász", "Lindholm Nærbanevej 3, 2/7", "9400", "Nørresundby"));
		customers.add(new Customer(2, "Santa Clause", "in your heart", "420", "Neverland"));
	}
	
	public Customer getCustomer(int customerId) {

		for (Customer customer : customers) {
			if (customer.getId() == customerId)
				return customer;
		}
		
		return null;
	}
}
