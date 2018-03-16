package guilayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import ctrllayer.ProductController;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class CreateOrder extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNameOrId;
	private ProductController pc;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrder frame = new CreateOrder();
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
	public CreateOrder() {
		pc = new ProductController();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNameOrId = new JTextField();
		txtNameOrId.setText("name or id");
		txtNameOrId.setBounds(10, 22, 130, 26);
		contentPane.add(txtNameOrId);
		txtNameOrId.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(140, 22, 69, 29);
		btnSearch.setActionCommand("search");
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 244, 300);
		contentPane.add(scrollPane);
		
		table = new JTable(new ProductModel());
		scrollPane.setViewportView(table);
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("search".equals(e.getActionCommand())) {
			((ProductModel) table.getModel()).search(txtNameOrId.getText());
		}
	}
	
	class ProductModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Name", "price", "Stock"};
		private Object[][] data = new Object[0][4];

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public void search(String name) {
			data = pc.getProductsData(name, new String[]{"id","name","sales_price,","stock"});
			fireTableDataChanged();
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {

			JOptionPane.showMessageDialog( CreateOrder.this,
				    "Added "+ data[row][1]+" to basket");
				return false;
		}
	}
}
