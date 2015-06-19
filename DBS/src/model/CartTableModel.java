package model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by marcelforgac on 5.4.15.
 */
public class CartTableModel implements TableModel {

	String[] columnNames = new String[] { "Drug name", "Count", "Drug type"};
	private ArrayList elements = new ArrayList();

	public CartTableModel(ArrayList<Items> elements) {
		this.elements = elements;
	}

	@Override
	public int getRowCount() {
		return elements.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		model.Items itemElement = (model.Items) elements.get(rowIndex);

		switch (columnIndex) {
			case 0: return itemElement.getName();
			case 1: return itemElement.getCount();
			case 2: return itemElement.getDrug_type();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}

	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}
}
