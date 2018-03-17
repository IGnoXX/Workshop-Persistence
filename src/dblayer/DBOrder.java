package dblayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modlayer.Customer;
import modlayer.Order;
import modlayer.OrderProduct;

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
		//TODO: do we need to search orders?
//		ResultSet results;
//		ArrayList<Order> list = new ArrayList<Order>();
//
//		String query = "SELECT * FROM Order where name LIKE '%";
//
//		try {
//			Statement stmt = con.createStatement();
//			stmt.setQueryTimeout(5);
//			results = stmt.executeQuery(query);
//
//			while (results.next()) {
//				Employee empObj = new Employee();
//				empObj = buildEmployee(results);
//				list.add(empObj);
//			} // end while
//			stmt.close();
//			if (retrieveAssociation) { // The supervisor and department is to be build as well
//				for (Employee empObj : list) {
//					String super_ssn = empObj.getSupervisor().getSsn();
//					Employee superEmp = singleWhere(" ssn = '" + super_ssn + "'", false);
//					empObj.setSupervisor(superEmp);
//					System.out.println("Supervisor is seleceted");
//					// here the department has to be selected as well
//				}
//			} // end if
//
//		} // slut try
//		catch (Exception e) {
//			System.out.println("Query exception - select: " + e);
//			e.printStackTrace();
//		}
//		return list;
	return null;
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
			} else { 
				order = null;
			}
		} 
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return order;
	}

	@Override
	public boolean insertOrder(Order order) {
		// TODO remove getInvoice
		int rc = -1;
		int id = 0;
		String query = "INSERT INTO Order(customer_id, delivery_date, payment_date, price) OUTPUT Inserted.ID VALUES('"
				+ order.getCustomer().getId() + "','" + order.getDeliveryDate() + "','"
				+ order.getInvoice().getPaymentDate() + "','" + order.getInvoice().getPrice() + "')";
		try {
			// con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			ResultSet results = stmt.executeQuery(query);
			if(results.next())
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

	@Override
	public boolean updateOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}
	private Order buildOrder(ResultSet results)
    {   Order order = new Order();
        try{ // the columns from the table employee  are used
        	order.setId(results.getInt("id"));
        	order.setCustomer(new Customer(results.getInt("customer_id")));
        //TODO: date and invoice fields setters
        	order.setDeliveryStatus(results.getInt("delivery_status_id"));
        }
       catch(Exception e)
       {
           System.out.println("error in building the employee object");
       }
       return order;
    }
}
