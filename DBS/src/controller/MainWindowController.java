package controller;

import dao.*;
import model.Drug;
import model.DrugTableModel;
import model.User;
import view.Cart;
import view.Login;
import view.MainWindow;
import view.NewDrug;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class MainWindowController {

	private MainWindow view;

	User currentUser=null;
	DrugDao drugDao;
	UserSessionDao session = new UserSessionDao();
	ItemsDao itemsDao = new ItemsDao();
	OrderDao orderDao = new OrderDao();

	ArrayList<Drug> drugList = new ArrayList<>();

	public MainWindowController(final MainWindow view) {
		this.view = view;
		UsersDao userDbc = new UsersDao();
		drugDao = new DrugDao();

		this.currentUser = userDbc.getUserById(session.getLoggedUserId());

		if (currentUser.getFirstName() == null) {
			reloadElements();
			view.LoggedName.setText("Guest");
			view.LoggedName.setVisible(true);
			view.loginButton.setText("Login");
			view.loginButton.addActionListener(e -> {
				new LoginController(new Login());
				view.dispose();
			});
		} else {
			if (currentUser.getFirstName().equals("Lekar") && currentUser.getUserId() == 1) {
				reloadElements();
				view.LoggedName.setText(currentUser.getFirstName());
				view.LoggedName.setVisible(true);
				view.addNewButton.setVisible(true);
				view.deleteButton.setVisible(true);
				view.statButton.setVisible(true);
				view.textArea1.setText("");
				view.loginButton.setText("Logout");
				view.loginButton.addActionListener(e -> {
					session.logoutUser(currentUser);
					view.dispose();
					new MainWindowController(new MainWindow());

				});
			} else {
				reloadElements();
				view.addToCartButton.setVisible(true);
				view.LoggedName.setText(currentUser.getFirstName());
				view.LoggedName.setVisible(true);
				view.cartButton.setVisible(true);
				view.loginButton.setText("Logout");
				view.loginButton.addActionListener(e -> {
					session.logoutUser(currentUser);
					view.dispose();
					new MainWindowController(new MainWindow());

				});
			}
		}

		view.addToCartButton.addActionListener(e -> {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			String dates = dateFormat.format(date);
			int serial = dates.hashCode();

			orderDao.createOrder(dates, serial, currentUser);
			for (Drug d : drugList) {
				if (d.isState()) {
					itemsDao.orderItems(d, orderDao.getOrderIdBySerialNumber(serial));
				}
			}
		});

		view.cartButton.addActionListener(e -> {
			new CartController(new Cart(),currentUser.getFirstName());
		});

		view.deleteButton.addActionListener(e -> {
			for (Drug d : drugList) {
				if (d.isState()) {
					drugDao.deleteDrug(d);
				}
			}
			reloadElements();
		});

		view.addNewButton.addActionListener(e -> {
			new NewDrugController(new NewDrug());
		});

		view.statButton.addActionListener(e -> {
			if (view.statPanel.isVisible()) {
				view.statPanel.setVisible(false);
			} else view.statPanel.setVisible(true);

			String statList = itemsDao.getStat();

			view.textArea1.append("Najviac nakupované lieky: \n");
			view.textArea1.append(statList + "\n");

			view.textArea1.append("Priemerný počet položiek v objednávke: \n");
			view.textArea1.append(String.valueOf(itemsDao.getAvgFromItems()));

		});




	}

	public void reloadElements() {

		drugList = drugDao.getAllDrugs();

		view.table1.setModel(new DrugTableModel(drugList));

		view.table1.getColumn(view.table1.getColumnName(0)).setMaxWidth(45); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(0)).setCellEditor(new TaskTableCellEditor(new JCheckBox()));
		view.table1.getColumn(view.table1.getColumnName(1)).setMinWidth(200); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(2)).setMinWidth(120); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(2)).setMaxWidth(120); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(3)).setMinWidth(120); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(3)).setMaxWidth(120); //can't be changed in view
	}

	class TaskTableCellEditor extends DefaultCellEditor {

		JCheckBox checkBox = new JCheckBox();

		public TaskTableCellEditor(final JCheckBox checkBox) {
			super(checkBox);
			this.checkBox = checkBox;

			checkBox.addActionListener(e -> {

				Drug drug = drugList.get(view.table1.getSelectedRow());
				drug.setState(!(Boolean) view.table1.getModel().getValueAt(view.table1.getSelectedRow(), 0));
				drugDao.updateState(drug);

				reloadElements();
				fireEditingStopped();
			});
		}
	}
}
