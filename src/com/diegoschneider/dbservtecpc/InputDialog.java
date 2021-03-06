package com.diegoschneider.dbservtecpc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class InputDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 2704173706136035635L;
	private final JPanel contentPanel = new JPanel();
	private int nextY = 1;
	private ArrayList<JTextField> textfields = new ArrayList<JTextField>();
	private JButton okButton;
	private JButton cancelButton;
	
	/**
	 * Clase primitiva para crear di�logos de ingreso
	 * @param title T�tulo del di�logo
	 * @param fields String[] t�tulo de los campos 
	 */
	public InputDialog(String title, String[] fields) {
		setTitle(title);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(null); 
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTitle = new JLabel(title);
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.fill = GridBagConstraints.VERTICAL;
			gbc_lblTitle.gridwidth = 2;
			gbc_lblTitle.insets = new Insets(5, 0, 0, 0);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 0;
			contentPanel.add(lblTitle, gbc_lblTitle);
		}
		
		for (String name : fields) {
			{
				JLabel label = new JLabel(name);
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.insets = new Insets(0, 5, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = nextY;
				gbc_label.weighty = 1.0;
				gbc_label.ipadx = 5;
				contentPanel.add(label, gbc_label);
			}
			{  
				final JTextField textField = new JTextField();
				textField.addFocusListener(new java.awt.event.FocusAdapter() {
				    public void focusGained(java.awt.event.FocusEvent evt) {
				    	textField.selectAll();
				    }
				});
				textfields.add(textField);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.insets = new Insets(5, 0, 0, 5);
				gbc_textField.gridx = 1;
				gbc_textField.gridy = nextY;
				gbc_textField.weighty = 0.0;
				contentPanel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
			
			nextY++;
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
		pack();
		Dimension size = getSize();
		size = new Dimension(size.width+100, size.height);
		setMinimumSize(size);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		//Escape Listener
		ActionListener escListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispatchEvent(new WindowEvent(InputDialog.this, WindowEvent.WINDOW_CLOSING));
			}
		};
		
		getRootPane().registerKeyboardAction(escListener,
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if(action.equals("OK")) {
			okPressed();
		}
		if(action.equals("Cancel")) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	public void okPressed() {
	}
	
	/**
	 * 
	 * @return ArrayList<String> Los valores de los campos 
	 */
	public ArrayList<String> getValues() {
		ArrayList<String> data = new ArrayList<String>();
		for (JTextField tf : textfields) {
			data.add(tf.getText());
		}
		return data;
	}

	/**
	 * Llena los campos con <i>data</i>
	 * @param data Los datos a cargar
	 */
	public void setValues(ArrayList<String> data) {
		int i = 0;
		for (String string : data) {
			textfields.get(i).setText(string);
			i++;
		}
	}
	
}
