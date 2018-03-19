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

public class ManageCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_address;
	private JTextField txt_zipcode;
	private JTextField txt_city;
	private JTextField txt_country;
	private JTextField txt_phone;
	private JTextField txt_mail;
	private JTable table;

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
		txt_name.setBounds(10, 38, 335, 20);
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
		txt_city.setBounds(286, 110, 59, 20);
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
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnYes);
	    group.add(rdbtnNo);
	    
	    JButton btn_createCustomer = new JButton("Create");
	    btn_createCustomer.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		System.out.println("FUCK");
	    	}
	    });
	    btn_createCustomer.setBounds(614, 539, 122, 32);
	    panel_createCustomer.add(btn_createCustomer);
		
		Panel panel_manageCustomer = new Panel();
		tabbedPane.addTab("Manage Customer", null, panel_manageCustomer, null);
		panel_manageCustomer.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 726, 560);
		panel_manageCustomer.add(scrollPane);
		
		String[] columnNames = {"Name",
                "Address",
                "Zipcode",
                "City",
                "Country",
                "Phone",
                "Mail",
                "Private customer?"};
	
		Object[][] data = {
			    {"John Doe", "An us str.", "9200", "Aalborg", "Denmark", "+45 777 777 77",
			     "huan@son.com", new Boolean(false)}
			    
			};
		
		table = new JTable(data, columnNames);
		scrollPane.setViewportView(table);
	}
}
