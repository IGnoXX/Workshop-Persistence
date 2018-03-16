package ctrllayer;

import java.util.ArrayList;
import modlayer.*;

public class ProductController {
	
	private ArrayList<Product> products;
	
	public ProductController() {
		products = new ArrayList<>();
	}
	
	public Product getProduct(int productId) {

		for (Product product : products) {
			if (product.getId() == productId)
				return product;
		}
		
		return null;
	}
	
	
	   /**
	   * This method searches the database and returns specific collumns
	   * @param search string to look for in the database.
	   * @param collumns which columns should be returned
	   * @return 2d array of data.
	   */
	public Object[][] getProductsData(String search, String[] columns){
		return new Object[][]{{"1","blabla","12","100"},{"2",search,"9",0}};
		//TODO: this method
	}
}
