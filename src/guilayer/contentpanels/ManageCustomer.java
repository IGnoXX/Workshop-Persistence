package guilayer.contentpanels;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import guilayer.mgcustpanels.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ManageCustomer extends JTabbedPane {

	private int prevId;

	public ManageCustomer() {
		super(JTabbedPane.TOP);
		prevId = 0;
		
		initialize();
	}

	private void initialize() {
		
		CreateCustomer createCustomer = new CreateCustomer();
		this.addTab("Create Customer", null, createCustomer, null);
		
		ShowCustomer showCustomer = new ShowCustomer();
		this.addTab("Show Customer", null, showCustomer, null);
		
		EditCustomer editCustomer = new EditCustomer();
		this.addTab("Edit Customer", null, editCustomer, null);
		
		ManageCustomer self = this;
		
		this.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				((ResetablePanel)self.getSelectedComponent()).reopen();
				((ResetablePanel)self.getComponent(prevId)).reset();
				
				prevId = self.getSelectedIndex();
	         }
		});
	}
}