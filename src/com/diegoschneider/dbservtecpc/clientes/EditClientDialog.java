package com.diegoschneider.dbservtecpc.clientes;

import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.diegoschneider.dbservtecpc.MainWindow;


public class EditClientDialog extends ClientDialog {

	private static final long serialVersionUID = -1124059762563655909L;
	private int id;

	public EditClientDialog(int id) {
		super(id);
		this.id = id;
	}
	
	public void okPressed() {
		ArrayList<String> data = getValues();
		String sql = "UPDATE clientes SET nombre=?, apellido=?, direccion=?, telefono=?, telefono2=? WHERE id=?";
		try {
			PreparedStatement stmt = MainWindow.con.prepareStatement(sql);
			stmt.setString(1, data.get(0));
			stmt.setString(2, data.get(1));
			stmt.setString(3, data.get(2));
			stmt.setString(4, data.get(3));
			stmt.setString(5, data.get(4));
			stmt.setInt(6, this.id);
			stmt.execute();
			PanelClientes.ReloadTable();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar el dato", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
}
