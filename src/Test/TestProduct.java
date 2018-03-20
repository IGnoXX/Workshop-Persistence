package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ctrllayer.ProductController;
import dblayer.DBProduct;
import modlayer.Product;

public class TestProduct {

	ProductController pCtr = new ProductController();
	
	@Test
	public void getProductByKeyword() {
		ArrayList<Product> results = new ArrayList<>();
		results = pCtr.searchProducts("hat");
		assertEquals("Product found","hat",results.get(0).getName());
	}
	
	@Test
	public void getProductByID() {
		Product product;
		product = pCtr.getProduct(1);
		assertEquals("Product found","hat",product.getName());
	}
	
	@Test
	public void createNewProduct() {
		pCtr.createProduct("FuckMarton",0,0,0,"Aserbajdsjan","Makes your dream come true",12,1);
		ArrayList<Product> result = new ArrayList<>();
		boolean inserted = true;
		result = pCtr.searchProducts("arton");
		if(result.get(0).getName().contains("arton"))
			inserted = true;
		else
			inserted = false;
		assertTrue("Product inserted",inserted);
	}
	
	@Test
	public void updateProduct() {
		ArrayList<Product> result = new ArrayList<>();
		result = pCtr.searchProducts("arton");
		result.get(0).setName("Money");
		
		assertTrue("Product updated",pCtr.updateProduct(result.get(0)));
	}
	
	@Test
	public void deleteProduct() {
		ArrayList<Product> results = new ArrayList<>();
		results = pCtr.searchProducts("oney");
		boolean deleted = false;
		for(Product result : results) {
			deleted = pCtr.deleteProduct(result);
		}
		assertTrue("Product deleted",deleted);
	}
}
