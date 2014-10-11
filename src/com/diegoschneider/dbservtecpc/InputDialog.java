package com.diegoschneider.dbservtecpc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class InputDialog extends JDialog implements ActionListener{

	private static final long serialVersionUID = 2704173706136035635L;
	private final JPanel contentPanel = new JPanel();
	private int nextY = 1;
	private ArrayList<JTextField> textfields = new ArrayList<JTextField>();
	private JButton okButton;
	private JButton cancelButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					String[] fields = {"Test1","Test2", "Test3"};
					InputDialog frame = new InputDialog("Test", fields);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the dialog.
	 */
	public InputDialog(String title, String[] fields) {
		this.setTitle(title);
		EscapeListener.addEscapeListener(this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setLocationRelativeTo(null); 
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		//gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		//gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
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
				JTextField textField = new JTextField();
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
		setActionListeners(this);
	}
	
	public void setActionListeners(InputDialog gd) {
		okButton.addActionListener(gd);
		cancelButton.addActionListener(gd);
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
	
	private void okPressed() {
		ArrayList<String> values = getValues();
		System.out.println(values.toString());
		this.pack();
	}
	
	public ArrayList<String> getValues() {
		ArrayList<String> values = new ArrayList<String>();
		for (JTextField tf : textfields) {
			values.add(tf.getText());
		}
		return values;
	}

}
