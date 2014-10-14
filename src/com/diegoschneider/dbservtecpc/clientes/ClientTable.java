package com.diegoschneider.dbservtecpc.clientes;

import com.diegoschneider.dbservtecpc.DataTable;

public class ClientTable extends DataTable {

	private static final long serialVersionUID = 1L;

	public ClientTable() {
		super();
	}
	
	public void OpenEditDialog(int id) {
		EditClientDialog editclientdialog = new EditClientDialog(id);
		editclientdialog.setVisible(true);
	}

}
