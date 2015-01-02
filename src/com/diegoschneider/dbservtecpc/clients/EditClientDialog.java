package com.diegoschneider.dbservtecpc.clients;

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
		data.add(Integer.toString(this.id));
		String sql = "UPDATE clientes SET nombre=?, apellido=?, direccion=?, telefono=?, telefono2=?, email=? WHERE id=?";
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
