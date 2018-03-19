package dblayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modlayer.Customer;
import modlayer.Order;
import modlayer.OrderProduct;
import modlayer.Product;

public class DBOrder implements IfDbOrder {
	private Connection con;

	/** Creates a new instance of DBEmployee */
	public DBOrder() {
		con = DbConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Order> getOrders() {
		return searchOrders("");
	}

	@Override
	public ArrayList<Order> searchOrders(String keyword) {

		ResultSet results;
		// TODO: by what do we search orders?
		ArrayList<Order> list = new ArrayList<Order>();

		String query = "SELECT * FROM Order where id LIKE '%" + keyword + "%'";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Order o = buildOrder(results);
				list.add(o);
			}
			stmt.close();
			for (Order order : list) {
				query = "SELECT product_id, amount FROM Order_product where id=" + String.valueOf(order.getId());
				try {
					stmt = con.createStatement();
					stmt.setQueryTimeout(5);
					results = stmt.executeQuery(query);
					DBProduct dbp = new DBProduct();
					while (results.next()) {
						OrderProduct op = new OrderProduct();
						op.setProduct(dbp.selectProduct(results.getInt(1)));
						op.setAmount(results.getInt(2));
						order.addOrderProduct(op);
					}
				} catch (Exception e) {
					System.out.println("Query exception - select: " + e);
					e.printStackTrace();
				}

			}
		} // slut try
		catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
		// return null;
	}

	@Override
	public Order selectOrder(int orderId) {
		ResultSet results;
		Order order = new Order();

		String query = "SELECT * FROM Order where id=" + String.valueOf(orderId);

		try { // read the employee from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				order = buildOrder(results);
				stmt.close();
				query = "SELECT product_id, amount FROM Order_product where id=" + String.valueOf(order.getId());
				try {
					stmt = con.createStatement();
					stmt.setQueryTimeout(5);
					results = stmt.executeQuery(query);
					DBProduct dbp = new DBProduct();
					while (results.next()) {
						OrderProduct op = new OrderProduct();
						op.setProduct(dbp.selectProduct(results.getInt(1)));
						op.setAmount(results.getInt(2));
						order.addOrderProduct(op);
					} // end while
						// String super_ssn = empObj.getSupervisor().getSsn();
						// Employee superEmp = singleWhere(" ssn = '" + super_ssn + "'", false);
						// empObj.setSupervisor(superEmp);
						// System.out.println("Supervisor is seleceted");
						// // here the department has to be selected as well
				} catch (Exception e) {
					System.out.println("Query exception - select: " + e);
					e.printStackTrace();
				}
			} else {
				order = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return order;
	}

	@Override
	public boolean insertOrder(Order order) {
		// TODO remove getInvoice
		int rc = -1;
		int id = 0;
		String query = "INSERT INTO Order(customer_id, delivery_date, price) OUTPUT Inserted.ID VALUES('"
				+ order.getCustomer().getId() + "','" + order.getDeliveryDate() + "','" + "','" + order.getPrice()
				+ "')";
		try {
			// con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet results = stmt.executeQuery(query);
			if (results.next())
				id = results.getInt(1);
			stmt.close();
			if (id != 0) {
				for (OrderProduct op : order.getOrderProducts()) {
					query = "INSERT INTO Order_product(product_id, order_id, amount) VALUES('" + op.getProduct().getId()
							+ "','" + id + "','" + op.getAmount() + "')";
					try {
						stmt = con.createStatement();
						stmt.setQueryTimeout(5);
						rc = stmt.executeUpdate(query);

						stmt.close();
					} // end try
					catch (SQLException ex) {
						System.out.println("OrderProduct was not inserted");
						return false;
					}
				}
				return true;
			} else
				System.out.println("order id check failed");
			return false;
		} // end try
		catch (SQLException ex) {
			System.out.println("Order was not inserted");
			return false;
		}
	}

	@Override
	public boolean updateOrder(Order order) {
		//
		// Employee empObj = emp;
		int rc = -1;

		String query = "UPDATE order SET " + "customer_id ='" + order.getCustomer().getId() + "', "
				+ "delivery_status_id ='" + order.getDeliveryStatus() + "', " + "delivery_date ='"
				+ order.getDeliveryDate() + "', " + "price ='" + order.getPrice() + "' " + " WHERE id = '"
				+ order.getId() + "'";
		System.out.println("Update query:" + query);
		try { // update employee
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();

			query = "DELETE FROM Order_product WHERE order_id = '" + order.getId() + "'";
			// System.out.println(query);
			try { // delete from employee
				stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
				for (OrderProduct op : order.getOrderProducts()) {
//						op.setAmount(44);
					query = "INSERT INTO Order_product(product_id,order_id,amount) VALUES("+op.getProduct().getId()+","+order.getId()+"," +op.getAmount()+")";
					// System.out.println(query);
					try { // delete from employee
						stmt = con.createStatement();
						stmt.setQueryTimeout(5);
						rc = stmt.executeUpdate(query);
						stmt.close();
					} // slut try
					catch (Exception ex) {
						System.out.println("Delete exception in employee db: " + ex);
					}
				}
			} // slut try
			catch (Exception ex) {
				System.out.println("Delete exception in employee db: " + ex);
			}
		} catch (Exception ex) {
			System.out.println("Update exception in employee db: " + ex);
		}
		if (rc >= 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteOrder(Order order) {
		int rc = -1;
		String query = "DELETE FROM Order_product WHERE order_id = '" + order.getId() + "'";
		// System.out.println(query);
		
		try { // delete from employee
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
			query = "DELETE FROM Order WHERE id = '" + order.getId() + "'";
			// System.out.println(query);
			try { // delete from employee
				stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				rc = stmt.executeUpdate(query);
				stmt.close();
			} // slut try
			catch (Exception ex) {
				System.out.println("Delete exception in employee db: " + ex);
			}
		} // slut try
		catch (Exception ex) {
			System.out.println("Delete exception in employee db: " + ex);
		}

		return (rc >= 0) ? true : false;
	}

	private Order buildOrder(ResultSet results) {
		Order order = new Order();
		try { // the columns from the table employee are used
			order.setId(results.getInt("id"));
			order.setCustomer(new Customer(results.getInt("customer_id")));
			// TODO: date and invoice fields setters
			order.setDeliveryStatus(results.getInt("delivery_status_id"));
		} catch (Exception e) {
			System.out.println("error in building the employee object");
		}
		return order;
	}

	private void removeOrderProducts(int OrderID) {

	}
}
