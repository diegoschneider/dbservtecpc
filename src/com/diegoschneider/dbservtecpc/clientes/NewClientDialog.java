package com.diegoschneider.dbservtecpc.clientes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.diegoschneider.dbservtecpc.MainWindow;



public class NewClientDialog extends ClientDialog {

	private static final long serialVersionUID = -3098292040902585698L;
	
	public NewClientDialog() {
		super();
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
			PanelClientes.ReloadTable();
			this.dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar el dato", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
}
