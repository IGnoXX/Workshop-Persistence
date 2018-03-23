package guilayer.mgcustpanels;

import java.awt.Font;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import ctrllayer.CustomerController;
import ctrllayer.ProductController;
import guilayer.ResetablePanel;
import modlayer.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.JRadioButton;

public class CreateCustomer extends JPanel implements ResetablePanel {

	private CustomerController customerCtrl;
	private JTextField txt_name;
	private JTextField txt_address;
	private JTextField txt_zipCode;
	private JTextField txt_city;
	private JTextField txt_country;
	private JTextField txt_phone;
	private JTextField txt_email;
	private ButtonGroup btngrp_isPrivate;
	private JRadioButton rdb_yes;
	private JRadioButton rdb_no;
	private JButton btn_create;
	
	public CreateCustomer() {
		initialize();
	}
	
	public void initialize() {
		customerCtrl = new CustomerController();
		
		setLayout(null);
		
		Label lbl_name = new Label("Name *");
		lbl_name.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_name.setBounds(6, 10, 129, 22);
		add(lbl_name);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(6, 38, 404, 20);
		add(txt_name);
		
		Label lbl_address = new Label("Address");
		lbl_address.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_address.setBounds(6, 82, 197, 22);
		add(lbl_address);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(6, 110, 197, 20);
		add(txt_address);
		
		Label lbl_zipCode = new Label("Zipcode");
		lbl_zipCode.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_zipCode.setBounds(213, 82, 59, 22);
		add(lbl_zipCode);
		
		txt_zipCode = new JTextField();
		txt_zipCode.setColumns(10);
		txt_zipCode.setBounds(213, 110, 59, 20);
		add(txt_zipCode);
		
		Label lbl_city = new Label("City");
		lbl_city.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_city.setBounds(282, 82, 59, 22);
		add(lbl_city);
		
		txt_city = new JTextField();
		txt_city.setColumns(10);
		txt_city.setBounds(282, 110, 128, 20);
		add(txt_city);
		
		Label lbl_country = new Label("Country");
		lbl_country.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_country.setBounds(6, 151, 197, 22);
		add(lbl_country);
		
		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(6, 179, 197, 20);
		add(txt_country);
		
		Label lbl_phone = new Label("Phone *");
		lbl_phone.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_phone.setBounds(6, 210, 197, 22);
		add(lbl_phone);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(6, 238, 197, 20);
		add(txt_phone);
		
		Label lbl_email = new Label("Email *");
		lbl_email.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_email.setBounds(213, 210, 197, 22);
		add(lbl_email);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(213, 238, 197, 20);
		add(txt_email);
		
		Label lbl_isPrivate = new Label("Private cutomer?");
		lbl_isPrivate.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_isPrivate.setBounds(6, 279, 197, 22);
		add(lbl_isPrivate);
		
		rdb_yes = new JRadioButton("Yes");
		rdb_yes.setBounds(6, 307, 59, 23);
		add(rdb_yes);
		
		rdb_no = new JRadioButton("No");
		rdb_no.setSelected(true);
		rdb_no.setBounds(67, 307, 59, 23);
		add(rdb_no);
		
		btngrp_isPrivate = new ButtonGroup();
		btngrp_isPrivate.add(rdb_yes);
		btngrp_isPrivate.add(rdb_no);
		rdb_yes.setSelected(true);
		
		btn_create = new JButton("Create");
		btn_create.setBounds(610, 539, 122, 32);
		add(btn_create);
		
		btn_create.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		createCustomer();
	    	}
	    });
	}
	
	public void createCustomer() {
		if (!isFilledOut()) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "Required (*) fields must be filled out!",
				    "Warning!",
				    JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		boolean success = customerCtrl.createCustomer(
				txt_name.getText().trim(),
				txt_address.getText().trim(),
				txt_zipCode.getText().trim(),
				txt_city.getText().trim(),
				txt_country.getText().trim(),
				txt_phone.getText().trim(),
				txt_email.getText().trim(),
				rdb_yes.isSelected());
		if (!success) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "An error occured while creating the Customer!",
				    "Error!",
				    JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(this.getRootPane(),
			    "The Customer was successfully created!",
			    "Success!",
			    JOptionPane.INFORMATION_MESSAGE);
		
		resetForm();
	}
	private boolean isFilledOut() {
		if (txt_name.getText().trim().isEmpty())
			return false;
		if (txt_phone.getText().trim().isEmpty() && txt_email.getText().trim().isEmpty())
			return false;
		
		return true;
	}
	private void resetForm() {
		txt_name.setText("");
		txt_address.setText("");
		txt_zipCode.setText("");
		txt_city.setText("");
		txt_country.setText("");
		txt_phone.setText("");
		txt_email.setText("");
		rdb_yes.setSelected(true);
	}
	@Override
	public void reset() {
		resetForm();
	}
	@Override
	public void reopen() {
		customerCtrl = new CustomerController();
	}
}
