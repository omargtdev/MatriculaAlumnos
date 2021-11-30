package gui;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import arreglos.ArregloAlumnos;
import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import clases.Alumno;
import libreria.Util;

import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgAlumnosMatriculadosPorCurso extends JDialog implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	JTextArea textArea;
	private JButton btnActualizar;

	/**
	 * Create the dialog.
	 */
	public DlgAlumnosMatriculadosPorCurso() {
		
		exists = true;
		
		addWindowListener(this);
		setTitle("Reporte | Alumnos matriculados por curso");
		setBounds(100, 100, 722, 400);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 684, 304);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(10, 11, 123, 23);
		getContentPane().add(btnActualizar);
		
		mostrarResultados();
		
	}

	/* arreglos */
	ArregloCursos ac = new ArregloCursos();
	ArregloMatriculas am = new ArregloMatriculas();
	ArregloAlumnos aa = new ArregloAlumnos();
	
	private void mostrarResultados() {
		textArea.setText("");
		for(int i = 0; i < ac.longitud(); i++){
			int counter = 0;
			imprimir(Util.tituloTxtArea(ac.obtener(i).getAsignatura()));
			for(int j = 0; j < am.longitud(); j++){
				if(am.obtener(j).getCodCurso() == ac.obtener(i).getCodCurso()){
					Alumno a = aa.buscar(am.obtener(j).getCodAlumno());
					
					imprimir("ALUMNO N. " + (j + 1));
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
			if(counter == 0) {
				imprimir("No hay alumnos matriculados en este curso");
				imprimir();
			}
			counter = 0;
		}
		
	}
	
	void imprimir(String s){
		textArea.append(s + "\n");
	}

	void imprimir(){
		textArea.append("\n");
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
		ac = new ArregloCursos();
		am = new ArregloMatriculas();
		aa = new ArregloAlumnos();
		mostrarResultados();
	}
}
