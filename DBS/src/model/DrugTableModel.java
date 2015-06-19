package model;

/**
 * Created by marcelforgac on 2.4.15.
 */

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class DrugTableModel implements TableModel {

	String[] columnNames = new String[] { "Check","Drug name", "Count", "Drug type"};
	private ArrayList elements = new ArrayList();

	public DrugTableModel(ArrayList<Drug> elements) {
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
		switch (columnIndex) {
			case 0: return Boolean.class;
			default: return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0: return true;
			default: return false;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Drug element = (Drug) elements.get(rowIndex);

		switch (columnIndex) {
			case 0: return element.isState();
			case 1: return element.getName();
			case 2: return element.getCount();
			case 3: return element.getDrug_type();
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Drug element = (Drug) elements.get(rowIndex);

		switch (columnIndex) {
			case 0:
				element.setState((Boolean) aValue);
				break;
			default: break;
		}

	}

	public Drug getDrugs(int rowIndex) {
		return (Drug) elements.get(rowIndex);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}
}
