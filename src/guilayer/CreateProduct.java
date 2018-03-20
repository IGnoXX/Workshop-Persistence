package guilayer;

import java.awt.Font;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ctrllayer.ProductController;
import modlayer.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateProduct extends JPanel {

	private ProductController productCtrl;
	private JTextField txt_name;
	private JTextField txt_desc;
	private JTextField txt_originCountry;
	private JSpinner spr_purchasePrice;
	private JSpinner spr_salesPrice;
	private JSpinner spr_rentPrice;
	private JSpinner spr_stock;
	private JSpinner spr_minStock;
	private JButton btn_create;
	
	public CreateProduct() {
		productCtrl = new ProductController();
		
		setLayout(null);

		Label lbl_name = new Label("Name *");
		lbl_name.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_name.setBounds(10, 10, 129, 22);
		add(lbl_name);
		
		txt_name = new JTextField();
		txt_name.setBounds(10, 38, 404, 20);
		add(txt_name);
		txt_name.setColumns(10);
		
		Label lbl_desc = new Label("Description");
		lbl_desc.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_desc.setBounds(10, 64, 197, 22);
		add(lbl_desc);
		
		txt_desc = new JTextField();
		txt_desc.setColumns(10);
		txt_desc.setBounds(10, 92, 404, 53);
		add(txt_desc);
		
		Label lbl_originCountry = new Label("Country of Origin");
		lbl_originCountry.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_originCountry.setBounds(10, 151, 197, 22);
		add(lbl_originCountry);
		
		txt_originCountry = new JTextField();
		txt_originCountry.setColumns(10);
		txt_originCountry.setBounds(10, 179, 197, 20);
		add(txt_originCountry);
		
		Label lbl_purchasePrice = new Label("Purchase price *");
		lbl_purchasePrice.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_purchasePrice.setBounds(10, 210, 129, 22);
		add(lbl_purchasePrice);
		
		spr_purchasePrice = new JSpinner();
		spr_purchasePrice.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spr_purchasePrice.setBounds(10, 238, 129, 20);
		add(spr_purchasePrice);
		
		Label lbl_salesPrice = new Label("Sales price *");
		lbl_salesPrice.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_salesPrice.setBounds(149, 210, 129, 22);
		add(lbl_salesPrice);
		
		spr_salesPrice = new JSpinner();
		spr_salesPrice.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spr_salesPrice.setBounds(149, 238, 129, 20);
		add(spr_salesPrice);
		
		Label lbl_rentPrice = new Label("Rent price");
		lbl_rentPrice.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_rentPrice.setBounds(284, 210, 129, 22);
		add(lbl_rentPrice);
	    
	    spr_rentPrice = new JSpinner();
	    spr_rentPrice.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
	    spr_rentPrice.setBounds(285, 238, 129, 20);
	    add(spr_rentPrice);
	    
	    Label lbl_stock = new Label("Stock *");
	    lbl_stock.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_stock.setBounds(10, 278, 129, 22);
	    add(lbl_stock);
	    
	    spr_stock = new JSpinner();
	    spr_stock.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
	    spr_stock.setBounds(10, 300, 129, 20);
	    add(spr_stock);
	    
	    Label lbl_minStock = new Label("Minimum stock");
	    lbl_minStock.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_minStock.setBounds(149, 274, 129, 22);
	    add(lbl_minStock);
	    
	    spr_minStock = new JSpinner();
	    spr_minStock.setBounds(149, 300, 129, 20);
	    add(spr_minStock);
	    
	    btn_create = new JButton("Create");
	    btn_create.setBounds(614, 539, 122, 32);
	    add(btn_create);
	    
	    btn_create.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		createProduct();
	    	}
	    });
	}
	
	public void createProduct() {
		if (!isFilledOut()) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "Required (*) fields must be filled out!",
				    "Warning!",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Product product = productCtrl.createProduct(
				txt_name.getText().trim(),
				(Double)spr_purchasePrice.getValue(),
				(Double)spr_salesPrice.getValue(),
				(Double)spr_rentPrice.getValue(),
				txt_originCountry.getText().trim(),
				txt_desc.getText().trim(),
				(Integer)spr_stock.getValue(),
				(Integer)spr_minStock.getValue());
		if (product == null) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "An error occured while creating the Order!",
				    "Error!",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this.getRootPane(),
			    "The Order was successfully created!",
			    "Success!",
			    JOptionPane.INFORMATION_MESSAGE);
		
		resetForm();
	}
	private boolean isFilledOut() {
		if (txt_name.getText().trim().isEmpty())
			return false;
		
		return true;
	}
	private void resetForm() {
		productCtrl = null;
		txt_name.setText("");
		txt_desc.setText("");
		txt_originCountry.setText("");
		spr_purchasePrice.setValue(new Double(0.0));
		spr_salesPrice.setValue(new Double(0.0));
		spr_rentPrice.setValue(new Double(0.0));
		spr_stock.setValue(new Double(0.0));
		spr_minStock.setValue(new Double(0.0));
		
		productCtrl = new ProductController();
	}
	
}
