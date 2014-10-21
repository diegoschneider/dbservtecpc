package com.diegoschneider.dbservtecpc;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class DataTable extends JTable {

	public DataTable() {
		super();
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		//FocusListener: Si no hay nada seleccionado al hacer foco, seleccionar la primera celda
		addFocusListener(new FocusListener() {	
			public void focusGained(FocusEvent e) {
				if(getSelectedRow() == -1) {
					DataTable.this.changeSelection(0, 0, false, false);
				}
			}
			public void focusLost(FocusEvent e) {}
		});
		
		//Editar con doble click
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        if (me.getClickCount() == 2) {
		        	OpenEditDialog();
		        }
		    }
		});
		
		InputMap im = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap am = getActionMap();
		
		//Eliminar con supr
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Del");
		am.put("Del", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				DeleteRow();
			}
		});
		
		//Editar con enter
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		am.put("Enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenEditDialog();
			}
		});
		
		//Que Tab y Shift+Tab cambien de componente en vez de celda
		am.put("selectPreviousColumnCell", new AbstractAction() {
			public void actionPerformed(ActionEvent arg0) {
				KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		        manager.focusPreviousComponent();
			}
		});
		
		am.put("selectNextColumnCell", new AbstractAction() {    
			public void actionPerformed(ActionEvent evt) {
				KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				manager.focusNextComponent();
			}
		});
		
	}
	
	/**
	 * Llama a OpenEditDialog(id) con el ID de la columna seleccionada
	 * Si no hay nada seleccionado, no hace nada.
	 * @see OpenEditDialog(int id)
	 */
	public void OpenEditDialog() {
		try {
			int id = getRowId(getSelectedRow());
			OpenEditDialog(id);
		} catch(IndexOutOfBoundsException e) {
			//Do nothing
		}

	}
	
	/**
	 * Función para Override
	 * @param id ID del registro con el que accionar
	 */
	public void OpenEditDialog(int id) {
		
	}
	
	/**
	 * Llama a DeleteRow(id) con el ID de la columna seleccionada
	 * Si no hay nada seleccionado, no hace nada.
	 * @see DeleteRow(int id)
	 */
	public void DeleteRow() {
		try {
			int id = getRowId(getSelectedRow());
			DeleteRow(id);
		} catch(IndexOutOfBoundsException e) {
			//Do nothing
		}
	}
	
	/**
	 * Función para Override
	 * @param id ID del registro con el que accionar
	 */
	public void DeleteRow(int id) {
		
	}
	
	/**
	 * Devuelve la ID de registro de una columna
	 * @param row Columna a leer
	 * @return ID de registro de la columna
	 * @throws IndexOutOfBoundsException Si no hay nada seleccionado
	 */
	public int getRowId(int row) throws IndexOutOfBoundsException {
		return ((FillTable) this.getModel()).getRowId(0);
	}

}
