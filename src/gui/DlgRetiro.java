package gui;

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

public class DlgRetiro extends JDialog implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private JLabel lblNumRetiro;
	private JLabel lblNumMatricula;
	private JTextField txtNumRetiro;
	private JTextField txtNumMatricula;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;


	/**
	 * Create the dialog.
	 */
	public DlgRetiro() {
		addWindowListener(this);
		
		exists = true;
		
		setTitle("Retiro");
		setBounds(100, 100, 755, 440);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		lblNumRetiro = new JLabel("C\u00F3digo del Retiro");
		lblNumRetiro.setBounds(10, 11, 114, 14);
		getContentPane().add(lblNumRetiro);
		
		lblNumMatricula = new JLabel("C\u00F3digo de la Matr\u00EDcula");
		lblNumMatricula.setBounds(10, 36, 114, 14);
		getContentPane().add(lblNumMatricula);
		
		txtNumRetiro = new JTextField();
		txtNumRetiro.setEditable(false);
		txtNumRetiro.setBounds(134, 8, 100, 20);
		getContentPane().add(txtNumRetiro);
		txtNumRetiro.setColumns(10);
		
		txtNumMatricula = new JTextField();
		txtNumMatricula.setEditable(false);
		txtNumMatricula.setColumns(10);
		txtNumMatricula.setBounds(134, 33, 100, 20);
		getContentPane().add(txtNumMatricula);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 61, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(598, 7, 131, 23);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(598, 36, 131, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(598, 65, 131, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 719, 295);
		getContentPane().add(scrollPane);
		
		/* cargar retiros */
		table = new JTable();
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel();
		model.addColumn("NUM. RETIRO");
		model.addColumn("NUM. RETIRO");
		model.addColumn("FECHA");
		model.addColumn("HORA");
		table.setModel(model);
		

	}
	
	private void cargarRetiros() {
		
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		if (e.getSource() == this) {
			windowClosingThis(e);
		}
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}

	// Colocar la ventana como "inexistente" al cerrarla
	protected void windowClosingThis(WindowEvent e) {
		exists = false;
	}
}
