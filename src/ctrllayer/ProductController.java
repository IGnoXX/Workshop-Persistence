package ctrllayer;

import java.util.ArrayList;
import modlayer.*;

public class ProductController {

	private ArrayList<Product> products;

	public ProductController() {
		products = new ArrayList<>();
		products.add(new Product(1, "query", 22.22, 25.22, 11, "that", "fery amazzzzing produgt", 9, 15));
		products.add(new Product(2, "f", 25.22, 29.22, 11, "that", "fery amazzzzing produgt", 9, 100));
		products.add(new Product(3, "asdf", 29.22, 55.22, 11, "that", "fery amazzzzing produgt", 9, 1235));
		products.add(new Product(4, "vcxz", 2214.22, 95.22, 11, "that", "fery amazzzzing produgt", 9, 10));
	}

	public Product getProduct(int productId) {

		for (Product product : products) {
			if (product.getId() == productId)
				return product;
		}

		return null;
	}

	public ArrayList<Product> searchProducts(String query) {
		return products;
	}
}
