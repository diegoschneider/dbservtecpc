package com.diegoschneider.dbservtecpc;

import java.sql.*;
import java.util.*;

import javax.swing.table.*;

public class FillTable extends AbstractTableModel{
 
	private static final long serialVersionUID = -912060609250881296L;
	private ResultSet rs;
	private int rowCount;
	private int columnCount;
	private ArrayList<Object[]> data=new ArrayList<Object[]>();
 
	/**
	 * TableModel
	 * Llena una tabla con los datos de una Query
	 * 
	 * @param _rs ResultSet desde el cual llenar la tabla
	 * @throws SQLException
	 */
	public FillTable(ResultSet _rs) throws SQLException {
		this.rs=_rs;
		ResultSetMetaData metaData=_rs.getMetaData();
		rowCount=0;
		columnCount=metaData.getColumnCount();
		while(_rs.next()) {
			Object[] row=new Object[columnCount];
			for(int j=0;j<columnCount;j++){
				row[j]=_rs.getObject(j+1);
			}
			data.add(row);
			rowCount++;
		}
	}
 
	public int getColumnCount(){
		return columnCount;
	}
 
	public int getRowCount(){
		return rowCount;
	}
 
	public Object getValueAt(int rowIndex, int columnIndex){
		Object[] row=(Object[]) data.get(rowIndex);
		return row[columnIndex];
	}
 
	public String getColumnName(int columnIndex){
		try{
			ResultSetMetaData metaData=rs.getMetaData();
			return metaData.getColumnName(columnIndex+1);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}