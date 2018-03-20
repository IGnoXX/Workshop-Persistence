package guilayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import java.awt.Button;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import ctrllayer.*;
import modlayer.Customer;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ManageCustomer extends JFrame {

	private CustomerController customerController = new CustomerController();

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_address;
	private JTextField txt_zipcode;
	private JTextField txt_city;
	private JTextField txt_country;
	private JTextField txt_phone;
	private JTextField txt_mail;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txt_customerId;
	private JTextField txt_eName;
	private JTextField txt_eAddress;
	private JTextField txt_eZipcode;
	private JTextField txt_eCity;
	private JTextField txt_eCountry;
	private JTextField txt_ePhone;
	private JTextField txt_eMail;
	private JRadioButton rdbtn_ePrivateCustomerYes;
	private JRadioButton rdbtn_ePrivateCustomerNo;
	private JButton btnDelete;
	private JButton btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCustomer frame = new ManageCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageCustomer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(5, 5, 751, 610);
		contentPane.add(tabbedPane);

		Panel panel_createCustomer = new Panel();
		tabbedPane.addTab("Create Customer", null, panel_createCustomer, null);
		panel_createCustomer.setLayout(null);

		Label lbl_name = new Label("Name");
		lbl_name.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_name.setBounds(10, 10, 129, 22);
		panel_createCustomer.add(lbl_name);

		txt_name = new JTextField();
		txt_name.setBounds(10, 38, 404, 20);
		panel_createCustomer.add(txt_name);
		txt_name.setColumns(10);

		Label lbl_address = new Label("Address");
		lbl_address.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_address.setBounds(10, 82, 197, 22);
		panel_createCustomer.add(lbl_address);

		txt_address = new JTextField();
		txt_address.setColumns(10);
		txt_address.setBounds(10, 110, 197, 20);
		panel_createCustomer.add(txt_address);

		Label lbl_zipcode = new Label("Zipcode");
		lbl_zipcode.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_zipcode.setBounds(217, 82, 59, 22);
		panel_createCustomer.add(lbl_zipcode);

		txt_zipcode = new JTextField();
		txt_zipcode.setColumns(10);
		txt_zipcode.setBounds(217, 110, 59, 20);
		panel_createCustomer.add(txt_zipcode);

		Label lbl_city = new Label("City");
		lbl_city.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_city.setBounds(286, 82, 59, 22);
		panel_createCustomer.add(lbl_city);

		txt_city = new JTextField();
		txt_city.setColumns(10);
		txt_city.setBounds(286, 110, 128, 20);
		panel_createCustomer.add(txt_city);

		Label lbl_country = new Label("Country");
		lbl_country.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_country.setBounds(10, 151, 197, 22);
		panel_createCustomer.add(lbl_country);

		txt_country = new JTextField();
		txt_country.setColumns(10);
		txt_country.setBounds(10, 179, 197, 20);
		panel_createCustomer.add(txt_country);

		Label lbl_phone = new Label("Phone");
		lbl_phone.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_phone.setBounds(10, 210, 197, 22);
		panel_createCustomer.add(lbl_phone);

		txt_phone = new JTextField();
		txt_phone.setColumns(10);
		txt_phone.setBounds(10, 238, 197, 20);
		panel_createCustomer.add(txt_phone);

		Label lbl_mail = new Label("Mail");
		lbl_mail.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_mail.setBounds(217, 210, 197, 22);
		panel_createCustomer.add(lbl_mail);

		txt_mail = new JTextField();
		txt_mail.setColumns(10);
		txt_mail.setBounds(217, 238, 197, 20);
		panel_createCustomer.add(txt_mail);

		Label lbl_isPrivate = new Label("Private cutomer?");
		lbl_isPrivate.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_isPrivate.setBounds(10, 279, 197, 22);
		panel_createCustomer.add(lbl_isPrivate);

		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(10, 307, 59, 23);
		panel_createCustomer.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		rdbtnNo.setBounds(71, 307, 59, 23);
		panel_createCustomer.add(rdbtnNo);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnYes);
		group.add(rdbtnNo);

		JButton btn_createCustomer = new JButton("Create");
		btn_createCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Grab and check all data
				if (!txt_name.getText().equals("") && !txt_address.getText().equals("")
						&& !txt_zipcode.getText().equals("") && !txt_city.getText().equals("")
						&& !txt_country.getText().equals("") && !txt_phone.getText().equals("")
						&& !txt_mail.getText().equals("")) {

					if (customerController.createCustomer(txt_name.getText(), txt_address.getText(),
							txt_zipcode.getText(), txt_city.getText(), txt_country.getText(), txt_phone.getText(),
							txt_mail.getText(), rdbtnYes.isSelected()))
						JOptionPane.showMessageDialog(ManageCustomer.this, "Customer has been created", "Confirmation",
								JOptionPane.PLAIN_MESSAGE);

				} else {
					System.out.println("Some data is missing");
				}
			}
		});

		btn_createCustomer.setBounds(614, 539, 122, 32);
		panel_createCustomer.add(btn_createCustomer);

		Panel panel_showCustomer = new Panel();

		tabbedPane.addTab("Show Customer", null, panel_showCustomer, null);
		panel_showCustomer.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 726, 560);
		panel_showCustomer.add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);

		table.getTableHeader().setReorderingAllowed(false);

		String[] columnNames = { "ID", "Name", "Address", "Zipcode", "City", "Country", "Phone", "Mail", "Private?" };

		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);

		JPanel panel_editCustomer = new JPanel();
		tabbedPane.addTab("Edit Customer", null, panel_editCustomer, null);
		panel_editCustomer.setLayout(null);

		Label lbl_customerId = new Label("ID");
		lbl_customerId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_customerId.setBounds(10, 10, 129, 22);
		panel_editCustomer.add(lbl_customerId);

		txt_customerId = new JTextField();
		txt_customerId.setBounds(10, 38, 86, 20);
		panel_editCustomer.add(txt_customerId);
		txt_customerId.setColumns(10);

		JButton btnSearch = new JButton("Search");

		btnSearch.setBounds(102, 37, 89, 21);
		panel_editCustomer.add(btnSearch);

		Label lbl_eName = new Label("Name");
		lbl_eName.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_eName.setBounds(10, 105, 129, 22);
		panel_editCustomer.add(lbl_eName);

		txt_eName = new JTextField();
		txt_eName.setEnabled(false);
		txt_eName.setColumns(10);
		txt_eName.setBounds(10, 133, 404, 20);
		panel_editCustomer.add(txt_eName);

		Label lbl_eAddress = new Label("Address");
		lbl_eAddress.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_eAddress.setBounds(10, 160, 197, 22);
		panel_editCustomer.add(lbl_eAddress);

		txt_eAddress = new JTextField();
		txt_eAddress.setEnabled(false);
		txt_eAddress.setColumns(10);
		txt_eAddress.setBounds(10, 188, 197, 20);
		panel_editCustomer.add(txt_eAddress);

		Label lbl_eZipcode = new Label("Zipcode");
		lbl_eZipcode.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_eZipcode.setBounds(217, 160, 59, 22);
		panel_editCustomer.add(lbl_eZipcode);

		txt_eZipcode = new JTextField();
		txt_eZipcode.setEnabled(false);
		txt_eZipcode.setColumns(10);
		txt_eZipcode.setBounds(217, 188, 59, 20);
		panel_editCustomer.add(txt_eZipcode);

		Label lbl_eCity = new Label("City");
		lbl_eCity.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_eCity.setBounds(286, 160, 59, 22);
		panel_editCustomer.add(lbl_eCity);

		txt_eCity = new JTextField();
		txt_eCity.setEnabled(false);
		txt_eCity.setColumns(10);
		txt_eCity.setBounds(286, 188, 128, 20);
		panel_editCustomer.add(txt_eCity);

		Label lbl_eCountry = new Label("Country");
		lbl_eCountry.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_eCountry.setBounds(10, 229, 197, 22);
		panel_editCustomer.add(lbl_eCountry);

		txt_eCountry = new JTextField();
		txt_eCountry.setEnabled(false);
		txt_eCountry.setColumns(10);
		txt_eCountry.setBounds(10, 257, 197, 20);
		panel_editCustomer.add(txt_eCountry);

		Label lbl_ePhone = new Label("Phone");
		lbl_ePhone.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_ePhone.setBounds(10, 288, 197, 22);
		panel_editCustomer.add(lbl_ePhone);

		txt_ePhone = new JTextField();
		txt_ePhone.setEnabled(false);
		txt_ePhone.setColumns(10);
		txt_ePhone.setBounds(10, 316, 197, 20);
		panel_editCustomer.add(txt_ePhone);

		Label lbl_eMail = new Label("Mail");
		lbl_eMail.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_eMail.setBounds(217, 288, 197, 22);
		panel_editCustomer.add(lbl_eMail);

		txt_eMail = new JTextField();
		txt_eMail.setEnabled(false);
		txt_eMail.setColumns(10);
		txt_eMail.setBounds(217, 316, 197, 20);
		panel_editCustomer.add(txt_eMail);

		Label lbl_ePrivateCustomer = new Label("Private cutomer?");
		lbl_ePrivateCustomer.setFont(new Font("Dialog", Font.PLAIN, 15));
		lbl_ePrivateCustomer.setBounds(10, 357, 197, 22);
		panel_editCustomer.add(lbl_ePrivateCustomer);

		JRadioButton rdbtn_ePrivateCustomerYes = new JRadioButton("Yes");
		rdbtn_ePrivateCustomerYes.setEnabled(false);
		rdbtn_ePrivateCustomerYes.setBounds(10, 385, 59, 23);
		panel_editCustomer.add(rdbtn_ePrivateCustomerYes);

		JRadioButton rdbtn_ePrivateCustomerNo = new JRadioButton("No");
		rdbtn_ePrivateCustomerNo.setEnabled(false);
		rdbtn_ePrivateCustomerNo.setSelected(true);
		rdbtn_ePrivateCustomerNo.setBounds(71, 385, 59, 23);
		panel_editCustomer.add(rdbtn_ePrivateCustomerNo);

		// Group the radio buttons.
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rdbtn_ePrivateCustomerYes);
		group2.add(rdbtn_ePrivateCustomerNo);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = customerController.getCustomer(Integer.parseInt(txt_customerId.getText()));
				if (customer != null) {
					customer.setAddress(txt_eAddress.getText());
					customer.setName(txt_eName.getText());
					customer.setCity(txt_eCity.getText());
					customer.setCountry(txt_eCountry.getText());
					customer.setPhone(txt_ePhone.getText());
					customer.setEmail(txt_eMail.getText());
					customer.setIsPrivate(rdbtn_ePrivateCustomerYes.isSelected());
					customer.setZipcode(txt_eZipcode.getText());
					if (customerController.updateCustomer(customer))
						JOptionPane.showMessageDialog(ManageCustomer.this, "Customer has been updated", "Confirmation",
								JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(614, 539, 122, 32);
		panel_editCustomer.add(btnUpdate);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = customerController.getCustomer(Integer.parseInt(txt_customerId.getText()));
				if (customer != null) {
					customerController.deleteCustomer(customer.getId());
					JOptionPane.showMessageDialog(ManageCustomer.this, "Customer has been deleted", "Confirmation",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(482, 539, 122, 32);
		panel_editCustomer.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(0, 608, 117, 29);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // you can't see me!
				dispose();
				new CreateOrder().setVisible(true);;
			}
		});
		// frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// Just do smth when the "ManageCustomer" tab was pressed
				if (tabbedPane.getSelectedIndex() == 1) {
					// Refresh the table with current data
					// Clear table
					model.setRowCount(0);

					// Fill table with all customers
					ArrayList<Object> customerList = customerController.getAllCustomers();
					for (int i = 0; i < customerList.size(); i++) {
						model.addRow((Object[]) customerList.get(i));
					}
				} else if (tabbedPane.getSelectedIndex() == 2) {
					txt_eName.setText("");
					txt_eAddress.setText("");
					txt_eZipcode.setText("");
					txt_eCity.setText("");
					txt_eCountry.setText("");
					txt_ePhone.setText("");
					txt_eMail.setText("");

					txt_eName.setEnabled(false);
					txt_eAddress.setEnabled(false);
					txt_eZipcode.setEnabled(false);
					txt_eCity.setEnabled(false);
					txt_eCountry.setEnabled(false);
					txt_ePhone.setEnabled(false);
					txt_eMail.setEnabled(false);
					rdbtn_ePrivateCustomerYes.setEnabled(false);
					rdbtn_ePrivateCustomerNo.setEnabled(false);

					btnDelete.setEnabled(false);
					btnUpdate.setEnabled(false);
				}
			}
		});

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txt_customerId.getText().equals("")) {
					Customer customer = customerController.getCustomer(Integer.parseInt(txt_customerId.getText()));
					if (customer != null) {

						txt_eName.setText(customer.getName());
						txt_eAddress.setText(customer.getAddress());
						txt_eZipcode.setText(customer.getZipcode());
						txt_eCity.setText(customer.getCity());
						txt_eCountry.setText(customer.getCountry());
						txt_ePhone.setText(customer.getPhone());
						txt_eMail.setText(customer.getEmail());
						rdbtn_ePrivateCustomerYes.setSelected(customer.isPrivate());

						txt_eName.setEnabled(true);
						txt_eAddress.setEnabled(true);
						txt_eZipcode.setEnabled(true);
						txt_eCity.setEnabled(true);
						txt_eCountry.setEnabled(true);
						txt_ePhone.setEnabled(true);
						txt_eMail.setEnabled(true);
						rdbtn_ePrivateCustomerYes.setEnabled(true);
						rdbtn_ePrivateCustomerNo.setEnabled(true);

						btnDelete.setEnabled(true);
						btnUpdate.setEnabled(true);
					}
				}
			}
		});
	}
}