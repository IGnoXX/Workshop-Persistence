package dblayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modlayer.Customer;
import modlayer.Product;

public class DBCustomer implements IfDbCustomer {

	private Connection con;

	/** Creates a new instance of DBEmployee */
	public DBCustomer() {
		con = DbConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Customer> getCustomers() {
		return searchCustomers("");
	}

	@Override
	public ArrayList<Customer> searchCustomers(String keyword) {
		ResultSet results;
		ArrayList<Customer> list = new ArrayList<Customer>();

		String query = "SELECT * FROM Customer WHERE id LIKE '%" + keyword + "%' OR name LIKE '%" + keyword
				+ "%' OR email LIKE '%" + keyword + "%' OR phone LIKE '%" + keyword + "%'";

		try { // read the employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Customer customer = buildCustomer(results);
				list.add(customer);
			} // end while
			stmt.close();
		} // slut try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Customer selectCustomer(int customerId) {
		ResultSet results;
		Customer product = new Customer();

		String query = "SELECT * FROM Customer WHERE id = " + customerId;
		System.out.println(query);
		try { // read the employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				product = buildCustomer(results);
				// assocaition is to be build
				stmt.close();
			} else { // no employee was found
				product = null;
			}
		} // end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return product;
	}

	@Override
	public int insertCustomer(Customer customer) {
		int rc = -1;
		String query = "INSERT INTO product(name, address, zip_Code, city, country, phone, email, stock)  VALUES('"
				+ customer.getName() + "','" + customer.getAddress() + "','" + customer.getZipcode() + "','"
				+ customer.getCity() + "','" + customer.getCountry() + "','" + customer.getPhone() + "','"
				+ customer.getEmail() + "','" + (customer.isPrivate() ? "1" : "0") + "')";
		try { // insert new employee + dependent
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} // end try
		catch (SQLException ex) {
			System.out.println("product ikke oprettet");
		}
		return (rc);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// Product empObj = emp;
		int rc = -1;
		String query = "UPDATE Customer SET " + "name ='" + customer.getName() + "', " + "address ='"
				+ customer.getAddress() + "', " + "zip_Code ='" + customer.getZipcode() + "', " + "city ='"
				+ customer.getCity() + "', " + "country ='" + customer.getCountry() + "', " + "phone ='"
				+ customer.getPhone() + "', " + "email ='" + customer.getEmail() + "', " + "is_private ='"
				+ (customer.isPrivate() ? "1" : "0") + "', " + " WHERE id = '" + customer.getId() + "'";
		System.out.println("Update query:" + query);
		try { // update employee
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		} // slut try
		catch (Exception ex) {
			System.out.println("Update exception in employee db: " + ex);
		}

		return (rc >= 0) ? true : false;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		int rc = -1;

		String query = "DELETE FROM Customer WHERE id = '" + customer.getId() + "'";
		// System.out.println(query);
		try { // delete from employee
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} // slut try
		catch (Exception ex) {
			System.out.println("Delete exception in employee db: " + ex);
		}

		return (rc >= 0) ? true : false;
	}

	private Customer buildCustomer(ResultSet results) {
		Customer customer = new Customer();
		try { // the columns from the table employee are used
			customer.setId(results.getInt("id"));
			customer.setName(results.getString("name"));
			customer.setAddress(results.getString("address"));
			customer.setZipcode(results.getString("zip_code"));
			customer.setCity(results.getString("city"));
			customer.setCountry(results.getString("country"));
			customer.setPhone(results.getString("phone"));
			customer.setEmail(results.getString("email"));
			customer.setPrivate(results.getBoolean("is_private"));
		} catch (Exception e) {
			System.out.println("error in building the employee object");
		}
		return customer;
	}

}
