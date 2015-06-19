package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class MainWindow extends JFrame {
	private JPanel rootPanel;
	private JPanel headerPanel;
	private JPanel footerPanel;
	public JButton loginButton;
	public JLabel LoggedName;
	public JButton cartButton;
	public JTable table1;
	public JButton addToCartButton;
	public JButton addNewButton;
	public JButton deleteButton;
	public JButton statButton;
	public JTextArea textArea1;
	public JPanel statPanel;

	public MainWindow() {

		super("Drug Store");
		setContentPane(rootPanel);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
		LoggedName.setVisible(false);
		cartButton.setVisible(false);
		addToCartButton.setVisible(false);
		addNewButton.setVisible(false);
		deleteButton.setVisible(false);
		statButton.setVisible(false);
		statPanel.setVisible(false);
		setMinimumSize(new Dimension(1000, 400));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
