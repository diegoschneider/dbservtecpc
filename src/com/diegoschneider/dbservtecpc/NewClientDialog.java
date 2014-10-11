package com.diegoschneider.dbservtecpc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;



public class NewClientDialog extends ClientDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3098292040902585698L;
	
	public NewClientDialog() {
		super();
		setActionListeners(this);
	}
	
	public void okPressed() {
		ArrayList<Object> data = getValues();
		String sql = "INSERT INTO clientes(nombre, apellido, direccion, telefono, telefono2) values(?,?,?,?,?)";
		try {
			PreparedStatement stmt = MainWindow.con.prepareStatement(sql);
			stmt.setString(1, data.get(0).toString());
			stmt.setString(2, data.get(1).toString());
			stmt.setString(3, data.get(2).toString());
			stmt.setString(4, data.get(3).toString());
			stmt.setString(5, data.get(4).toString());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PanelClientes.ReloadTable();
			this.dispose();
		}
	}
	
}
