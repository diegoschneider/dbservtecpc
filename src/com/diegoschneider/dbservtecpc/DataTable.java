package com.diegoschneider.dbservtecpc;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class DataTable extends JTable {

	private static final long serialVersionUID = 1L;

	public DataTable() {
		super();
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		        	int id = GetIDAtRow(row);
		        	OpenEditDialog(id);
		        }
		    }
		});
	}
	
	public void OpenEditDialog() throws IndexOutOfBoundsException {
		int id = GetIDAtRow(getSelectedRow());
		OpenEditDialog(id);
	}
	
	public void OpenEditDialog(int id) {
		
	}
	
	public int GetIDAtRow(int row) throws IndexOutOfBoundsException {
		Object data = (Object) getValueAt(row, 0);
    	int id = Integer.parseInt(data.toString());
    	return id;
	}
}
