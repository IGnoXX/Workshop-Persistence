package guilayer.mgcustpanels;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import ctrllayer.CustomerController;
import guilayer.ResetablePanel;
import modlayer.Customer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowCustomer extends JPanel implements ResetablePanel {

	private CustomerController customerCtrl;
	private JTable table;
	private CustomerTable model;
	private int selectedId;
	private JButton btn_delete;
	
	public ShowCustomer() {
		customerCtrl = new CustomerController();
		selectedId = -1;
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 726, 560);
		add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		model = new CustomerTable();
		model.setCustomers(customerCtrl.getCustomers());
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		btn_delete = new JButton("Delete selected Customer");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean success = customerCtrl.deleteCustomer(model.getCustomerAt(selectedId));
				if (!success) {
					JOptionPane.showMessageDialog(null,
						    "An error occured while deleting the Customer!",
						    "Error!",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				reset();
				reopen();
				JOptionPane.showMessageDialog(null,
					"The Customer was successfully deleted!",
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
		customerCtrl = new CustomerController();
		model.setCustomers(customerCtrl.getCustomers());
		model.update();
	}
	private class CustomerTable extends AbstractTableModel {
		
		private String[] columns = new String[] { "Id", "Name", "Phone", "Email", "Private" };
		private ArrayList<Customer> customers;
		
		public CustomerTable() {
			this(new ArrayList<Customer>());
		}
		public CustomerTable(ArrayList<Customer> customers) {
			this.customers = customers;
			update();
		}

		@Override
		public int getRowCount() {
			return customers.size();
		}
		@Override
		public int getColumnCount() {
			return 5;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			Customer customer = customers.get(rowIndex);
			
			switch(columnIndex) {
				case 0:
					return customer.getId();
				case 1:
					return customer.getName();
				case 2:
					return customer.getPhone();
				case 3:
					return customer.getEmail();
				case 4:
					return customer.getIsPrivate() ? "Yes" : "No";
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
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
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
		public Customer getCustomerAt(int rowIndex) {
			return customers.get(rowIndex);
		}
		public void setCustomers(ArrayList<Customer> customers) {
			this.customers = customers;
			update();
		}
	}
}
