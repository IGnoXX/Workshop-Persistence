package guilayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import guilayer.mgprodpanels.*;

public class ManageProduct {
	
	private JFrame frame;
	private JPanel contentPane;
	private int prevId;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProduct window = new ManageProduct();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManageProduct() {
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
		
		CreateProduct createProduct = new CreateProduct();
		tabbedPane.addTab("Create Product", null, createProduct, null);
		
		ShowProduct showProduct = new ShowProduct();
		tabbedPane.addTab("Show Product", null, showProduct, null);
		
		EditProduct editProduct = new EditProduct();
		tabbedPane.addTab("Edit Product", null, editProduct, null);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				((ResetablePanel)tabbedPane.getSelectedComponent()).reopen();
				((ResetablePanel)tabbedPane.getComponent(prevId)).reset();
				
				prevId = tabbedPane.getSelectedIndex();
	         }
		});
	}
}
