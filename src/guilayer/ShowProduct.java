package guilayer;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import ctrllayer.ProductController;
import modlayer.Product;

public class ShowProduct extends JPanel {

	private JTable table;
	private DefaultTableModel model;
	
	public ShowProduct() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 726, 560);
		add(scrollPane);
		
		table = new JTable(new ProductModel());
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		String[] columnNames = {
				"ID",
				"Name",
                "Address",
                "Zipcode",
                "City",
                "Country",
                "Phone",
                "Mail",
                "Private?"};

		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
//		table.setModel(model);
	}
	class ProductModel extends AbstractTableModel {
		private String[] columnNames = { "ID", "Name", "Purchase price", "Sales price", "Rent price", "Country of origin", "Description", "stock" };
		private ArrayList<Product> ddata = new ProductController().searchProducts("");

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
				return ddata.get(row).getPurchasePrice();
			else if (col == 4)
				return ddata.get(row).getRentPrice();
			else if (col == 5)
				return ddata.get(row).getCountryOfOrigin();
			else if (col == 6)
				return ddata.get(row).getDesc();
			else if (col == 7)
				return ddata.get(row).getStock();
			return null;
		}

		

		public Class getColumnClass(int c) {
			
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
			return false;
		}
	}
}
