package com.diegoschneider.dbservtecpc.clientes;

import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.diegoschneider.dbservtecpc.MainWindow;



public class NewClientDialog extends ClientDialog {

	private static final long serialVersionUID = -3098292040902585698L;
	
	public NewClientDialog() {
		super();
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
			ClientPanel.ReloadTable();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar el dato", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
}
