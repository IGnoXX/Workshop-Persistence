package ctrllayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import dblayer.DBCustomer;
import dblayer.DBProduct;
import modlayer.*;

public class CustomerController {
	private ArrayList<Customer> customers;
//	private static int customerIDs = 1; // replace this with auto db increment
	private DBCustomer dbCustomer;

	public CustomerController() {
//		customers = new ArrayList<>();
//		customers.add(new Customer(customerIDs, "M�rton Juh�sz", "Lindholm N�rbanevej 3, 2/7", "9400", "N�rresundby", "Denmark", "+45 654 463 34", "marton@mail.com", false)); customerIDs++;
//		customers.add(new Customer(customerIDs, "Santa Clause", "in your heart", "420", "Neverland", "Neverlandland", "+45 654 463 34", "santa@mail.com", true)); customerIDs++;
		dbCustomer = new DBCustomer();
		customers = dbCustomer.getCustomers();
		}
	//
	public Customer getCustomer(int customerId) {

		for (Customer customer : customers) {
			if (customer.getId() == customerId)
				return customer;
		}
		return null;
	}
	
	public void deleteCustomer(int customerId) {
		if (dbCustomer.deleteCustomer(new Customer(customerId)))
			System.out.println("Customer deleted!" + customers.size());
		customers = dbCustomer.getCustomers();

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
	
	//
	public boolean createCustomer(String name, String address, String zipcode, String city,  String country, String phone, String email, boolean isPrivate) {
		try {
			Customer customer = new Customer();
			customer.setName(name);
			customer.setAddress(address);
			customer.setZipcode(zipcode);
			customer.setCity(city);
			customer.setCountry(country);
			customer.setPhone(phone);
			customer.setEmail(email);
			customer.setPrivate(isPrivate);
			if (dbCustomer.insertCustomer(customer) >= 0)
				System.out.println("Customer created!" + customers.size());
			customers = dbCustomer.getCustomers();

			return true;
		}
		catch (Exception e) {
			System.out.println("Error in createCustomer(), can't create new customer");
			return false;
		}
		
	}
}
