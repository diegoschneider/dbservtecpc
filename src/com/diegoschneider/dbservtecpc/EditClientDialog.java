package com.diegoschneider.dbservtecpc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class EditClientDialog extends ClientDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1124059762563655909L;
	private int id;

	public EditClientDialog(int id) {
		super();
		this.id = id;
		setActionListeners(this);
		Statement stmt;
		try {
			ArrayList<Object> data = new ArrayList<Object>();
			stmt = MainWindow.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre, apellido, direccion, telefono, telefono2 FROM clientes WHERE id="+id);
			while(rs.next()) {
				data.add(rs.getObject("nombre"));
				data.add(rs.getObject("apellido"));
				data.add(rs.getObject("direccion"));
				data.add(rs.getObject("telefono"));
				data.add(rs.getObject("telefono2"));
			}
			
			this.setValues(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void okPressed() {
		ArrayList<Object> data = getValues();
		String sql = "UPDATE clientes SET nombre=?, apellido=?, direccion=?, telefono=?, telefono2=? WHERE id=?";
		try {
			PreparedStatement stmt = MainWindow.con.prepareStatement(sql);
			stmt.setString(1, data.get(0).toString());
			stmt.setString(2, data.get(1).toString());
			stmt.setString(3, data.get(2).toString());
			stmt.setString(4, data.get(3).toString());
			stmt.setString(5, data.get(4).toString());
			stmt.setInt(6, this.id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PanelClientes.ReloadTable();
			this.dispose();
		}
	}
	
}
