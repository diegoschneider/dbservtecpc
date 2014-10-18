package com.diegoschneider.dbservtecpc.clientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.diegoschneider.dbservtecpc.DataTable;
import com.diegoschneider.dbservtecpc.FillTable;
import com.diegoschneider.dbservtecpc.MainWindow;

public class ClientTable extends DataTable {

	private static final long serialVersionUID = 1L;

	public ClientTable() throws SQLException{
		super();
		ReloadTable();
	}
	
	public void OpenEditDialog(int id) {
		EditClientDialog editclientdialog = new EditClientDialog(id);
		editclientdialog.setVisible(true);
	}

	public static FillTable ClientesTableModel() throws SQLException {
		Statement stmt = MainWindow.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id,nombre, apellido, direccion, telefono, telefono2 FROM clientes");
		FillTable model = new FillTable(rs);
		return model;
	}
	
	public void ReloadTable() throws SQLException {
		setModel(ClientesTableModel());
	}
	
}
