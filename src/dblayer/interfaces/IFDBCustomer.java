package dblayer.interfaces;

import java.util.ArrayList;
import modlayer.*;

public interface IFDBCustomer {

    public ArrayList<Customer> getCustomers();
    public ArrayList<Customer> searchCustomers(String keyword);
    public Customer selectCustomer(int customerId);
    public int insertCustomer(Customer customer);
    public boolean updateCustomer(Customer customer);
    public boolean deleteCustomer(Customer customer);
}