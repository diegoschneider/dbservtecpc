package com.diegoschneider.dbservtecpc.clientes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.diegoschneider.dbservtecpc.DataTable;
import com.diegoschneider.dbservtecpc.FillTable;
import com.diegoschneider.dbservtecpc.MainWindow;

public class ClientTable extends DataTable {

	private static final long serialVersionUID = 1L;

	/**
	 * Tabla de clientes. Autocarga los datos
	 * @throws SQLException
	 */
	public ClientTable() throws SQLException {
		super();
		ReloadTable();
	}
	
	/**
	 * Abre el diálogo para editar clientes
	 * @param id ID del cliente a editar
	 */
	public void OpenEditDialog(int id) {
		EditClientDialog editclientdialog = new EditClientDialog(id);
		editclientdialog.setVisible(true);
	}
	
	/**
	 * Elimina un cliente
	 * @param id ID a eliminar
	 */
	public void DeleteRow(int id) {
		try {
			PreparedStatement stmt = MainWindow.con.prepareStatement("DELETE FROM clientes WHERE id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			ReloadTable();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Modelo de tabla con los datos de clientes cargados
	 * @return TableModel
	 * @throws SQLException
	 */
	public static FillTable ClientesTableModel() throws SQLException {
		Statement stmt = MainWindow.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id,nombre, apellido, direccion, telefono, telefono2, email FROM clientes");
		FillTable model = new FillTable(rs);
		return model;
	}
	
	/**
	 * Vuelve a cargar los datos de la tabla
	 * @throws SQLException
	 */
	public void ReloadTable() throws SQLException {
		setModel(ClientesTableModel());
	}
	
}
