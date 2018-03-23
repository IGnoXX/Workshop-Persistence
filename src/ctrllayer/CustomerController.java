package ctrllayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import dblayer.DBCustomer;
import modlayer.*;

public class CustomerController {

	private ArrayList<Customer> customers;
	private DBCustomer dbCustomer;

	public CustomerController() {
		dbCustomer = new DBCustomer();
		customers = dbCustomer.getCustomers();
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public ArrayList<Customer> searchCustomers(String keyword) {
		ArrayList<Customer> results = new ArrayList<>();
		keyword = keyword.toLowerCase();
		
		for (Customer customer : customers) {
			if (customer.getName().toLowerCase().indexOf(keyword) > -1 ||
				customer.getEmail().toLowerCase().indexOf(keyword) > -1 ||
				customer.getPhone().toLowerCase().indexOf(keyword) > -1 ||
				Integer.toString(customer.getId()).indexOf(keyword) > -1) {
				results.add(customer);
			}
		}
		
		return results;
	}
	public Customer getCustomer(int customerId) {

		for (Customer customer : customers) {
			if (customer.getId() == customerId)
				return customer;
		}
		
		return null;
	}
	public boolean createCustomer(String name, String address, String zipcode, String city,  String country, String phone, String email, boolean isPrivate) {
		boolean success = false;
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setZipcode(zipcode);
		customer.setCity(city);
		customer.setCountry(country);
		customer.setPhone(phone);
		customer.setEmail(email);
		customer.setPrivate(isPrivate);
		
		int id = dbCustomer.insertCustomer(customer);
		if (id > 0) {
			success = true;
			
			customer.setId(id);
			customers.add(customer);
		}
		
		return success;
	}
	public boolean updateCustomer(Customer customer) {
		boolean success = dbCustomer.updateCustomer(customer);
		if (success) {
			customers.remove(getCustomer(customer.getId()));
			customers.add(customer);
		}
		
		return success;
	}
	public boolean deleteCustomer(Customer customer) {
		boolean success = dbCustomer.deleteCustomer(customer);
		if (success) {
			customers.remove(customer);
		}
		
		return success;
	}
}
