package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class Login extends JFrame {
	private JPanel rootPanel;
	public JTextField textField1;
	public JButton loginButton;
	public JButton registrationButton;
	public JLabel label;

	public Login() {

		super("Login");
		setContentPane(rootPanel);
		pack();
		setMinimumSize(new Dimension(150, 100));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
