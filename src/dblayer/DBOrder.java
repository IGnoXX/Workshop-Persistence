package dblayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modlayer.Order;
import modlayer.OrderProduct;

public class DBOrder implements IOrder {

	private Connection con;

	/** Creates a new instance of DBEmployee */
	public DBOrder() {
		con = DbConnection.getInstance().getDBcon();
	}

	@Override
	public boolean InsertOrder(Order order) {
		// TODO Auto-generated method stub
		int rc = -1;
		int id = 0;
		String query = "INSERT INTO Order(customer_id, delivery_date, payment_date, price) OUTPUT Inserted.ID VALUES('"
				+ order.getCustomer().getId() + "','" + order.getDeliveryDate() + "','" + order.getPaymentDate() + "','"
				+ order.getPrice() + "')";
		try {
			// con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet results = stmt.executeQuery(query);
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
					}
				}

			} else
				System.out.println("order id check failed");

		} // end try
		catch (SQLException ex) {
			System.out.println("Order was not inserted");
		}
		return false;
	}

}
