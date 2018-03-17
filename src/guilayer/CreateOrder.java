package guilayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import ctrllayer.ProductController;
import modlayer.OrderProduct;
import modlayer.Product;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class CreateOrder extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNameOrId;
	private ProductController pc;
	private ArrayList<Product> basket;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JLabel lblNewLabel;

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
		basket = new ArrayList();

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
		scrollPane.setBounds(10, 47, 250, 300);
		contentPane.add(scrollPane);

		table = new JTable(new ProductModel());
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(300, 47, 250, 300);
		contentPane.add(scrollPane_1);

		table_1 = new JTable(new BasketModel());
		scrollPane_1.setViewportView(table_1);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(300, 350, 172, 16);
		contentPane.add(lblNewLabel);

		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = table.getInputMap(condition);
		ActionMap actionMap = table.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DELETE");
		actionMap.put("DELETE", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// ((EmployeeModel)
				// table.getModel()).fireTableRowsDeleted(table.getSelectedRow(),
				// table.getSelectedRow());
				// table.removeRowSelectionInterval(table.getSelectedRow(),
				// table.getSelectedRow());
				((BasketModel) table_1.getModel()).removeFromBasket(table_1.getSelectedRow());
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if ("search".equals(e.getActionCommand())) {
			((ProductModel) table.getModel()).search(txtNameOrId.getText());
		}
	}

	public void addToBasket(Product product) {
		((BasketModel) table_1.getModel()).add(product);
	}

	class ProductModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Name", "price", "Stock" };
		// private Object[][] data = new Object[0][4];
		private ArrayList<Product> ddata = new ArrayList();

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			// return data.length;
			return ddata.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			// return data[row][col];
			if (col == 0)
				return ddata.get(row).getId();
			else if (col == 1)
				return ddata.get(row).getName();
			else if (col == 2)
				return ddata.get(row).getSalesPrice();
			else if (col == 3)
				return ddata.get(row).getStock();
			return null;
		}

		public void search(String name) {
			// data = pc.getProductsData(name, new
			// String[]{"id","name","sales_price,","stock"});
			// ArrayList<Product> results = pc.searchProducts(name);
			// data= new Object[results.size()][4];
			// for(int i =0;i<results.size();i++) {
			// data[i][0]=results.get(i).getId();
			// data[i][1]=results.get(i).getName();
			// data[i][2]=results.get(i).getSalesPrice();
			// data[i][3]=results.get(i).getStock();
			// }
			ddata = pc.searchProducts(name);
			fireTableDataChanged();
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			CreateOrder.this.addToBasket(ddata.get(row));
			// JOptionPane.showMessageDialog(CreateOrder.this, "Addeded " +
			// ddata.get(row).getName() + " to basket");
			return false;
		}
	}

	class BasketModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Name", "price", "Amount" };
		// private Object[][] data = new Object[0][4];
		// private ArrayList<Product> data = new ArrayList();
		// private ArrayList<Integer> amount = new ArrayList();

		private ArrayList<OrderProduct> data = new ArrayList();

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			// return data.length;
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			// return data[row][col];
			if (col == 0)
				return data.get(row).getProduct().getId();
			else if (col == 1)
				return data.get(row).getProduct().getName();
			else if (col == 2)
				return data.get(row).getProduct().getSalesPrice();
			else if (col == 3)
				return data.get(row).getAmount();
			return null;
		}

		public void add(Product product) {
			boolean contains = true;
			for (OrderProduct p : data)
				if (p.getProduct().getId() == product.getId()) {
					contains = false;
					break;
				}
			if (contains) {
				data.add(new OrderProduct(product, 1));
				calculatePrice();
				fireTableDataChanged();
			}
		}
		// public void search(String name) {
		// // data = pc.getProductsData(name, new
		// // String[]{"id","name","sales_price,","stock"});
		// // ArrayList<Product> results = pc.searchProducts(name);
		// // data= new Object[results.size()][4];
		// // for(int i =0;i<results.size();i++) {
		// // data[i][0]=results.get(i).getId();
		// // data[i][1]=results.get(i).getName();
		// // data[i][2]=results.get(i).getSalesPrice();
		// // data[i][3]=results.get(i).getStock();
		// // }
		// data = pc.searchProducts(name);
		// fireTableDataChanged();
		// }

		public void setValueAt(Object value, int row, int col) {
			// EmployeeEditor editor = new EmployeeEditor(String.valueOf(data[row][0]));
			if ((int) value > data.get(row).getProduct().getStock())
				data.get(row).setAmount(data.get(row).getProduct().getStock());
			else
				data.get(row).setAmount((int) value);
			calculatePrice();
			fireTableCellUpdated(row, col);
			
			// if (col == 1) {
			// data[row][col] = value;
			// editor.setName(String.valueOf(value));
			// fireTableCellUpdated(row, col);
			// } else if (col == 2) {
			// data[row][col] = value;
			// editor.setAddress(String.valueOf(value));
			// fireTableCellUpdated(row, col);
			// } else if (col == 3) {
			// try {
			// editor.setAccessLevel(Integer.valueOf(String.valueOf(value)));
			// data[row][col] = value;
			// fireTableCellUpdated(row, col);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// } else if (col == 4) {
			// editor.setPassword(String.valueOf(value));
			// // fireTableCellUpdated(row, col);
			// data[row][col] = "updated";
			// }
		}

		public void removeFromBasket(int row) {
			data.remove(row);
			calculatePrice();
			fireTableDataChanged();
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			// JOptionPane.showMessageDialog(CreateOrder.this, "Addeded " +
			// ddata.get(row).getName() + " to basket");
			if (col == 3) {
				return true;
			}
			return false;
		}

		public void calculatePrice() {
			double price = 0.0;

			for (OrderProduct orderProduct : data) {
				price += orderProduct.getProduct().getSalesPrice() * orderProduct.getAmount();
			}
			CreateOrder.this.lblNewLabel.setText(String.valueOf(price));
		}
	}
}
