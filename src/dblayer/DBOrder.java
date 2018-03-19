package dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

		String query = "SELECT * FROM [Order] LEFT JOIN Customer ON [Order].customer_id=customer.id where [Order].id LIKE '%%' OR Customer.name lIKE '%%' OR Customer.id LIKE '%%';";

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
		Order order = null;
		ResultSet results;
		PreparedStatement ps;
		
		String query = "SELECT * FROM [Order] WHERE id = ?";

		try {
			ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			
			ps.setInt(1, orderId);
			results = ps.executeQuery(query);

			if (results.next()) {
				
				order = buildOrder(results);
				ps.close();
				
				query = "SELECT product_id, amount FROM [Order_product] WHERE id = ?";
				try {
					ps = con.prepareStatement(query);
					ps.setQueryTimeout(5);
					results = ps.executeQuery(query);
					
					DBProduct dbp = new DBProduct();
					while (results.next()) {
						OrderProduct op = new OrderProduct();
						op.setProduct(dbp.selectProduct(results.getInt(1)));
						op.setAmount(results.getInt(2));
						order.addOrderProduct(op);
					}
				} catch (Exception e) {
					System.out.println("Query exception - select: " + e);
				}
			}
		} catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		
		return order;
	}

	@Override
	public int insertOrder(Order order) {
		String query =
				  "INSERT INTO [Order] "
				+ "(customer_id, price, discount, delivery_price) "
				+ "VALUES (?, ?, ?, ?)";
		
		try {
			con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setQueryTimeout(5);
			
			ps.setInt(1, order.getCustomer().getId());
			ps.setDouble(2, order.getPrice());
			ps.setDouble(3, order.getDiscount());
			ps.setDouble(4, order.getDeliveryPrice());
			
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            
            order.setId(generatedKeys.getInt(1));
			ps.close();
			
			//OrderProducts
			for (OrderProduct orderProduct : order.getOrderProducts()) {
				int productId = orderProduct.getProduct().getId();
				int amount = orderProduct.getAmount();
				
				query =   "INSERT INTO [Order_product] "
						+ "(product_id, order_id, amount) "
						+ "VALUES (?, ?, ?)";
				try {
					ps = con.prepareStatement(query);
					ps.setQueryTimeout(5);
					
					ps.setInt(1, productId);
					ps.setInt(2, order.getId());
					ps.setInt(3, amount);
					
					ps.executeUpdate();
					ps.close();
				}
				catch (SQLException e) {
					System.out.println("OrderProduct was not inserted!");
					
					throw e;
				}
				
				query =   "UPDATE [Product] "
						+ "SET stock = stock - ? "
						+ "WHERE (id = ?)";
				try {
					ps = con.prepareStatement(query);
					ps.setQueryTimeout(5);
					
					ps.setInt(1, amount);
					ps.setInt(2, productId);
					
					ps.executeUpdate();
					ps.close();
				}
				catch (SQLException e) {
					System.out.println("Product stock was not decreased!");
					
					throw e;
				}
			}
			
			con.commit();
			
			return order.getId();
		}
		catch (SQLException e) {
			System.out.println("Order was not inserted!");
			System.out.println(e.getMessage());
			System.out.println(query);
			
			try {
				con.rollback();
			} catch (SQLException ignored) {}
			
			return -1;
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
