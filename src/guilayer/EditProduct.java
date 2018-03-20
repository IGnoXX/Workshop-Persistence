package guilayer;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class EditProduct extends JPanel {

	private JTextField txt_productId;
    private JRadioButton rdbtn_ePrivateCustomerYes;
    private JRadioButton rdbtn_ePrivateCustomerNo;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JTextField txt_name;
    private JTextField txt_desc;
    private JTextField txt_originCountry;
	
	public EditProduct() {
		setLayout(null);
		
		Label lbl_productId = new Label("ID");
		lbl_productId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_productId.setBounds(10, 10, 129, 22);
		add(lbl_productId);
		
		txt_productId = new JTextField();
		txt_productId.setBounds(10, 38, 86, 20);
		add(txt_productId);
		txt_productId.setColumns(10);
		
		JButton btn_search = new JButton("Search");
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
	    
	    JSpinner spr_purchasePrice = new JSpinner();
	    spr_purchasePrice.setBounds(10, 327, 129, 20);
	    add(spr_purchasePrice);
	    
	    Label lbl_salesPrice = new Label("Sales price");
	    lbl_salesPrice.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_salesPrice.setBounds(149, 299, 129, 22);
	    add(lbl_salesPrice);
	    
	    JSpinner spr_salesPrice = new JSpinner();
	    spr_salesPrice.setBounds(149, 327, 129, 20);
	    add(spr_salesPrice);
	    
	    Label lbl_rentPrice = new Label("Rent price");
	    lbl_rentPrice.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_rentPrice.setBounds(284, 299, 129, 22);
	    add(lbl_rentPrice);
	    
	    JSpinner spr_rentPrice = new JSpinner();
	    spr_rentPrice.setBounds(285, 327, 129, 20);
	    add(spr_rentPrice);
	    
	    Label lbl_stock = new Label("Stock");
	    lbl_stock.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_stock.setBounds(10, 367, 129, 22);
	    add(lbl_stock);
	    
	    JSpinner spr_stock = new JSpinner();
	    spr_stock.setBounds(10, 389, 129, 20);
	    add(spr_stock);
	    
	    Label lbl_minStock = new Label("Minimum stock");
	    lbl_minStock.setFont(new Font("Dialog", Font.PLAIN, 15));
	    lbl_minStock.setBounds(149, 363, 129, 22);
	    add(lbl_minStock);
	    
	    JSpinner spr_minStock = new JSpinner();
	    spr_minStock.setBounds(149, 389, 129, 20);
	    add(spr_minStock);
	    
	    JButton btn_delete = new JButton("Delete");
	    btn_delete.setEnabled(false);
	    btn_delete.setBounds(482, 539, 122, 32);
	    add(btn_delete);
	}

}
