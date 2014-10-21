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
	private ArrayList<Integer> id =new ArrayList<Integer>();
 
	/**
	 * TableModel
	 * Llena una tabla con los datos de una Query
	 * La primera columna debe ser el ID
	 * 
	 * @param _rs ResultSet desde el cual llenar la tabla
	 * @throws SQLException
	 */
	public FillTable(ResultSet _rs) throws SQLException {
		this.rs=_rs;
		ResultSetMetaData metaData=_rs.getMetaData();
		rowCount=0;
		columnCount=metaData.getColumnCount()-1;
		while(_rs.next()) {
			Object[] row=new Object[columnCount];
			id.add(Integer.parseInt(_rs.getObject(1).toString()));
			
			for(int j=1;j<=columnCount;j++){
				row[j-1]=_rs.getObject(j+1);
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
			return metaData.getColumnName(columnIndex+2);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Devuelve la ID del registro de esa columna
	 * @param rowIndex
	 * @return ID del registro de la columna
	 */
	public int getRowId(int rowIndex) {
		return id.get(rowIndex);
	}
	
}