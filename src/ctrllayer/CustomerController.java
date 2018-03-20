package ctrllayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import modlayer.*;

public class CustomerController {
	private ArrayList<Customer> customers;
	private static int customerIDs = 1; // replace this with auto db increment
	
	public CustomerController() {
		customers = new ArrayList<>();
		customers.add(new Customer(customerIDs, "Márton Juhász", "Lindholm Nærbanevej 3, 2/7", "9400", "Nørresundby", "Denmark", "+45 654 463 34", "marton@mail.com", false)); customerIDs++;
		customers.add(new Customer(customerIDs, "Santa Clause", "in your heart", "420", "Neverland", "Neverlandland", "+45 654 463 34", "santa@mail.com", true)); customerIDs++;
	}
	
	public Customer getCustomer(int customerId) {

		for (Customer customer : customers) {
			if (customer.getId() == customerId)
				return customer;
		}
		
		return null;
	}
	
	public void deleteCustomer(int customerId) {
		for (Customer customer : customers) {
			if (customer.getId() == customerId) {
				customers.remove(customer);
				break;
			}
				
		}
	}
	
	public ArrayList<Object> getAllCustomers() {
		ArrayList<Object> customerList = new ArrayList<Object>();
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			Customer c = iterator.next();
			customerList.add(new Object[]{
					c.getId(),
					c.getName(),
					c.getAddress(),
					c.getZipcode(),
					c.getCity(),
					c.getCountry(),
					c.getPhone(),
					c.getEmail(),
					c.getIsPrivate()
			});
		}
		return customerList;
	}
	
	public boolean createCustomer(String name, String address, String zipcode, String city,  String country, String phone, String email, boolean isPrivate) {
		try {
			customers.add(new Customer(customerIDs, name, address, zipcode, city, country, phone, email, isPrivate)); customerIDs++;
			System.out.println("Customer created!" + customers.size());
			
			return true;
		}
		catch (Exception e) {
			System.out.println("Error in createCustomer(), can't create new customer");
			return false;
		}
	}
}
