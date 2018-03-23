package ctrllayer;

import java.util.ArrayList;

import dblayer.*;
import modlayer.*;

public class ProductController {

	private ArrayList<Product> products;
	private DBProduct dbProduct;

	public ProductController() {
		dbProduct = new DBProduct();
		products = dbProduct.getProducts();
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	public ArrayList<Product> searchProducts(String keyword) {
		ArrayList<Product> results = new ArrayList<>();
		keyword = keyword.toLowerCase();
		
		for (Product product : products) {
			if (product.getName().toLowerCase().indexOf(keyword) > -1 ||
				product.getDesc().toLowerCase().indexOf(keyword) > -1 ||
				Integer.toString(product.getId()).indexOf(keyword) > -1) {
				results.add(product);
			}
		}
		
		return results;
	}
	public Product getProduct(int productId) {

		for (Product product : products) {
			if (product.getId() == productId)
				return product;
		}
		
		return null;
	}
	public boolean createProduct(String name, double purchasePrice, double salesPrice, double rentPrice, String countryOfOrigin, String desc, int stock, int minStock) {
		boolean success = false;
		
		Product product = new Product();
		product.setName(name);
		product.setPurchasePrice(purchasePrice);
		product.setSalesPrice(salesPrice);
		product.setRentPrice(rentPrice);
		product.setCountryOfOrigin(countryOfOrigin);
		product.setDesc(desc);
		product.setStock(stock);
		product.setMinStock(minStock);
		
		int id = dbProduct.insertProduct(product);
		if (id > 0) {
			success = true;
			
			product.setId(id);
			products.add(product);
		}
		
		return success;
	}
	public boolean updateProduct(Product product) {
		boolean success = dbProduct.updateProduct(product);
		if (success) {
			products.remove(getProduct(product.getId()));
			products.add(product);
		}
		
		return success;
	}
	public boolean deleteProduct(Product product) {
		boolean success = dbProduct.deleteProduct(product);
		if (success) {
			products.remove(product);
		}
		
		return success;
	}
}
