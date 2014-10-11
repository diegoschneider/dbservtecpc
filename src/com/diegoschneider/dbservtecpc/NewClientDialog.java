package com.diegoschneider.dbservtecpc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;



public class NewClientDialog extends InputDialog {

	private static final long serialVersionUID = -3098292040902585698L;
	
	public NewClientDialog() {
		super("Nuevo cliente", new String[] {"Nombre", "Apellido", "Dirección", "Teléfono", "Teléfono2"});
		setActionListeners(this);
	}
	
	public void okPressed() {
		ArrayList<String> data = getValues();
		String sql = "INSERT INTO clientes(nombre, apellido, direccion, telefono, telefono2) values(?,?,?,?,?)";
		try {
			PreparedStatement stmt = MainWindow.con.prepareStatement(sql);
			int i = 1;
			for (String string : data) {
				stmt.setString(i++, string);
			}
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			PanelClientes.ReloadTable();
			this.dispose();
		}
	}
	
}
