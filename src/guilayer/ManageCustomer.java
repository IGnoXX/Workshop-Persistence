package guilayer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import guilayer.mgcustpanels.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ManageCustomer extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private int prevId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCustomer window = new ManageCustomer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManageCustomer() {
		prevId = 0;
		
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 777, 665);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 751, 610);
		contentPane.add(tabbedPane);
		
		CreateCustomer createCustomer = new CreateCustomer();
		tabbedPane.addTab("Create Customer", null, createCustomer, null);
		
		ShowCustomer showCustomer = new ShowCustomer();
		tabbedPane.addTab("Show Customer", null, showCustomer, null);
		
		EditCustomer editCustomer = new EditCustomer();
		tabbedPane.addTab("Edit Customer", null, editCustomer, null);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				((ResetablePanel)tabbedPane.getSelectedComponent()).reopen();
				((ResetablePanel)tabbedPane.getComponent(prevId)).reset();
				
				prevId = tabbedPane.getSelectedIndex();
	         }
		});
	}
}