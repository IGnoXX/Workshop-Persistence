package guilayer;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import ctrllayer.ProductController;
import modlayer.Product;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowProduct extends JPanel implements ManageProductPanel {

	private ProductController productCtrl;
	private JTable table;
	private ProductTable model;
	private int selectedId;
	private JButton btn_delete;
	
	public ShowProduct() {
		productCtrl = new ProductController();
		selectedId = -1;
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 726, 560);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		model = new ProductTable();
		model.setProducts(productCtrl.getProducts());
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btn_delete = new JButton("Delete selected Product");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean success = productCtrl.deleteProduct(model.getProductAt(selectedId));
				if (!success) {
					JOptionPane.showMessageDialog(null,
						    "An error occured while deleting the Product!",
						    "Error!",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				reset();
				reopen();
				JOptionPane.showMessageDialog(null,
					"The Product was successfully deleted!",
				    "Success!",
				    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn_delete.setEnabled(false);
		btn_delete.setBounds(10, 0, 726, 23);
		add(btn_delete);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();

		        int firstIndex = e.getFirstIndex();
		        int lastIndex = e.getLastIndex();

		        if (lsm.isSelectionEmpty()) {
		            selectedId = -1;
		            btn_delete.setEnabled(false);
		        } else {
		            int minIndex = lsm.getMinSelectionIndex();
		            int maxIndex = lsm.getMaxSelectionIndex();
		            for (int i = minIndex; i <= maxIndex; i++) {
		                if (lsm.isSelectedIndex(i)) {
		                    selectedId = i;
		                    break;
		                }
		            }
		            btn_delete.setEnabled(true);
		        }
			}
			
		});
	}
	@Override
	public void reset() {
		btn_delete.setEnabled(false);
		selectedId = -1;
	}
	@Override
	public void reopen() {
		productCtrl = new ProductController();
		model.setProducts(productCtrl.getProducts());
		model.update();
	}
	private class ProductTable extends AbstractTableModel {
		
		private String[] columns = new String[] { "Id", "Name", "Sales Price", "Rent Price", "Stock" };
		private ArrayList<Product> products;
		
		public ProductTable() {
			this(new ArrayList<Product>());
		}
		public ProductTable(ArrayList<Product> products) {
			this.products = products;
			update();
		}

		@Override
		public int getRowCount() {
			return products.size();
		}
		@Override
		public int getColumnCount() {
			return 5;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			Product product = products.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return product.getId();
				case 1:
					return product.getName();
				case 2:
					return product.getSalesPrice();
				case 3:
					return product.getRentPrice();
				case 4:
					return product.getStock();
			}
			
			return null;
		}
		@Override
		public String getColumnName(int columnIndex) {
			return columns[columnIndex];
		}
		@Override
		public Class getColumnClass(int columnIndex) {
			switch(columnIndex) {
				case 0:
					return int.class;
				case 1:
					return String.class;
				case 2:
					return double.class;
				case 3:
					return double.class;
				case 4:
					return int.class;
			}
			
			return null;
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
		public void update() {
			fireTableDataChanged();
		}
		public Product getProductAt(int rowIndex) {
			return products.get(rowIndex);
		}
		public void setProducts(ArrayList<Product> products) {
			this.products = products;
			update();
		}
	}
}
