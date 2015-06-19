package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by marcelforgac on 12.4.15.
 */
public class NewDrug extends JFrame {
	private JPanel rootPanel;
	public JButton addButton;
	public JCheckBox onReceptCheckBox;
	public JList list1;
	public JTextField textField1;
	public JTextField textField2;

	public NewDrug() {

		super("");

		setContentPane(rootPanel);
		pack();
		setMinimumSize(new Dimension(500, 300));
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
