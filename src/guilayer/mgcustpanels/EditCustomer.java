package guilayer.mgcustpanels;

import java.awt.Font;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ctrllayer.CustomerController;
import guilayer.ResetablePanel;
import modlayer.Customer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCustomer extends JPanel implements ResetablePanel {

	private CustomerController customerCtrl;
	private Customer customer;
	private JTextField txt_customerId;
	private JButton btn_search;
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
	private JButton btn_update;
	private JButton btn_cancel;
	
	public EditCustomer() {
		initialize();
	}
	
	public void initialize() {
		customerCtrl = new CustomerController();
		
		setLayout(null);
		
		Label lbl_customerId = new Label("Customer ID");
		lbl_customerId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_customerId.setBounds(6, 10, 129, 22);
		add(lbl_customerId);
		
		txt_customerId = new JTextField();
		txt_customerId.setColumns(10);
		txt_customerId.setBounds(6, 38, 100, 20);
		add(txt_customerId);
		
		btn_search = new JButton("Search");
		btn_search.setBounds(114, 38, 89, 23);
		add(btn_search);
		
		Label lbl_name = new Label("Name");
		lbl_name.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_name.setBounds(6, 81, 129, 22);
		add(lbl_name);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(6, 109, 404, 20);
		add(txt_name);
		
		Label lbl_address = new Label("Address");
		lbl_address.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_address.setBounds(6, 153, 197, 22);
		add(lbl_address);
		
		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(6, 181, 197, 20);
		add(txt_address);
		
		Label lbl_zipCode = new Label("Zipcode");
		lbl_zipCode.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_zipCode.setBounds(213, 153, 59, 22);
		add(lbl_zipCode);
		
		txt_zipCode = new JTextField();
		txt_zipCode.setColumns(10);
		txt_zipCode.setBounds(213, 181, 59, 20);
		add(txt_zipCode);
		
		Label lbl_city = new Label("City");
		lbl_city.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_city.setBounds(282, 153, 59, 22);
		add(lbl_city);
		
		txt_city = new JTextField();
		txt_city.setColumns(10);
		txt_city.setBounds(282, 181, 128, 20);
		add(txt_city);
		
		Label lbl_country = new Label("Country");
		lbl_country.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_country.setBounds(6, 222, 197, 22);
		add(lbl_country);
		
		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(6, 250, 197, 20);
		add(txt_country);
		
		Label lbl_phone = new Label("Phone");
		lbl_phone.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_phone.setBounds(6, 281, 197, 22);
		add(lbl_phone);
		
		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(6, 309, 197, 20);
		add(txt_phone);
		
		Label lbl_email = new Label("Email");
		lbl_email.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_email.setBounds(213, 281, 197, 22);
		add(lbl_email);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(213, 309, 197, 20);
		add(txt_email);
		
		Label lbl_isPrivate = new Label("Private cutomer?");
		lbl_isPrivate.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_isPrivate.setBounds(6, 350, 197, 22);
		add(lbl_isPrivate);
		
		rdb_yes = new JRadioButton("Yes");
		rdb_yes.setBounds(6, 378, 59, 23);
		add(rdb_yes);
		
		rdb_no = new JRadioButton("No");
		rdb_no.setSelected(true);
		rdb_no.setBounds(67, 378, 59, 23);
		add(rdb_no);
		
		btngrp_isPrivate = new ButtonGroup();
		btngrp_isPrivate.add(rdb_yes);
		btngrp_isPrivate.add(rdb_no);
		
		btn_update = new JButton("Update");
		btn_update.setBounds(479, 553, 122, 32);
		add(btn_update);
		
		btn_cancel = new JButton("Cancel");
		btn_cancel.setBounds(611, 553, 122, 32);
		add(btn_cancel);
		
		resetForm();
		
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer = searchCustomer(txt_customerId.getText().trim());
				if (customer == null) {
					return;
				}
				
				fillForm(customer);
			}
		});
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer = null;
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
				
				Customer result = new Customer(
						customer.getId(),
						txt_name.getText().trim(),
						txt_address.getText().trim(),
						txt_zipCode.getText().trim(),
						txt_city.getText().trim(),
						txt_country.getText().trim(),
						txt_phone.getText().trim(),
						txt_email.getText().trim(),
						rdb_yes.isSelected());
				
				if (!customerCtrl.updateCustomer(result)) {
					JOptionPane.showMessageDialog(null,
						    "An error occured while updating the Customer!",
						    "Error!",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(null,
					    "The Customer was successfully edited!",
					    "Success!",
					    JOptionPane.INFORMATION_MESSAGE);
				
				customer = null;
				resetForm();
			}
		});
	}
	
	public void updateCustomer() {
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
	private Customer searchCustomer(String keyword) {
		Customer result = null;
		int customerId = -1;
		
		try {
			customerId = Integer.parseInt(keyword);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "The entered value is not a valid number!",
				    "Error!",
				    JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		result = customerCtrl.getCustomer(customerId);
		if (result == null) {
			JOptionPane.showMessageDialog(this.getRootPane(),
				    "There is no Customer with that id!",
				    "Warning!",
				    JOptionPane.WARNING_MESSAGE);
			return null;
		}
		
		return result;
	}
	private void fillForm(Customer customer) {
		txt_customerId.setEnabled(false);
		btn_search.setEnabled(false);
		
		txt_customerId.setText("");
		txt_name.setText(customer.getName());
		txt_address.setText(customer.getAddress());
		txt_zipCode.setText(customer.getZipcode());
		txt_city.setText(customer.getCity());
		txt_country.setText(customer.getCountry());
		txt_phone.setText(customer.getPhone());
		txt_email.setText(customer.getEmail());
		if (customer.getIsPrivate())
			rdb_yes.setSelected(true);
		else
			rdb_no.setSelected(true);
		
		txt_name.setEnabled(true);
		txt_address.setEnabled(true);
		txt_zipCode.setEnabled(true);
		txt_city.setEnabled(true);
		txt_country.setEnabled(true);
		txt_phone.setEnabled(true);
		txt_email.setEnabled(true);
		rdb_yes.setEnabled(true);
		rdb_no.setEnabled(true);
	}
	private void resetForm() {
		txt_customerId.setEnabled(true);
		btn_search.setEnabled(true);
		
		txt_name.setText("");
		txt_address.setText("");
		txt_zipCode.setText("");
		txt_city.setText("");
		txt_country.setText("");
		txt_phone.setText("");
		txt_email.setText("");
		rdb_yes.setSelected(true);
		
		txt_name.setEnabled(false);
		txt_address.setEnabled(false);
		txt_zipCode.setEnabled(false);
		txt_city.setEnabled(false);
		txt_country.setEnabled(false);
		txt_phone.setEnabled(false);
		txt_email.setEnabled(false);
		rdb_yes.setEnabled(false);
		rdb_no.setEnabled(false);
	}
	@Override
	public void reset() {
		customer = null;
		resetForm();
	}
	@Override
	public void reopen() {
		customerCtrl = new CustomerController();
	}
}
