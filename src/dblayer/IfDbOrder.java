package dblayer;

import java.sql.SQLException;
import java.util.ArrayList;
import modlayer.*;

public interface IfDbOrder {

    public ArrayList<Order> getOrders();
    public ArrayList<Order> searchOrders(String keyword);
    public Order selectOrder(int orderId);
    public int insertOrder(Order order);
    public boolean updateOrder(Order order);
    public boolean deleteOrder(Order order);
}