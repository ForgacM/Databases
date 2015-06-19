package view;

import javax.swing.*;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class Registration extends JFrame {
	public JButton registerButton;
	public JFormattedTextField formattedTextField1;
	public JFormattedTextField formattedTextField2;
	public JFormattedTextField formattedTextField3;
	private JPanel rootPanel;

	public Registration() {

		super("Registration");
		setContentPane(rootPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
