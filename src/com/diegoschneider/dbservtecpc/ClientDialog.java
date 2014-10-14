package com.diegoschneider.dbservtecpc;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ClientDialog extends InputDialog {

	private static final long serialVersionUID = 1L;

	public ClientDialog() {
		super("Nuevo cliente", new String[] {"Nombre", "Apellido", "Dirección", "Teléfono", "Teléfono2"});
		setActionListeners(this);
	}
	
	public ClientDialog(int id) {
		super("Editar cliente", new String[] {"Nombre", "Apellido", "Dirección", "Teléfono", "Teléfono2"});
		Statement stmt;
		try {
			ArrayList<String> data = new ArrayList<String>();
			stmt = MainWindow.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre, apellido, direccion, telefono, telefono2 FROM clientes WHERE id="+id);
			while(rs.next()) {
				data.add(rs.getString("nombre"));
				data.add(rs.getString("apellido"));
				data.add(rs.getString("direccion"));
				data.add(rs.getString("telefono"));
				data.add(rs.getString("telefono2"));
			}
			
			this.setValues(data);
			setActionListeners(this);
		} catch (SQLException e) {
			e.printStackTrace();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
	
}
