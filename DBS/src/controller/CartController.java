package controller;

import dao.DrugDao;
import dao.ItemsDao;
import dao.OrderDao;
import model.CartTableModel;
import model.Items;
import model.Order;
import view.Cart;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by marcelforgac on 5.4.15.
 */
public class CartController {

	private Cart view;
	OrderDao orderDao = new OrderDao();
	ItemsDao itemsDao = new ItemsDao();
	DrugDao drugDao = new DrugDao();

	ArrayList<Order> orderList = new ArrayList<>();
	ArrayList<Items> itemList = new ArrayList<>();

	public CartController(final Cart view, String currentUserName) {
		this.view = view;

		orderList = orderDao.getOrderByPatientName(currentUserName);

		DefaultListModel listModel = new DefaultListModel();
		for (Order o: orderList ) {
			listModel.addElement("Objednavka č " + o.getSerialNumber());
		}
		view.list1.setModel(listModel);

		view.list1.addListSelectionListener(e -> {
			if (! e.getValueIsAdjusting()) {
			String selected = (String) view.list1.getSelectedValue();
				int orderId = orderDao.getOrderIdBySerialNumber(Integer.parseInt(selected.substring(13)));
				itemList = itemsDao.getAllItemsByOrder(orderId);
			reloadElements(itemList);
			view.buyButton.addActionListener(a -> {
				orderDao.deleteOrder(orderId);
				view.dispose();
				view.setVisible(false);
				new CartController(new Cart(), currentUserName);
			});
			}
		});

	}

	public void reloadElements(ArrayList<Items> drugses) {

		view.table1.setModel(new CartTableModel(drugses));

		view.table1.getColumn(view.table1.getColumnName(0)).setMinWidth(200); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(1)).setMinWidth(120); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(1)).setMaxWidth(120); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(2)).setMinWidth(120); //can't be changed in view
		view.table1.getColumn(view.table1.getColumnName(2)).setMaxWidth(120); //can't be changed in view
	}
}
