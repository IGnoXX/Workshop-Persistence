package dblayer;

import java.util.ArrayList;
import modlayer.*;

public interface IfDbProduct {

    public ArrayList<Product> getProducts();
    public ArrayList<Product> searchProducts(String keyword);
    public Product selectProduct(int productId);
    public int insertProduct(Product product);
    public boolean updateProduct(Product product);
    public boolean deleteProduct(Product product);
}