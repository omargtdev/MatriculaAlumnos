package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import arreglos.ArregloAlumnos;
import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import arreglos.ArregloRetiros;
import clases.Alumno;
import clases.Curso;
import clases.Matricula;
import clases.Retiro;
import libreria.Mensaje;
import libreria.Tiempo;
import libreria.Util;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class DlgConsultaMatriculasRetiros extends JDialog implements ActionListener, WindowListener{

	/**
	 * 
	 */
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnBuscarMatricula;
	private JScrollPane scrollPane;
	private JTextArea txtArea;
	private JButton btnBuscarRetiro;

	/**
	 * Create the dialog.
	 */
	public DlgConsultaMatriculasRetiros() {
		
		exists = true;
		
		addWindowListener(this);
		setTitle("Consulta de Matriculas y Retiros");

		setBounds(100, 100, 700, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		btnBuscarMatricula = new JButton("Buscar matricula");
		btnBuscarMatricula.addActionListener(this);
		btnBuscarMatricula.setBounds(10, 11, 125, 23);
		contentPanel.add(btnBuscarMatricula);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 664, 315);
		contentPanel.add(scrollPane);
		
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		scrollPane.setViewportView(txtArea);
		
		btnBuscarRetiro = new JButton("Buscar retiro");
		btnBuscarRetiro.addActionListener(this);
		btnBuscarRetiro.setBounds(145, 11, 125, 23);
		contentPanel.add(btnBuscarRetiro);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarRetiro) {
			actionPerformedBtnBuscarRetiro(e);
		}
		if (e.getSource() == btnBuscarMatricula) {
			actionPerformedBtnBuscarMatricula(e);
		}
	}
	
	private ArregloAlumnos aa = new ArregloAlumnos();
	private ArregloCursos ac = new ArregloCursos();
	private ArregloMatriculas am = new ArregloMatriculas();
	private ArregloRetiros ar = new ArregloRetiros();


	private int obtenerNumMatricula() {
		String num = "";
		Matricula m = null;
		while(m == null) {
			try {
				num = Mensaje.input(this, "Coloca el numero de la matricula", "Buscar matricula", "000000000");
				if(num == null){
					Mensaje.msg(this, "Se cancelo la operacion", "Buscar matricula", Mensaje.ADVERTENCIA);
					return -1;
				}else if(num.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un numero de matricula", "Buscar matricula", Mensaje.ERROR);
				}else {	
					m = am.buscar(Integer.parseInt(num.trim()));
					if(m == null) {
						Mensaje.msg(this, "Ingrese un numero existente", "Buscar matricula", Mensaje.ERROR);
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una matricula correcta", "Buscar matricula", Mensaje.ERROR);
			}
		}
		return Integer.parseInt(num.trim());
	}
	
	private int obtenerNumRetiro() {
		String num = "";
		Retiro r = null;
		while(r == null) {
			try {
				num = Mensaje.input(this, "Coloca el numero del retiro", "Buscar retiro", "000000000");
				if(num == null){
					Mensaje.msg(this, "Se cancelo la operacion", "Buscar retiro", Mensaje.ADVERTENCIA);
					return -1;
				}else if(num.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un numero de retiro", "Buscar retiro", Mensaje.ERROR);
				}else {	
					r = ar.buscar(Integer.parseInt(num.trim()));
					if(r == null){
						Mensaje.msg(this, "Ingrese un numero existente", "Buscar retiro", Mensaje.ERROR);
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un retiro correcto", "Buscar retiro", Mensaje.ERROR);
			}
		}
		return Integer.parseInt(num.trim());
	}

	protected void actionPerformedBtnBuscarMatricula(ActionEvent e) {
		actualizarArreglos();
		int num = obtenerNumMatricula();
		if(num != -1) {
			Matricula m = am.buscar(num);
			listar(m);
			listar(aa.buscar(m.getCodAlumno()));
			listar(ac.buscar(m.getCodCurso()));
		}
	}

	protected void actionPerformedBtnBuscarRetiro(ActionEvent e) {
		actualizarArreglos();
		int num = obtenerNumRetiro();
		if(num != -1) {
			Retiro r = ar.buscar(num);
			Matricula m = am.buscar(r.getNumMatricula());
			listar(r);
			listar(aa.buscar(m.getCodAlumno()));
			listar(ac.buscar(m.getCodCurso()));
		}
	}
	
	private void listar(Retiro r) {
		txtArea.setText("");
		imprimir(Util.tituloTxtArea("DATOS DEL RETIRO"));
		imprimir("Num. Retiro          : " + r.getNumRetiro());
		imprimir("Num. Matricula       : " + r.getNumMatricula());
		imprimir("Fecha                : " + Tiempo.formatearFecha(r.getFecha()));
		imprimir("Hora                 : " + Tiempo.formatearHora(r.getHora()));
		imprimir();
	}
	
	private void listar(Matricula m) {
		txtArea.setText("");
		imprimir(Util.tituloTxtArea("DATOS DE LA MATRICULA"));
		imprimir("Numero       : " + m.getNumMatricula());
		imprimir("Cod. Alumno  : " + m.getCodAlumno());
		imprimir("Cod. Curso   : " + m.getCodCurso());
		imprimir("Fecha        : " + Tiempo.formatearFecha(m.getFecha()));
		imprimir("Hora         : " + Tiempo.formatearHora(m.getHora()));
		imprimir();
	}
	
	private void listar(Alumno a) {
		imprimir(Util.tituloTxtArea("DATOS DEL ALUMNO"));
		imprimir("Codigo     : " + a.getCodAlumno());
		imprimir("Nombre(s)  : " + a.getNombres());
		imprimir("Apellidos  : " + a.getApellidos());
		imprimir("DNI        : " + a.getDni());
		imprimir("Edad       : " + a.getEdad());
		imprimir("Celular    : " + a.getCelular());
		imprimir("Estado     : " + Util.nombreEstado(a.getEstado()));
		imprimir();
	}
	
	private void listar(Curso c) {
		imprimir(Util.tituloTxtArea("DATOS DEL CURSO"));
		imprimir("Codigo     : " + c.getCodCurso());
		imprimir("Asignatura : " + c.getAsignatura());
		imprimir("Ciclo      : " + Util.numeroCiclo(c.getCiclo()));
		imprimir("Creditos   : " + c.getCreditos());
		imprimir("Horas      : " + c.getHoras());
		imprimir();
	}
	
	private void imprimir(String s) {
		txtArea.append(Util.saltoLinea(s));
	}
	
	private void imprimir() {
		txtArea.append(Util.saltoLinea(""));
	}
	
	private void actualizarArreglos() {
		aa = new ArregloAlumnos();
		ac = new ArregloCursos();
		am = new ArregloMatriculas();
		ar = new ArregloRetiros();
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
}
