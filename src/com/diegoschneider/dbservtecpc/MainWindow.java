package com.diegoschneider.dbservtecpc;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.h2.jdbcx.JdbcDataSource;

import com.diegoschneider.dbservtecpc.clientes.ClientPanel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 4910287097969392192L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	public static Connection con;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:./servtecpc;ifexists=true");
		ds.setUser("sa");
		ds.setPassword("sa");
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			switch (e1.getErrorCode()) {
				//Si la base de datos no existe
				case 90013:
					try {
						//Copiamos la bd original
						InputStream stream = MainWindow.class.getResourceAsStream("/com/diegoschneider/dbservtecpc/initial.mv.db");
						OutputStream resStreamOut = null;
						int readBytes;
						byte[] buffer = new byte[4096];
						try {
							resStreamOut = new FileOutputStream(new File("./servtecpc.mv.db"));
							while ((readBytes = stream.read(buffer)) > 0) {
								resStreamOut.write(buffer, 0, readBytes);
							}
						} catch (IOException e2) {
							e2.printStackTrace();
						} finally {
							try {
								stream.close();
								resStreamOut.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						//Conectamos a la nueva bd
						ds.setURL("jdbc:h2:./servtecpc");
						con = ds.getConnection();					
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
				//Si ya está abierta
				case 90020:
					JOptionPane.showMessageDialog(null, "Ya hay una instancia del programa", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
					break;
				default:
					e1.printStackTrace();
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					MainWindow frame = new MainWindow();
					frame.setTitle("DB Servicio Técnico PC");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "name_110562573486989");
		
		//Panel inicio
		JPanel panel_inicio = new JPanel();
		tabbedPane.addTab("Inicio", null, panel_inicio, null);
		panel_inicio.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBienvenido = new JLabel("Bienvenido! :3");
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		panel_inicio.add(lblBienvenido);
		
		
		new ClientPanel(tabbedPane);
		
		//Panel Presupuestos
		JPanel panel_presupuestos = new JPanel();
		tabbedPane.addTab("Presupuestos", null, panel_presupuestos, null);
		panel_presupuestos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBancaAmiwoVamos_1 = new JLabel("Banca, amiwo, vamos de a poco (^w^)");
		panel_presupuestos.add(lblBancaAmiwoVamos_1);
		
		setMinimumSize(new Dimension(450,300));
	}
}
