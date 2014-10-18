package com.diegoschneider.dbservtecpc.clientes;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class PanelClientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private static ClientTable table_clientes;
	
	public PanelClientes(JTabbedPane tabbedPane) {
		//Creamos el panel y lo agregamos al padre
		super();
		tabbedPane.addTab("Clientes", null, this, null);
		setLayout(null);
		
		//Agregamos el scrollPanel para la tabla y lo agregamos a PanelClientes
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 409, 328);
		add(scrollPane);
		
		try {
			table_clientes = new ClientTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(scrollPane, "Error al cargar los clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		scrollPane.setViewportView(table_clientes);
		
		// Creamos el panel para los botones inferiores
		JPanel panel = new JPanel();
		panel.setBounds(10, 350, 409, 23);
		add(panel);
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
				table_clientes.DeleteRow();
				ReloadTable();
			}
		});
		panel.add(btnEliminarCliente);
	}
	
	/**
	 * Llama a table_clientes.ReloadTable() 
	 */
	public static void ReloadTable() {
		try {
			table_clientes.ReloadTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
