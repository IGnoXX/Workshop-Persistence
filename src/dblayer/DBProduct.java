package dblayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modlayer.Customer;
import modlayer.Product;

public class DBProduct implements IfDbProduct {

	private Connection con;

	/** Creates a new instance of DBEmployee */
	public DBProduct() {
		con = DbConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Product> getProducts() {
		return searchProducts("");
	}

	@Override
	public ArrayList<Product> searchProducts(String keyword) {
		ResultSet results;
		ArrayList<Product> list = new ArrayList<Product>();

		String query = "SELECT * FROM Product WHERE id LIKE '%" + keyword + "%' OR name LIKE '%" + keyword + "%'";

		try { // read the employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Product product = buildProduct(results);
				list.add(product);
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
	public Product selectProduct(int productId) {
		ResultSet results;
		Product product = new Product();

		String query = "SELECT * FROM Product WHERE id = " + productId;
		System.out.println(query);
		try { // read the employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				product = buildProduct(results);
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
	public int insertProduct(Product product) {
		int rc = -1;
		String query = "INSERT INTO product(name, purchase_price, sales_price, rent_price, origin_country, description, stock)  VALUES('"
				+ product.getName() + "','" + product.getPurchasePrice() + "','" + product.getSalesPrice() + "','"
				+ product.getRentPrice() + "','" + product.getCountryOfOrigin() + "','" + product.getDesc() + "','"
				+ product.getStock() + "')";
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
	public boolean updateProduct(Product product) {
		// Product empObj = emp;
		int rc = -1;

		String query = "UPDATE product SET " + "name ='" + product.getName() + "', " + "purchase_price ='"
				+ product.getPurchasePrice() + "', " + "sales_price ='" + product.getSalesPrice() + "', "
				+ "rent_price ='" + product.getRentPrice() + "', " + "origin_country ='" + product.getCountryOfOrigin()
				+ "', " + "description ='" + product.getDesc() + "', " + "stock ='" + product.getStock() + "', "
				+ " WHERE id = '" + product.getId() + "'";
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
	public boolean deleteProduct(Product product) {
		int rc = -1;

		String query = "DELETE FROM Product WHERE id = '" + product.getId() + "'";
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

	private Product buildProduct(ResultSet results) {
		Product product = new Product();
		try { // the columns from the table employee are used
			product.setId(results.getInt("id"));
			product.setName(results.getString("name"));
			product.setPurchasePrice(results.getDouble("purchase_price"));
			product.setSalesPrice(results.getDouble("sales_price"));
			product.setRentPrice(results.getDouble("rent_price"));
			product.setCountryOfOrigin(results.getString("origin_country"));
			product.setDesc(results.getString("description"));
			product.setStock(results.getInt("stock"));
		} catch (Exception e) {
			System.out.println("error in building the employee object");
		}
		return product;
	}
}
