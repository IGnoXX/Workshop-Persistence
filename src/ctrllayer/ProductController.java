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
}
