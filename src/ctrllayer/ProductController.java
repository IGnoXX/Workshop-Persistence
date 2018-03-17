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
	 * 
	 * @param search
	 *            string to look for in the database.
	 * @param collumns
	 *            which columns should be returned
	 * @return 2d array of data.
	 */
	public Object[][] getProductsData(String search, String[] columns) {
		return new Object[][] { { "1", "blabla", "12", "100" }, { "2", search, "9", 0 } };
		// TODO: this method
	}

	public ArrayList<Product> searchProducts(String query) {
		// TODO:stub
		ArrayList<Product> b = new ArrayList();
		b.add(new Product(1, "query", 22.22, 25.22, 11, "that", "fery amazzzzing produgt", 9, 15));
		b.add(new Product(2, "f", 25.22, 29.22, 11, "that", "fery amazzzzing produgt", 9, 100));
		b.add(new Product(3, "asdf", 29.22, 55.22, 11, "that", "fery amazzzzing produgt", 9, 1235));
		b.add(new Product(4, "vcxz", 2214.22, 95.22, 11, "that", "fery amazzzzing produgt", 9, 10));

		return b;
	}
}
