package guilayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageProduct {

	private JFrame frame;
	private JPanel contentPane;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManageProduct window = new ManageProduct();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public ManageProduct() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 777, 665);
		frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 751, 610);
		contentPane.add(tabbedPane);
		
		JPanel createProduct = new CreateProduct();
		tabbedPane.addTab("Create Product", null, createProduct, null);
		
		JPanel showProduct = new ShowProduct();
		tabbedPane.addTab("Show Product", null, showProduct, null);
		
		JPanel editProduct = new EditProduct();
		tabbedPane.addTab("Edit Product", null, editProduct, null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); // you can't see me!
				frame.dispose();
				new CreateOrder().setVisible(true);;
			}
		});
		btnBack.setBounds(15, 608, 117, 29);
		contentPane.add(btnBack);
	}
}
