package dblayer;

import java.util.ArrayList;

import modlayer.Product;

public interface IProduct {
    //get one Departmen having the dno
    public Product getProductByID(int id);
    //find one department having the department name 
    public ArrayList<Product> findProducts(String query);
    //find one department having the department name 
    //insert new department
    public int insert(Product product);
    //update department
    public int update(Product product);
    //delete department
    public int delete(Product product);
}
