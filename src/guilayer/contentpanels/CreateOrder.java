package guilayer.contentpanels;

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

import ctrllayer.CustomerController;
import ctrllayer.OrderCreator;
import ctrllayer.ProductController;
import modlayer.Customer;
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
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;

public class CreateOrder extends JPanel implements ActionListener {

	private JTable table;
	private JTextField txtNameOrId;
	private ProductController pc;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JLabel subtotal;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblDiscount;
	private JLabel delivery;
	private JLabel discount;
	private JTextField txtCustomerId;
	private JLabel lblCustomer;
	private JLabel lblCustomerName;
	private JButton btnFinalize;
	private Customer c;
	private OrderCreator oc;
	private JLabel lblTotal;
	private JLabel total;

	public CreateOrder() {
		super();
		pc = new ProductController();
		oc = new OrderCreator();

		this.setBounds(100, 100, 777, 665);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		txtNameOrId = new JTextField();
		txtNameOrId.setText("name or id");
		txtNameOrId.setBounds(10, 22, 130, 26);
		this.add(txtNameOrId);
		txtNameOrId.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(140, 22, 69, 29);
		btnSearch.setActionCommand("search");
		btnSearch.addActionListener(this);
		this.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 250, 473);
		this.add(scrollPane);

		table = new JTable(new ProductModel());
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(503, 122, 250, 300);
		this.add(scrollPane_1);

		table_1 = new JTable(new BasketModel());
		scrollPane_1.setViewportView(table_1);

		subtotal = new JLabel("");
		subtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		subtotal.setBounds(581, 434, 172, 16);
		this.add(subtotal);

		lblNewLabel_1 = new JLabel("Subtotal:");
		lblNewLabel_1.setBounds(503, 434, 61, 16);
		this.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Delivery:");
		lblNewLabel_2.setBounds(503, 451, 61, 16);
		this.add(lblNewLabel_2);

		lblDiscount = new JLabel("Discount:");
		lblDiscount.setBounds(503, 467, 61, 16);
		this.add(lblDiscount);

		delivery = new JLabel("");
		delivery.setHorizontalAlignment(SwingConstants.RIGHT);
		delivery.setBounds(591, 451, 162, 16);
		this.add(delivery);

		discount = new JLabel("");
		discount.setHorizontalAlignment(SwingConstants.RIGHT);
		discount.setBounds(601, 467, 152, 16);
		this.add(discount);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(503, 485, 250, 6);
		this.add(separator);

		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblTotal.setBounds(503, 495, 61, 16);
		this.add(lblTotal);

		total = new JLabel("0");
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		total.setBounds(607, 495, 146, 16);
		this.add(total);

		txtCustomerId = new JTextField();
		txtCustomerId.setText("Customer ID");
		txtCustomerId.setBounds(503, 43, 172, 26);
		this.add(txtCustomerId);
		txtCustomerId.setColumns(10);

		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(672, 43, 81, 29);
		btnSelect.setActionCommand("customerSelect");
		btnSelect.addActionListener(this);
		this.add(btnSelect);

		lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblCustomer.setBounds(503, 68, 99, 26);
		this.add(lblCustomer);

		lblCustomerName = new JLabel("");
		lblCustomerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerName.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblCustomerName.setBounds(562, 84, 191, 26);
		this.add(lblCustomerName);

		btnFinalize = new JButton("Finalize");
		btnFinalize.setBounds(562, 535, 117, 29);
		btnFinalize.setActionCommand("finish");
		btnFinalize.addActionListener(this);
		this.add(btnFinalize);

		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = table.getInputMap(condition);
		ActionMap actionMap = table.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DELETE");
		actionMap.put("DELETE", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				((BasketModel) table_1.getModel()).removeFromBasket(table_1.getSelectedRow());
				updateBasket();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if ("search".equals(e.getActionCommand())) {
			((ProductModel) table.getModel()).search(txtNameOrId.getText());
		} else if ("customerSelect".equals(e.getActionCommand())) {
			try {
				c = new CustomerController().getCustomer(Integer.valueOf(txtCustomerId.getText()));
				lblCustomerName.setText(c.getName());
			} catch (Exception ignored) {
			}
		} else if ("finish".equals(e.getActionCommand())) {
			System.out.println("finalize order");
			oc.finalizeOrder();
		}
	}

	public void updateBasket() {
		((BasketModel) table_1.getModel()).update();
		subtotal.setText(String.valueOf(oc.getPrice()));
		delivery.setText(String.valueOf(oc.getDeliveryPrice()));
		discount.setText(String.valueOf(oc.getDiscount()));
		total.setText(String.valueOf(oc.getFinalPrice()));
	}

	class ProductModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Name", "price", "Stock" };
		private ArrayList<Product> ddata = new ArrayList();

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return ddata.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
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
			ddata = pc.searchProducts(name);
			fireTableDataChanged();
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			oc.addProduct(ddata.get(row).getId());
			CreateOrder.this.updateBasket();
			return false;
		}
	}

	class BasketModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Name", "price", "Amount" };

		private ArrayList<OrderProduct> data = new ArrayList();

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
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
		}

		public void update() {
			data = oc.getOrderProducts();
			fireTableDataChanged();
		}

		public void setValueAt(Object value, int row, int col) {
			oc.updateProduct(data.get(row).getProduct().getId(), (int) value);
			CreateOrder.this.updateBasket();
		}

		public void removeFromBasket(int row) {
			oc.removeProduct(data.get(row).getProduct().getId());
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
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
			CreateOrder.this.subtotal.setText(String.valueOf(price));
			if (c != null) {
				if (c.isPrivate() && price > 2500) {
					delivery.setText("0");
					discount.setText("0");
				} else if (!c.isPrivate() && price > 1500) {
					discount.setText(String.valueOf(0 - price * 0.10));
					price *= 0.90;
					delivery.setText("45");
					price += 45;
				} else {
					delivery.setText("45");
					price += 45;
					discount.setText("0");
				}

			} else {
				delivery.setText("45");
				price += 45;
				discount.setText("0");
			}
			CreateOrder.this.total.setText(String.valueOf(price));
		}
	}
}
