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

	public ArrayList<Product> searchProducts(String keyword) {
		ArrayList<Product> results = new ArrayList<>();
		
		for (Product product : products) {
			if (product.getName().indexOf(keyword) > -1 ||
				product.getDesc().indexOf(keyword) > -1 ||
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
	public Product createProduct(String name, double purchasePrice, double salesPrice, double rentPrice, String countryOfOrigin, String desc, int stock, int minStock) {
		Product product = new Product();
		product.setName(name);
		product.setPurchasePrice(purchasePrice);
		product.setSalesPrice(salesPrice);
		product.setRentPrice(rentPrice);
		product.setCountryOfOrigin(countryOfOrigin);
		product.setDesc(desc);
		product.setStock(stock);
		product.setMinStock(minStock);
		
		return createProduct(product);
	}
	public Product createProduct(Product product) {
		int id = dbProduct.insertProduct(product);
		if (id < 1)
			return null;
		
		product.setId(id);
		products = dbProduct.getProducts();
		return product;
	}
	public boolean updateProduct(Product product) {
		boolean success = dbProduct.updateProduct(product);
		products = dbProduct.getProducts();
		
		return success;
	}
	public boolean deleteProduct(Product product) {
		boolean success = dbProduct.deleteProduct(product);
		products = dbProduct.getProducts();
		
		return success;
	}

}
