package com.diegoschneider.dbservtecpc.clients;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class ClientPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static ClientTable table_clientes;
	
	/**
	 * Crea el panel de clientes
	 * @param tabbedPane TabbedPane en el cual ubicar el panel
	 */
	public ClientPanel(JTabbedPane tabbedPane) {
		//Creamos el panel y lo agregamos al padre
		super(new GridBagLayout());
		tabbedPane.addTab("Clientes", this);
		//Agregamos el scrollPanel para la tabla y lo agregamos a PanelClientes
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_sp = new GridBagConstraints();
		gbc_sp.fill = GridBagConstraints.BOTH;
		gbc_sp.gridwidth = 3;
		gbc_sp.gridx = 0;
		gbc_sp.gridy = 0;
		gbc_sp.weighty = 1.0;
		gbc_sp.insets = new Insets(5,5,5,5);
		add(scrollPane, gbc_sp);
		
		try {
			table_clientes = new ClientTable();
		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(scrollPane, "Error al cargar los clientes", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		scrollPane.setViewportView(table_clientes);
		
		// Creamos el panel para los botones inferiores
		JButton btnAgregarCliente = new JButton("Agregar cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewClientDialog newclientdialog = new NewClientDialog();
				newclientdialog.setVisible(true);
			}
		});
		GridBagConstraints gbc_AC = new GridBagConstraints();
		gbc_AC.gridx = 0;
		gbc_AC.gridy = 1;
		gbc_AC.weightx = 1.0;
		gbc_AC.insets = new Insets(0,5,5,0);
		gbc_AC.fill = GridBagConstraints.HORIZONTAL;
		add(btnAgregarCliente, gbc_AC);
		
		JButton btnEditarCliente = new JButton("Editar cliente");
		btnEditarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					table_clientes.OpenEditDialog();
				} catch (IndexOutOfBoundsException ex) {
					
				}
			}
		});
		GridBagConstraints gbc_EC = new GridBagConstraints();
		gbc_EC.gridx = 1;
		gbc_EC.gridy = 1;
		gbc_EC.weightx = 1.0;
		gbc_EC.insets = new Insets(0,0,5,0);
		gbc_EC.fill = GridBagConstraints.HORIZONTAL;
		add(btnEditarCliente,gbc_EC);
		
		JButton btnEliminarCliente = new JButton("Eliminar cliente");
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				table_clientes.DeleteRow();
				ReloadTable();
			}
		});
		GridBagConstraints gbc_ElC = new GridBagConstraints();
		gbc_ElC.gridx = 2;
		gbc_ElC.gridy = 1;
		gbc_ElC.weightx = 1.0;
		gbc_ElC.insets = new Insets(0,0,5,5);
		gbc_ElC.fill = GridBagConstraints.HORIZONTAL;
		add(btnEliminarCliente,gbc_ElC);
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
