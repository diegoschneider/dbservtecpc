package com.diegoschneider.dbservtecpc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientDialog extends JDialog implements ActionListener {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4126067203941729380L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_nombre;
	private JTextField textField_apellido;
	private JTextField textField_direccion;
	private JTextField textField_telefono;
	private JTextField textField_telefono2;
	private JButton okButton;
	private JButton cancelButton;
	
	/**
	 * Create the dialog.
	 */
	public ClientDialog() {
		EscapeListener.addEscapeListener(this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(150, 150, 450, 237);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField_nombre = new JTextField();
			textField_nombre.setBounds(224, 11, 210, 20);
			contentPanel.add(textField_nombre);
			textField_nombre.setColumns(10);
		}
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 14, 204, 14);
			contentPanel.add(lblNombre);
		}
		{
			textField_apellido = new JTextField();
			textField_apellido.setBounds(224, 42, 210, 20);
			contentPanel.add(textField_apellido);
			textField_apellido.setColumns(10);
		}
		{
			JLabel lblApellido = new JLabel("Apellido");
			lblApellido.setBounds(10, 45, 204, 14);
			contentPanel.add(lblApellido);
		}
		{
			textField_direccion = new JTextField();
			textField_direccion.setBounds(224, 73, 210, 20);
			contentPanel.add(textField_direccion);
			textField_direccion.setColumns(10);
		}
		{
			JLabel lblDireccin = new JLabel("Direcci\u00F3n");
			lblDireccin.setBounds(10, 76, 204, 14);
			contentPanel.add(lblDireccin);
		}
		{
			textField_telefono = new JTextField();
			textField_telefono.setBounds(224, 104, 210, 20);
			contentPanel.add(textField_telefono);
			textField_telefono.setColumns(10);
		}
		{
			JLabel lblTelfono = new JLabel("Tel\u00E9fono");
			lblTelfono.setBounds(10, 107, 204, 14);
			contentPanel.add(lblTelfono);
		}
		{
			textField_telefono2 = new JTextField();
			textField_telefono2.setBounds(224, 135, 210, 20);
			contentPanel.add(textField_telefono2);
			textField_telefono2.setColumns(10);
		}
		{
			JLabel lblTelfonoSecundario = new JLabel("Tel\u00E9fono secundario");
			lblTelfonoSecundario.setBounds(10, 138, 204, 14);
			contentPanel.add(lblTelfonoSecundario);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public ArrayList<Object> getValues() {
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(textField_nombre.getText());
		data.add(textField_apellido.getText());
		data.add(textField_direccion.getText());
		data.add(textField_telefono.getText());
		data.add(textField_telefono2.getText());
		
		return data;
	}
	
	public void setValues(ArrayList<Object> data) {
		textField_nombre.setText(data.get(0).toString());
		textField_apellido.setText(data.get(1).toString());
		textField_direccion.setText(data.get(2).toString());
		textField_telefono.setText(data.get(3).toString());
		textField_telefono2.setText(data.get(4).toString());
	}

	public void setActionListeners(ClientDialog cd) {
		okButton.addActionListener(cd);
		cancelButton.addActionListener(cd);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if(action.equals("OK")) {
			okPressed();
		}
		if(action.equals("Cancel")) {
			this.dispose();
		}
	}
	
	public void okPressed() {
		PanelClientes.ReloadTable();
	}
	
	
}
