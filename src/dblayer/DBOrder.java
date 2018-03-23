package dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dblayer.interfaces.IFDBOrder;
import modlayer.Customer;
import modlayer.Order;
import modlayer.OrderProduct;
import modlayer.Product;

public class DBOrder implements IFDBOrder {
	
	private Connection con;

	public DBOrder() {
		con = DBConnection.getInstance().getConnection();
	}

	@Override
	public ArrayList<Order> getOrders() {
		ArrayList<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM [Order]";
		try {
			
			Statement st = con.createStatement();
			st.setQueryTimeout(5);
			
			ResultSet results = st.executeQuery(query);
			while (results.next()) {
				
				orders.add(buildOrder(results));
			}
		} catch (SQLException e) {
			System.out.println("Orders were not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return orders;
	}
	@Override
	public ArrayList<Order> searchOrders(String keyword) {
		ArrayList<Order> orders = new ArrayList<Order>();

		String query =
				  "SELECT [Order].* FROM [Order] "
				+ "LEFT JOIN Customer "
				+ "ON [Order].customer_id = [Customer].id "
				+ "WHERE [Order].id LIKE '%?%' "
				+ "OR Customer.name LIKE '%?%' "
				+ "OR Customer.id LIKE '%?%'";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setString(1, keyword);
			ps.setString(2, keyword);
			ps.setString(3, keyword);
			
			ResultSet results = ps.executeQuery(query);
			while (results.next()) {
				orders.add(buildOrder(results));
			}
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("Orders were not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return orders;
	}
	@Override
	public Order selectOrder(int orderId) {

		String query = "SELECT * FROM [Order] WHERE [Order].id = ?";
		try {
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setInt(1, orderId);
			
			ResultSet results = ps.executeQuery();
			if(results.next())
				return buildOrder(results);
		} catch (SQLException e) {
			System.out.println("Order was not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return null;
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
				
				DBProduct dbp = new DBProduct();
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
		
		String query =
				    "UPDATE [Order] "
				  + "SET delivery_status_id = ? "
				  + ",delivery_date = ? "
				  + ",payment_status = ? "
				  + ",payment_date = ? "
				  + "WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			
			ps.setInt(1, order.getDeliveryStatus());
			ps.setDate(2, order.getDeliveryDate());
			ps.setInt(3, order.getPaymentStatus());
			ps.setDate(4, order.getPaymentDate());
			ps.setInt(5, order.getId());
			
			boolean success = (ps.executeUpdate() != 0);
			ps.close();
			
			return success;
		}
		catch (SQLException e) {
			System.out.println("Order was not updated!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		
		return false;
	}
	@Override
	public boolean deleteOrder(Order order) {

		String query = "DELETE FROM [Order] WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setInt(1, order.getId());
			
			boolean success = (ps.executeUpdate() != 0);
			ps.close();
			
			return success;
		}
		catch (SQLException e) {
			System.out.println("Order was not deleted!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
			
		return false;
	}
	private Order buildOrder(ResultSet results) {
		String query = "";
		try {
			
			//Order
			Order order = new Order();
			order.setId(results.getInt("id"));
			order.setPurchaseDate(results.getDate("purchase_date"));
			order.setPrice(results.getDouble("price"));
			order.setDiscount(results.getDouble("discount"));
			order.setDeliveryPrice(results.getDouble("delivery_price"));
			order.setPaymentStatus(results.getInt("payment_status"));
			order.setPaymentDate(results.getDate("payment_date"));
			order.setDeliveryStatus(results.getInt("delivery_status_id"));
			order.setDeliveryDate(results.getDate("delivery_date"));
			
			//Customer
			DBCustomer dbc = new DBCustomer();
			Customer customer = dbc.selectCustomer(results.getInt("customer_id"));
			order.setCustomer(customer);
	
			//OrderProducts
			query = "SELECT product_id, amount FROM [Order_product] WHERE order_id = ?";
			results.close();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setQueryTimeout(5);
			ps.setInt(1, order.getId());
			
			OrderProduct op;
			DBProduct dbp = new DBProduct();
			results = ps.executeQuery();
			while (results.next()) {
				op = new OrderProduct();
				op.setProduct(dbp.selectProduct(results.getInt(1)));
				op.setAmount(results.getInt(2));
				order.addOrderProduct(op);
			}
			
			return order;
		}
		catch (SQLException e) {
			System.out.println("Order was not found!");
			System.out.println(e.getMessage());
			System.out.println(query);
		}
		return null;
	}
}
