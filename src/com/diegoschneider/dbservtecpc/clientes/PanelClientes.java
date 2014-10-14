package com.diegoschneider.dbservtecpc.clientes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.diegoschneider.dbservtecpc.FillTable;
import com.diegoschneider.dbservtecpc.MainWindow;

public class PanelClientes {

	private static ClientTable table_clientes;
	
	public PanelClientes(JTabbedPane tabbedPane) {
		JPanel panel_clientes = new JPanel();
		tabbedPane.addTab("Clientes", null, panel_clientes, null);
		panel_clientes.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 409, 328);
		panel_clientes.add(scrollPane);
		
		table_clientes = new ClientTable();
		
		scrollPane.setViewportView(table_clientes);
		try {
			table_clientes.setModel(ClientesTableModel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 350, 409, 23);
		panel_clientes.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnAgregarCliente = new JButton("Agregar cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewClientDialog newclientdialog = new NewClientDialog();
				newclientdialog.setVisible(true);
			}
		});
		panel.add(btnAgregarCliente);
		
		JButton btnEditarCliente = new JButton("Editar cliente");
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					table_clientes.OpenEditDialog();
				} catch (IndexOutOfBoundsException ex) {
					
				}
			}
		});
		panel.add(btnEditarCliente);
		
		JButton btnEliminarCliente = new JButton("Eliminar cliente");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int row = table_clientes.getSelectedRow();
					Object data = (Object)table_clientes.getValueAt(row, 0);
					PreparedStatement stmt = MainWindow.con.prepareStatement("DELETE FROM clientes WHERE id=?");
					stmt.setInt(1, Integer.parseInt(data.toString()));
		            stmt.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
				} catch (IndexOutOfBoundsException ex) {
					
				}
				ReloadTable();
			}
		});
		panel.add(btnEliminarCliente);
	}
	
	public static void ReloadTable() {
		try {
			table_clientes.setModel(ClientesTableModel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static FillTable ClientesTableModel() throws SQLException {
		Statement stmt = MainWindow.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id,nombre, apellido, direccion, telefono, telefono2 FROM clientes");
		FillTable model = new FillTable(rs);
		return model;
	}
	
}
