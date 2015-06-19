package controller;

import dao.DrugDao;
import model.Drug;
import view.NewDrug;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

/**
 * Created by marcelforgac on 12.4.15.
 */
public class NewDrugController {

	private NewDrug view;

	Drug drug = new Drug();

	public NewDrugController(NewDrug view) {
		this.view = view;

		DrugDao drugDao = new DrugDao();
		DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
		ArrayList<String> typeList = drugDao.getDrugTypes();
		for (String s : typeList) {
			defaultListModel.addElement(s);
		}
		view.list1.setModel(defaultListModel);

		if (view.textField1.getText().equals("") || view.textField2.getText().equals("")) {
			view.addButton.setEnabled(false);
		}

		view.textField1.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (view.textField1.getText().equals("") || view.textField2.getText().equals("")) {
					view.addButton.setEnabled(false);
				} else {
					view.addButton.setEnabled(true);
				}

			}
		});

		view.textField2.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (view.textField1.getText().equals("") || view.textField2.getText().equals("")){
					view.addButton.setEnabled(false);
				}
				else {
					view.addButton.setEnabled(true);
				}

			}
		});


		view.addButton.addActionListener(e -> {

			drug.setName(view.textField1.getText());
			drug.setCount(Integer.parseInt(view.textField2.getText()));

			if (view.onReceptCheckBox.isSelected()) {
				drug.setRecept(true);
			} else {
				drug.setRecept(false);
			}

			drug.setTypeIndex(view.list1.getSelectedIndex()+1);
			drugDao.addNewDrug(drug);
			view.dispose();
		});



	}
}
