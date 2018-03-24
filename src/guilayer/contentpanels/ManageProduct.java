package guilayer.contentpanels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import guilayer.mgprodpanels.*;

public class ManageProduct extends JTabbedPane {

	private int prevId;

	public ManageProduct() {
		super(JTabbedPane.TOP);
		prevId = 0;
		
		initialize();
	}

	private void initialize() {
		
		CreateProduct createProduct = new CreateProduct();
		this.addTab("Create Product", null, createProduct, null);
		
		ShowProduct showProduct = new ShowProduct();
		this.addTab("Show Product", null, showProduct, null);
		
		EditProduct editProduct = new EditProduct();
		this.addTab("Edit Product", null, editProduct, null);
		
		ManageProduct self = this;
		
		this.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				((ResetablePanel)self.getSelectedComponent()).reopen();
				((ResetablePanel)self.getComponent(prevId)).reset();
				
				prevId = self.getSelectedIndex();
	         }
		});
	}
}
