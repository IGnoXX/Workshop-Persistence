package dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dblayer.interfaces.IFDBCustomer;
import modlayer.Customer;

public class DBCustomer implements IFDBCustomer {

	private Connection con;

	public DBCustomer() {
		con = DBConnection.getConnection();
	}

	@Override
	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<>();

		String query = "SELECT * FROM Customer";
		try {
			
			Statement st = con.createStatement();
			st.setQueryTimeout(5);
			
			Customer customer;
			ResultSet results = st.executeQuery(query);
			while (results.next()) {
				customer = buildCustomer(results);
				customers.add(customer);
			}
			st.close();
		} catch (SQLException e) {
			System.out.println("Customers were not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return customers;
	}
	@Override
	public ArrayList<Customer> searchCustomers(String keyword) {
		ArrayList<Customer> customers = new ArrayList<>();

		String query =
				  "SELECT * FROM Customer "
				+ "WHERE id LIKE '%?%' "
				+ "OR name LIKE '%?%' "
				+ "OR email LIKE '%?%' "
				+ "OR phone LIKE '%?%'";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setString(3, keyword);
			ps.setString(4, keyword);
			
			Customer customer;
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				customer = buildCustomer(results);
				customers.add(customer);
			}
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("Customers were not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return customers;
	}
	@Override
	public Customer selectCustomer(int customerId) {
		Customer customer = null;
		
		String query = "SELECT * FROM Customer WHERE id = ?";
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setInt(1, customerId);
			
			ResultSet results = ps.executeQuery();
			if (results.next()) {
				customer = buildCustomer(results);
			}
		} catch (SQLException e) {
			System.out.println("Customer was not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return customer;
	}
	@Override
	public int insertCustomer(Customer customer) {
		int id = -1;
		
		String query =
				  "INSERT INTO Customer "
				+ "(name, address, zip_code, city, country, phone, email, is_private) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setQueryTimeout(5);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getZipcode());
			ps.setString(4, customer.getCity());
			ps.setString(5, customer.getCountry());
			ps.setString(6,  customer.getPhone());
			ps.setString(7,  customer.getEmail());
			ps.setBoolean(8, customer.getIsPrivate());
			
			if (ps.executeUpdate() > 0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	            	id = generatedKeys.getInt(1);
		            customer.setId(id);
	            }
			}
			ps.close();
			
		}
		catch (SQLException e) {
			System.out.println("Customer was not inserted!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return id;
	}
	@Override
	public boolean updateCustomer(Customer customer) {
		boolean success = false;
		
		String query =
				    "UPDATE Customer "
				  + "SET name = ? "
				  + ",address = ? "
				  + ",zip_code = ? "
				  + ",city = ? "
				  + ",country = ? "
				  + ",phone = ? "
				  + ",email = ? "
				  + ",is_private = ? "
				  + "WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getAddress());
			ps.setString(3, customer.getZipcode());
			ps.setString(4, customer.getCity());
			ps.setString(5, customer.getCountry());
			ps.setString(6,  customer.getPhone());
			ps.setString(7,  customer.getEmail());
			ps.setBoolean(8, customer.getIsPrivate());
			ps.setInt(9, customer.getId());
			
			success = ps.executeUpdate() > 0;
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("Customer was not updated!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return success;
	}
	@Override
	public boolean deleteCustomer(Customer customer) {
		boolean success = false;

		String query = "DELETE FROM Customer WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setInt(1, customer.getId());
			
			success = ps.executeUpdate() > 0;
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("Customer stock was not decreased!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
			
		return success;
	}
	
	private Customer buildCustomer(ResultSet results) throws SQLException {
		Customer customer = null;
		
		try {
			
			customer = new Customer();
			customer.setId(results.getInt("id"));
			customer.setName(results.getString("name"));
			customer.setAddress(results.getString("address"));
			customer.setZipcode(results.getString("zip_code"));
			customer.setCity(results.getString("city"));
			customer.setCountry(results.getString("country"));
			customer.setPhone(results.getString("phone"));
			customer.setEmail(results.getString("email"));
			customer.setPrivate(results.getBoolean("is_private"));
		}
		catch (SQLException e) {
			System.out.println("Customer was not built!");
			System.out.println(e.getMessage());
			
			throw e;
		}
		
		return customer;
	}
}
