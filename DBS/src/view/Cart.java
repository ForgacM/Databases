package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marcelforgac on 5.4.15.
 */
public class Cart extends JFrame {
	public JTable table1;
	public JList list1;
	private JPanel rootPanel;
	public JButton buyButton;

	public Cart() {
		super("Cart");
		setContentPane(rootPanel);
		pack();
		setMinimumSize(new Dimension(300, 300));
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
