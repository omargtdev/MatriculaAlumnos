package gui;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloAlumnos;
import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import clases.Alumno;
import clases.Curso;
import clases.Matricula;
import libreria.Mensaje;
import libreria.Tiempo;
import libreria.Util;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgMatricula extends JDialog implements WindowListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JScrollPane scrollPane;
	private JTable tbMatricula;
	private DefaultTableModel model;
	
	//Titulos mensajes
	private String TITU_AGREGAR = "Agregar matricula";
	private String TITU_MODIFICAR = "Modificar matricula";
	private String TITU_CONSULTAR = "Consultar matricula";
	private String TITU_ELIMINAR = "Eliminar matricula";
	
	/**
	 * Create the dialog.
	 */
	public DlgMatricula() {
		setTitle("Matricula");
		addWindowListener(this);
		
		exists = true;

		setBounds(100, 100, 700, 410);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(15, 11, 123, 23);
		getContentPane().add(btnAdicionar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(148, 11, 123, 23);
		getContentPane().add(btnConsultar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(281, 11, 123, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(414, 11, 123, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(547, 11, 123, 23);
		getContentPane().add(btnActualizar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 45, 659, 315);
		getContentPane().add(scrollPane);
		
		tbMatricula = new JTable();
		scrollPane.setViewportView(tbMatricula);
		
		tbMatricula = new JTable();
		scrollPane.setViewportView(tbMatricula);
		
		model = new DefaultTableModel();
		model.addColumn("NUM. MATRICULA");
		model.addColumn("COD. ALUMNO");
		model.addColumn("COD. CURSO");
		model.addColumn("FECHA");
		model.addColumn("HORA");

		tbMatricula.setModel(model);

		mostrarMatriculas();
	}
	
	/* arreglos */
	private ArregloAlumnos aa = new ArregloAlumnos();
	private ArregloMatriculas am = new ArregloMatriculas();
	private ArregloCursos ac = new ArregloCursos();
	
	private void mostrarMatriculas() {
		model.setRowCount(0);
		for(int i = 0; i < am.longitud(); i++) {
			Matricula m = am.obtener(i);
			model.addRow(new Object[]{
					m.getNumMatricula(), 
					m.getCodAlumno(), 
					m.getCodCurso(), 
					Tiempo.formatearFecha(m.getFecha()), 
					Tiempo.formatearHora(m.getHora())
				});
		}
	}

	public void windowActivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
		if (e.getSource() == this) {
			windowClosedThis(e);
		}
	}
	public void windowClosing(WindowEvent e) {
		exists = false;
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	protected void windowClosedThis(WindowEvent e) {
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnConsultar) {
			actionPerformedBtnConsultar(e);
		}
		if (e.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(e);
		}
	}
	
	private int obtenerCodAlumno() {
		String cod = "";
		Alumno a = null;
		while(a == null) {
			try {
				cod = Mensaje.input(this, "Coloca el codigo del alumno", TITU_AGREGAR, "000000000");
				if(cod == null){
					Mensaje.msg(this, "Se cancelo la operacion", TITU_AGREGAR, Mensaje.ADVERTENCIA);
					return -1;
				}else if(cod.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un codigo de alumno", TITU_AGREGAR, Mensaje.ERROR);
				}else {	
					a = aa.buscar(Integer.parseInt(cod.trim()));
					if(a == null) {
						Mensaje.msg(this, "Ingrese un codigo existente", TITU_AGREGAR, Mensaje.ERROR);
					}else if(aa.buscar(Integer.parseInt(cod.trim())).getEstado() != 0) {
						Mensaje.msg(this, "Este alumno ya esta matriculado", TITU_AGREGAR, Mensaje.ERROR);
						a = null;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un codigo correcto", TITU_AGREGAR, Mensaje.ERROR);
			}
		}
		return Integer.parseInt(cod.trim());
	}
	
	private int obtenerCodCurso(String titulo) {
		String cod = "";
		Curso c = null;
		while(c == null) {
			try {
				cod = Mensaje.input(this, "Coloca el codigo del curso", titulo, "0000");
				if(cod == null){
					Mensaje.msg(this, "Se cancelo la operacion", titulo, Mensaje.ADVERTENCIA);
					return -1;
				}else if(cod.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un codigo de curso", titulo, Mensaje.ERROR);
				}else {	
					c = ac.buscar(Integer.parseInt(cod.trim()));
					if(c == null) {
						Mensaje.msg(this, "Ingrese un codigo existente", titulo, Mensaje.ERROR);
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una matricula correcta", titulo, Mensaje.ERROR);
			}
		}
		return Integer.parseInt(cod.trim());
	} 
	
	protected void actionPerformedBtnAdicionar(ActionEvent e){
		actualizarArreglos();
		int codAlumno = obtenerCodAlumno();
		if(codAlumno != -1) {
			int codCurso = obtenerCodCurso(TITU_AGREGAR);
			if(codCurso != -1) {
				String datos = Util.saltoLinea("¿Los datos son correctos?") + 
							   Util.saltoLinea("Cod. alumno : " + codAlumno) +
							   Util.saltoLinea("Nombre      : " + aa.buscar(codAlumno).getNombres() + " " + aa.buscar(codAlumno).getApellidos()) + 
							   Util.saltoLinea("Cod. curso  : " + codCurso) +
							   Util.saltoLinea("Asignatura  : " + ac.buscar(codCurso).getAsignatura());
				int confir = Mensaje.confirm(this, datos, TITU_AGREGAR);
				if(confir == 0) {
					am.agregar(new Matricula(
							am.codigoCorrelativo(), 
							codAlumno, 
							codCurso
						));
					aa.buscar(codAlumno).setEstado(1); // Matriculado
					aa.actualizarArchivo();
					am.actualizarArchivo();
					mostrarMatriculas();
					int seguir = Mensaje.confirm(this, "¿Desea agregar otra matricula?", TITU_AGREGAR);
					if(seguir == 0) {
						actionPerformedBtnAdicionar(e);
					}
				}else {
					Mensaje.msg(this, "Se cancelo la operacion", TITU_AGREGAR, Mensaje.ADVERTENCIA);
				}
			}
		}
	}
	

	private void actualizarArreglos() {
		aa = new ArregloAlumnos();
		ac = new ArregloCursos();
		am = new ArregloMatriculas();
	}
	
	protected void actionPerformedBtnConsultar(ActionEvent e) {
		actualizarArreglos();
		int num = Util.obtenerCodigo(tbMatricula, model);
		if(num != -1) {
			Matricula m = am.buscar(num);
			Alumno a = aa.buscar(m.getCodAlumno());
			Curso c = ac.buscar(m.getCodCurso());
			Mensaje.msg(this, datosMatricula(m) + datosAlumno(a) + datosCurso(c), TITU_CONSULTAR, Mensaje.INFO);
		}else {
			Mensaje.msg(this, "Debes seleccionar UNA matricula", "Error", Mensaje.ERROR);
		}
	}
	
	private String datosMatricula(Matricula m) {
		return Util.saltoLinea("DATOS DE LA MATRICULA") +
			   Util.saltoLinea("  Numero : " + m.getNumMatricula()) +
			   Util.saltoLinea("  Fecha  : " + Tiempo.formatearFecha(m.getFecha())) +
			   Util.saltoLinea("  Hora   : " + Tiempo.formatearHora(m.getHora()));
	}
	
	private String datosAlumno(Alumno a) {
		return Util.saltoLinea("DATOS DEL ALUMNO") +
			   Util.saltoLinea("  Codigo : " + a.getCodAlumno()) +
			   Util.saltoLinea("  Nombre : " + a.getNombres()  + " " + a.getApellidos()) +
			   Util.saltoLinea("  Estado : " + Util.nombreEstado(a.getEstado()));
	}

	private String datosCurso(Curso c) {
		return Util.saltoLinea("DATOS DEL CURSO") +
			   Util.saltoLinea("  Codigo : " + c.getCodCurso()) +
			   Util.saltoLinea("  Nombre : " + c.getAsignatura()) +
			   Util.saltoLinea("  Ciclo  : " + Util.numeroCiclo(c.getCiclo()));
	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
		actualizarArreglos();
		int num = Util.obtenerCodigo(tbMatricula, model);
		if(num != -1) {
			String datos = "";
			Matricula m = am.buscar(num);
			int codCurso = obtenerCodCurso(TITU_MODIFICAR);
			if(codCurso != -1) {
				datos = Util.saltoLinea("¿Los datos son correctos?") + 
						Util.saltoLinea("Cod. curso  : " + codCurso) +
						Util.saltoLinea("Asignatura  : " + ac.buscar(codCurso).getAsignatura());
				int confir = Mensaje.confirm(this, datos, TITU_MODIFICAR);
				if(confir == 0) {
					m.setCodCurso(codCurso);
					am.actualizarArchivo();
					mostrarMatriculas();
				}else {
					Mensaje.msg(this, "Se cancelo la operacion", TITU_MODIFICAR, Mensaje.ADVERTENCIA);
				}
			}
		}else {
			Mensaje.msg(this, "Debes seleccionar UNA matricula", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		actualizarArreglos();
		int num = Util.obtenerCodigo(tbMatricula, model);
		if(num != -1) {
			Matricula m = am.buscar(num);
			Alumno a = aa.buscar(m.getCodAlumno());
			int confir = Mensaje.confirm(this, "¿Estas seguro de cancelar esta matricula?", TITU_ELIMINAR);
			if(confir == 0) {
				if(a.getEstado() == 2) {
					Mensaje.msg(this, "No puedes cancelar una matricula retirada", TITU_ELIMINAR, Mensaje.ERROR);
				}else {
					am.eliminar(m);
					a.setEstado(0); // Pasar el estado del alumno a registrado
					aa.actualizarArchivo();
					am.actualizarArchivo();
					mostrarMatriculas();
					Mensaje.msg(this, "La matricula se cancelo", TITU_ELIMINAR, Mensaje.INFO);
				}
			}		
		}else {
			Mensaje.msg(this, "Debes seleccionar UNA matricula", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarArreglos();
		mostrarMatriculas();
	}
}
