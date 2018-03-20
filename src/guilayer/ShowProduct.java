package guilayer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowProduct extends JPanel {

	private JTable table;
	private DefaultTableModel model;
	
	public ShowProduct() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 726, 560);
		add(scrollPane);
		
		table = new JTable();
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
		table.setModel(model);
	}
}
