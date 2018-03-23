package guilayer.mgprodpanels;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import ctrllayer.ProductController;
import guilayer.ResetablePanel;
import modlayer.Product;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class EditProduct extends JPanel implements ResetablePanel {

    private ProductController productCtrl;
	private JTextField txt_productId;
    private JButton btn_search;
	private JTextField txt_name;
	private JTextField txt_desc;
	private JTextField txt_originCountry;
	private JSpinner spr_purchasePrice;
	private JSpinner spr_salesPrice;
	private JSpinner spr_rentPrice;
	private JSpinner spr_stock;
	private JSpinner spr_minStock;
    private JButton btn_cancel;
    private JButton btn_update;
    private Product product;
	
	public EditProduct() {
		productCtrl = new ProductController();
		
		setLayout(null);
		
		Label lbl_productId = new Label("ID");
		lbl_productId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_productId.setBounds(10, 10, 129, 22);
		add(lbl_productId);
		
		txt_productId = new JTextField();
		txt_productId.setBounds(10, 38, 86, 20);
		add(txt_productId);
		txt_productId.setColumns(10);
		
		btn_search = new JButton("Search");
		btn_search.setBounds(102, 37, 89, 21);
		add(btn_search);
	    
	    Label lbl_name = new Label("Name");
	    lbl_name.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_name.setBounds(10, 99, 129, 22);
	    add(lbl_name);
	    
	    txt_name = new JTextField();
	    txt_name.setColumns(10);
	    txt_name.setBounds(10, 127, 404, 20);
	    add(txt_name);
	    
	    Label lbl_desc = new Label("Description");
	    lbl_desc.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_desc.setBounds(10, 153, 197, 22);
	    add(lbl_desc);
	    
	    txt_desc = new JTextField();
	    txt_desc.setColumns(10);
	    txt_desc.setBounds(10, 181, 404, 53);
	    add(txt_desc);
	    
	    Label lbl_originCountry = new Label("Country of Origin");
	    lbl_originCountry.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_originCountry.setBounds(10, 240, 197, 22);
	    add(lbl_originCountry);
	    
	    txt_originCountry = new JTextField();
	    txt_originCountry.setColumns(10);
	    txt_originCountry.setBounds(10, 268, 197, 20);
	    add(txt_originCountry);
	    
	    Label lbl_purchasePrice = new Label("Purchase price");
	    lbl_purchasePrice.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_purchasePrice.setBounds(10, 299, 129, 22);
	    add(lbl_purchasePrice);
	    
	    spr_purchasePrice = new JSpinner();
	    spr_purchasePrice.setBounds(10, 327, 129, 20);
	    add(spr_purchasePrice);
	    
	    Label lbl_salesPrice = new Label("Sales price");
	    lbl_salesPrice.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_salesPrice.setBounds(149, 299, 129, 22);
	    add(lbl_salesPrice);
	    
	    spr_salesPrice = new JSpinner();
	    spr_salesPrice.setBounds(149, 327, 129, 20);
	    add(spr_salesPrice);
	    
	    Label lbl_rentPrice = new Label("Rent price");
	    lbl_rentPrice.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_rentPrice.setBounds(284, 299, 129, 22);
	    add(lbl_rentPrice);
	    
	    spr_rentPrice = new JSpinner();
	    spr_rentPrice.setBounds(285, 327, 129, 20);
	    add(spr_rentPrice);
	    
	    Label lbl_stock = new Label("Stock");
	    lbl_stock.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_stock.setBounds(10, 367, 129, 22);
	    add(lbl_stock);
	    
	    Label lbl_minStock = new Label("Minimum stock");
	    lbl_minStock.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_minStock.setBounds(149, 363, 129, 22);
	    add(lbl_minStock);
	    
	    btn_cancel = new JButton("Cancel");
	    btn_cancel.setEnabled(false);
	    btn_cancel.setBounds(614, 539, 122, 32);
	    add(btn_cancel);
	    
	    btn_update = new JButton("Update");
	    btn_update.setEnabled(false);
	    btn_update.setBounds(482, 539, 122, 32);
	    add(btn_update);
	    
	    spr_stock = new JSpinner();
	    spr_stock.setEnabled(false);
	    spr_stock.setBounds(10, 395, 129, 20);
	    add(spr_stock);
	    
	    spr_minStock = new JSpinner();
	    spr_minStock.setEnabled(false);
	    spr_minStock.setBounds(149, 395, 129, 20);
	    add(spr_minStock);
	    
	    resetForm();
	    
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product = searchProduct(txt_productId.getText().trim());
				if (product == null) {
					return;
				}
				
				fillForm(product);
			}
		});
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product = null;
				resetForm();
			}
		});
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isFilledOut()) {
					JOptionPane.showMessageDialog(null,
						    "Required (*) fields must be filled out!",
						    "Warning!",
						    JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Product result = new Product(
						product.getId(),
						txt_name.getText().trim(),
						(Double)spr_purchasePrice.getValue(),
						(Double)spr_salesPrice.getValue(),
						(Double)spr_rentPrice.getValue(),
						txt_originCountry.getText().trim(),
						txt_desc.getText().trim(),
						(Integer)spr_minStock.getValue(),
						(Integer)spr_stock.getValue());
				
				if (!productCtrl.updateProduct(result)) {
					JOptionPane.showMessageDialog(null,
						    "An error occured while updating the Product!",
						    "Error!",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(null,
					    "The Product was successfully edited!",
					    "Success!",
					    JOptionPane.INFORMATION_MESSAGE);
				
				product = null;
				resetForm();
			}
		});
	}
	private Product searchProduct(String keyword) {
		Product result = null;
		int productId = -1;
		
		try {
			productId = Integer.parseInt(keyword);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "The entered value is not a valid number!",
				    "Error!",
				    JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		result = productCtrl.getProduct(productId);
		if (result == null) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "There is no Product with that id!",
				    "Warning!",
				    JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		return result;
	}
	private void fillForm(Product product) {
		txt_productId.setEnabled(false);
		btn_search.setEnabled(false);
		
		txt_name.setText(product.getName());
		txt_desc.setText(product.getDesc());
		txt_originCountry.setText(product.getCountryOfOrigin());
		spr_purchasePrice.setValue(new Double(product.getPurchasePrice()));
		spr_salesPrice.setValue(new Double(product.getSalesPrice()));
		spr_rentPrice.setValue(new Double(product.getRentPrice()));
		spr_stock.setValue(new Integer(product.getStock()));
		spr_minStock.setValue(new Integer(product.getMinStock()));
		
		txt_name.setEnabled(true);
		txt_desc.setEnabled(true);
		txt_originCountry.setEnabled(true);
		spr_purchasePrice.setEnabled(true);
		spr_salesPrice.setEnabled(true);
		spr_rentPrice.setEnabled(true);
		spr_stock.setEnabled(true);
		spr_minStock.setEnabled(true);
		btn_update.setEnabled(true);
		btn_cancel.setEnabled(true);
	}
	private void resetForm() {
		txt_productId.setEnabled(true);
		btn_search.setEnabled(true);
		
		txt_productId.setText("");
		txt_name.setText("");
		txt_desc.setText("");
		txt_originCountry.setText("");
		spr_purchasePrice.setValue(new Double(0.0));
		spr_salesPrice.setValue(new Double(0.0));
		spr_rentPrice.setValue(new Double(0.0));
		spr_stock.setValue(new Integer(0));
		spr_minStock.setValue(new Integer(0));
		
		txt_name.setEnabled(false);
		txt_desc.setEnabled(false);
		txt_originCountry.setEnabled(false);
		spr_purchasePrice.setEnabled(false);
		spr_salesPrice.setEnabled(false);
		spr_rentPrice.setEnabled(false);
		btn_update.setEnabled(false);
		btn_cancel.setEnabled(false);
	}
	private boolean isFilledOut() {
		if (txt_name.getText().trim().isEmpty())
			return false;
		
		return true;
	}
	@Override
	public void reset() {
		product = null;
		resetForm();
	}
	@Override
	public void reopen() {
		productCtrl = new ProductController();
	}
}
