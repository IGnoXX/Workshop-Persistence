package dblayer;

import java.util.ArrayList;
import modlayer.*;

public interface IfDbOrderProduct {

    public ArrayList<OrderProduct> getOrderProductsByOrder(int orderId);
    public OrderProduct selectOrderProduct(int orderProductId);
    public int insertOrderProduct(OrderProduct orderProduct);
    public boolean updateOrderProduct(OrderProduct orderProduct);
    public boolean deleteOrderProduct(OrderProduct orderProduct);
}