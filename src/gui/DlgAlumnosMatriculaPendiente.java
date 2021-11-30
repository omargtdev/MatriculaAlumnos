package gui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import arreglos.ArregloAlumnos;
import clases.Alumno;
import libreria.Util;

import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgAlumnosMatriculaPendiente extends JDialog implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private JTextArea txtArea;

	
	ArregloAlumnos aa = new ArregloAlumnos();
	private JButton btnActualizar;

	/**
	 * Create the dialog.
	 */
	public DlgAlumnosMatriculaPendiente() {
		
		exists = true;
		
		addWindowListener(this);
		setTitle("Reporte | Alumnos con matr\u00EDcula pendiente");
		setBounds(100, 100, 710, 400);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 674, 304);
		getContentPane().add(scrollPane);
		
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtArea);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(10, 11, 123, 23);
		getContentPane().add(btnActualizar);
		
		mostrarResultados();

	}
	
	private void mostrarResultados() {
		txtArea.setText("");
		for(int i = 0; i < aa.longitud(); i++){		
			if(aa.obtener(i).getEstado() == 0){
				Alumno a = aa.obtener(i);
				int counter = 1;
				
				imprimir("ALUMNO N. " + counter);
				imprimir("Codigo: " + a.getCodAlumno());
				imprimir("Nombres: " + a.getNombres());
				imprimir("Apellidos: " + a.getApellidos());
				imprimir("DNI: " + a.getDni());
				imprimir("Edad: " + a.getEdad());
				imprimir("Celular: " + a.getCelular());
				imprimir("Estado: " + Util.nombreEstado(a.getEstado()));
				imprimir();
				counter++;
				
			}
		}
	}
	
	void imprimir(String s){
		txtArea.append(s + "\n");
	}

	void imprimir(){
		txtArea.append("\n");
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
	protected void windowClosingThis(WindowEvent e) {
		exists = false;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		aa = new ArregloAlumnos();
		mostrarResultados();
	}

}

