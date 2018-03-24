package guilayer.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JPanel {
	
	private int itemHeight;
	private Color activeSignColour;
	private Color itemBackgroundColour;
	private Color activeItemBackgroundColour;
	private List<MenuItemListener> listeners;
	private List<JPanel> items;
	private int activeItemIndex;
	private JPanel activeSign;
	
	public Menu() {
		this((LayoutManager)null);
	}
	public Menu(LayoutManager lm) {
		super(lm);
		
		itemHeight = 64;
		itemBackgroundColour = Color.DARK_GRAY;
		activeItemBackgroundColour = Color.GRAY;
		listeners = new ArrayList<>();
		items = new ArrayList<>();
		activeItemIndex = -1;
		
		initialize();
	}

	public void initialize() {
		
		activeSign = new JPanel();
		activeSign.setLayout(null);
		activeSign.setBounds(0, 0, 4, itemHeight);
		activeSign.setBackground(activeSignColour);
		activeSign.setVisible(false);
		this.add(activeSign);
	}
	public int getItemHeight() {
		return itemHeight;
	}
	public Color getActiveSignBackground() {
		return activeSignColour;
	}
	public Color getActiveItemBackground() {
		return activeItemBackgroundColour;
	}
	public Color getItemBackground() {
		return itemBackgroundColour;
	}
	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
		calculateLayout();
	}
	public void setActiveSignBackground(Color activeSignColour) {
		this.activeSignColour = activeSignColour;
	}
	public void setItemBackground(Color itemBackgroundColour) {
		this.itemBackgroundColour = itemBackgroundColour;
	}
	public void setActiveItemBackground(Color activeItemBackgroundColour) {
		this.activeItemBackgroundColour = activeItemBackgroundColour;
	}
	public void add(String name) {
		JPanel item = new JPanel();
		item.setBackground(itemBackgroundColour);
		item.setLayout(null);
		this.add(item);
		
		JLabel label = new JLabel(name);
		label.setForeground(this.getForeground());
		label.setFont(this.getFont());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		item.add(label);
		
		int currentIndex = items.size();
		if (activeItemIndex < 0) {
			activeItemIndex = currentIndex;
		}
		
		items.add(item);
		
		calculateLayout();
		this.repaint();
		this.doLayout();
		
		item.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				activeItemIndex = currentIndex;
				fireMenuItemListeners(currentIndex);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				item.setBackground(activeItemBackgroundColour);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				item.setBackground(itemBackgroundColour);
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});			
	}
	private void calculateLayout() {
		int itemsHeight = items.size() * itemHeight;
		int menuHeight = this.getHeight();
		int menuWidth = this.getWidth();
		if (menuHeight >= itemsHeight) {
			int y = (menuHeight - itemsHeight) / 2;
			JPanel item;
			for (int i = 0; i < items.size(); i++) {
				item = items.get(i);
				item.setBounds(0, y, menuWidth, itemHeight);
				item.getComponent(0).setBounds(0, 0, menuWidth, itemHeight);
				
				if (activeItemIndex == i) {
					activeSign.setBounds(4, y, 8, itemHeight);
				}
				
				y += itemHeight;
			}
		}
	}
	public boolean addMenuItemListener(MenuItemListener l) {
		return listeners.add(l);
	}
	public boolean removeMenuItemListener(MenuItemListener l) {
		return listeners.remove(l);
	}
	private void fireMenuItemListeners(int index) {
		for (MenuItemListener l : listeners) {
			l.menuItemClicked(index);
		}
	}
}
