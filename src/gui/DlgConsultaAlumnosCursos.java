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
import clases.Alumno;
import clases.Curso;
import libreria.Mensaje;
import libreria.Util;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class DlgConsultaAlumnosCursos extends JDialog implements ActionListener, WindowListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private final JPanel contentPanel = new JPanel();
	private JButton btnBuscarAlumno;
	private JScrollPane scrollPane;
	private JTextArea txtArea;
	private JButton btnBuscarCurso;

	/**
	 * Create the dialog.
	 */
	public DlgConsultaAlumnosCursos() {
		addWindowListener(this);
		
		exists = true;
		
		setTitle("Consulta de Alumnos y Cursos");

		setBounds(100, 100, 700, 410);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		btnBuscarAlumno = new JButton("Buscar alumno");
		btnBuscarAlumno.addActionListener(this);
		btnBuscarAlumno.setBounds(10, 11, 125, 23);
		contentPanel.add(btnBuscarAlumno);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 664, 315);
		contentPanel.add(scrollPane);
		
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		scrollPane.setViewportView(txtArea);
		
		btnBuscarCurso = new JButton("Buscar curso");
		btnBuscarCurso.addActionListener(this);
		btnBuscarCurso.setBounds(145, 11, 125, 23);
		contentPanel.add(btnBuscarCurso);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarCurso) {
			actionPerformedBtnBuscarCurso(e);
		}
		if (e.getSource() == btnBuscarAlumno) {
			actionPerformedBtnBuscarAlumno(e);
		}
	}
	
	private ArregloAlumnos aa = new ArregloAlumnos();
	private ArregloCursos ac = new ArregloCursos();
	private ArregloMatriculas am = new ArregloMatriculas();


	private int obtenerCodCurso() {
		String cod = "";
		Curso c = null;
		while(c == null) {
			try {
				cod = Mensaje.input(this, "Coloca el codigo del curso", "Buscar curso", "0000");
				if(cod == null){
					Mensaje.msg(this, "Se cancelo la operacion", "Buscar curso", Mensaje.ADVERTENCIA);
					return -1;
				}else if(cod.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un codigo de curso", "Buscar curso", Mensaje.ERROR);
				}else {	
					c = ac.buscar(Integer.parseInt(cod.trim()));
					if(c == null) {
						Mensaje.msg(this, "Ingrese un codigo existente", "Buscar curso", Mensaje.ERROR);
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un curso correcta", "Buscar curso", Mensaje.ERROR);
			}
		}
		return Integer.parseInt(cod.trim());
	} 

	private int obtenerCodAlumno() {
		String cod = "";
		Alumno a = null;
		while(a == null) {
			try {
				cod = Mensaje.input(this, "Coloca el codigo del alumno", "Buscar alumno", "000000000");
				if(cod == null){
					Mensaje.msg(this, "Se cancelo la operacion", "Buscar alumno", Mensaje.ADVERTENCIA);
					return -1;
				}else if(cod.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un codigo de alumno", "Buscar alumno", Mensaje.ERROR);
				}else {	
					a = aa.buscar(Integer.parseInt(cod.trim()));
					if(a == null) {
						Mensaje.msg(this, "Ingrese un codigo existente", "Buscar alumno", Mensaje.ERROR);
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un codigo correcto", "Buscar alumno", Mensaje.ERROR);
			}
		}
		return Integer.parseInt(cod.trim());
	}

	protected void actionPerformedBtnBuscarCurso(ActionEvent e) {
		actualizarArreglos();
		int cod = obtenerCodCurso();
		if(cod != -1) {
			Curso c = ac.buscar(cod);
			listar(c, false);
		}
	}

	protected void actionPerformedBtnBuscarAlumno(ActionEvent e) {
		actualizarArreglos();
		int cod = obtenerCodAlumno();
		if(cod != -1) {
			Alumno a = aa.buscar(cod);
			listar(a);
			if(a.getEstado() != 0) {
				//Buscamos el curso en el que este matriculado
				for(int i = 0; i < am.longitud(); i++) {
					if(aa.buscar(am.obtener(i).getCodAlumno()) == a) {
						listar(ac.buscar(am.obtener(i).getCodCurso()), true);
						break;
					}
				}
			}
		}
	}
	
	private void listar(Curso c, boolean matriculado) {
		if(!matriculado) txtArea.setText("");
		String titulo = "DATOS DEL CURSO";
		titulo += matriculado ? " EN EL QUE ESTA MATRICULADO" : "";
		imprimir(Util.tituloTxtArea(titulo));
		imprimir("Codigo     : " + c.getCodCurso());
		imprimir("Asignatura : " + c.getAsignatura());
		imprimir("Ciclo      : " + Util.numeroCiclo(c.getCiclo()));
		imprimir("Creditos   : " + c.getCreditos());
		imprimir("Horas      : " + c.getHoras());
		imprimir();
	}
	
	private void listar(Alumno a) {
		txtArea.setText("");
		imprimir(Util.tituloTxtArea("DATOS DEL ALUMNO"));
		imprimir("Codigo     : " + a.getCodAlumno());
		imprimir("Nombre(s)  : " + a.getNombres());
		imprimir("Apellidos  : " + a.getApellidos());
		imprimir("DNI        : " + a.getDni());
		imprimir("Edad       : " + a.getEdad());
		imprimir("Celular    : " + a.getCelular());
		imprimir("Estado     : " + Util.nombreEstado(a.getEstado()));
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

