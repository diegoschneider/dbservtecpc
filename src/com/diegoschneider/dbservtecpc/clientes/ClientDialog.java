package com.diegoschneider.dbservtecpc.clientes;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.diegoschneider.dbservtecpc.InputDialog;
import com.diegoschneider.dbservtecpc.MainWindow;


public class ClientDialog extends InputDialog {

	private static final long serialVersionUID = 1L;
	private static String[] columns = {"Nombre", "Apellido", "Dirección", "Teléfono", "Teléfono2"};
	
	/**
	 * Crea un diálogo primitivo de nuevo cliente
	 */
	public ClientDialog() {
		super("Nuevo cliente", columns);
	}
	
	/**
	 * Crea un diálogo primitivo para editar cliente
	 * @param id ID del cliente a editar
	 */
	public ClientDialog(int id) {
		super("Editar cliente", columns);
		try {
			ArrayList<String> data = new ArrayList<String>();
			Statement stmt = MainWindow.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre, apellido, direccion, telefono, telefono2 FROM clientes WHERE id="+id);
			while(rs.next()) {
				data.add(rs.getString("nombre"));
				data.add(rs.getString("apellido"));
				data.add(rs.getString("direccion"));
				data.add(rs.getString("telefono"));
				data.add(rs.getString("telefono2"));
			}
			
			this.setValues(data);
		} catch (SQLException e) {
			e.printStackTrace();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
	
}
