package gui;

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRetiro extends JDialog implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnConsultar;
	private JButton btnActualizar;
	private JScrollPane scrollPane;
	private JTable tbRetiro;
	private DefaultTableModel model;
	
	//Titulos mensajes
	private String TITU_AGREGAR = "Agregar retiro";
	private String TITU_MODIFICAR = "Modificar retiro";
	private String TITU_CONSULTAR = "Consultar retiro";
	private String TITU_ELIMINAR = "Eliminar retiro";


	/**
	 * Create the dialog.
	 */
	public DlgRetiro() {
		setTitle("Retiro");
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
		
		tbRetiro = new JTable();
		scrollPane.setViewportView(tbRetiro);
		
		tbRetiro = new JTable();
		scrollPane.setViewportView(tbRetiro);
		
		model = new DefaultTableModel();
		model.addColumn("NUM. RETIRO");
		model.addColumn("NUM. MATRICULA");
		model.addColumn("FECHA");
		model.addColumn("HORA");

		tbRetiro.setModel(model);

		mostrarRetiros();

	}
	
	/* Arreglos */
	private ArregloRetiros ar = new ArregloRetiros();
	private ArregloMatriculas am = new ArregloMatriculas();
	private ArregloCursos ac = new ArregloCursos();
	private ArregloAlumnos aa = new ArregloAlumnos();
	

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
	
	private void mostrarRetiros() {
		model.setRowCount(0);
		for(int i = 0; i < ar.longitud(); i++) {
			Retiro r = ar.obtener(i);
			model.addRow(new Object[]{
					r.getNumRetiro(), 
					r.getNumMatricula(), 
					Tiempo.formatearFecha(r.getFecha()), 
					Tiempo.formatearHora(r.getHora())
				});
		}
	}
	
	private void actualizarArreglos() {
		aa = new ArregloAlumnos();
		ac = new ArregloCursos();
		am = new ArregloMatriculas();
		ar = new ArregloRetiros();
	}

	private int obtenerNumMatricula() {
		String num = "";
		Matricula m = null;
		while(m == null) {
			try {
				num = Mensaje.input(this, "Coloca el numero de la matricula", TITU_AGREGAR, "000000000");
				if(num == null){
					Mensaje.msg(this, "Se cancelo la operacion", TITU_AGREGAR, Mensaje.ADVERTENCIA);
					return -1;
				}else if(num.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un numero de matricula", TITU_AGREGAR, Mensaje.ERROR);
				}else {	
					m = am.buscar(Integer.parseInt(num.trim()));
					if(m == null) {
						Mensaje.msg(this, "Ingrese un numero existente", TITU_AGREGAR, Mensaje.ERROR);
					}else if(aa.buscar(m.getCodAlumno()).getEstado() == 2) {
						Mensaje.msg(this, "Esta matricula ya esta retirada", TITU_AGREGAR, Mensaje.ERROR);
						m = null;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una matricula correcta", TITU_AGREGAR, Mensaje.ERROR);
			}
		}
		return Integer.parseInt(num.trim());
	}
	
	private int obtenerCodCurso() {
		String cod = "";
		Curso c = null;
		while(c == null) {
			try {
				cod = Mensaje.input(this, "Coloca el codigo del curso", TITU_MODIFICAR, "0000");
				if(cod == null){
					Mensaje.msg(this, "Se cancelo la operacion", TITU_MODIFICAR, Mensaje.ADVERTENCIA);
					return -1;
				}else if(cod.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un codigo de curso", TITU_MODIFICAR, Mensaje.ERROR);
				}else {	
					c = ac.buscar(Integer.parseInt(cod.trim()));
					if(c == null) {
						Mensaje.msg(this, "Ingrese un codigo existente", TITU_MODIFICAR, Mensaje.ERROR);
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una matricula correcta", TITU_MODIFICAR, Mensaje.ERROR);
			}
		}
		return Integer.parseInt(cod.trim());
	} 
	
	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		actualizarArreglos();
		int numMatricula = obtenerNumMatricula();
		if(numMatricula != -1) {
			Matricula m = am.buscar(numMatricula);
			String datos = Util.saltoLinea("¿Los datos son correctos?") + 
						   Util.saltoLinea("Num. matricula : " + numMatricula);
						   Util.saltoLinea("Cod. alumno    : " + m.getCodAlumno());
						   Util.saltoLinea("Cod. curso     : " + m.getCodCurso());
			int confir = Mensaje.confirm(this, datos, TITU_AGREGAR);
			if(confir == 0) {
				ar.agregar(new Retiro(
						ar.codigoCorrelativo(), 
						numMatricula
					));
				aa.buscar(m.getCodAlumno()).setEstado(2); // Retirado 
				aa.actualizarArchivo();
				ar.actualizarArchivo();
				mostrarRetiros();
				int seguir = Mensaje.confirm(this, "¿Desea retirar otra matricula?", TITU_AGREGAR);
				if(seguir == 0) {
					actionPerformedBtnAdicionar(e);
				}
			}else {
				Mensaje.msg(this, "Se cancelo la operacion", TITU_AGREGAR, Mensaje.ADVERTENCIA);
			}
		}
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		actualizarArreglos();
		int num = Util.obtenerCodigo(tbRetiro, model);
		if(num != -1) {
			Retiro r = ar.buscar(num);
			Matricula m = am.buscar(r.getNumMatricula());
			Alumno a = aa.buscar(m.getCodAlumno());
			Curso c = ac.buscar(m.getCodCurso());
			Mensaje.msg(this, datosRetiro(r) + datosMatricula(m) + datosAlumno(a) + datosCurso(c), TITU_CONSULTAR, Mensaje.INFO);
		}else {
			Mensaje.msg(this, "Debes seleccionar UNA matricula", "Error", Mensaje.ERROR);
		}
	}
	
	private String datosRetiro(Retiro r) {
		return Util.saltoLinea("DATOS DEL RETIRO") +
			   Util.saltoLinea("  Numero : " + r.getNumRetiro()) +
			   Util.saltoLinea("  Fecha  : " + Tiempo.formatearFecha(r.getFecha())) +
			   Util.saltoLinea("  Hora   : " + Tiempo.formatearHora(r.getHora()));
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
		int numRetiro = Util.obtenerCodigo(tbRetiro, model);
		if(numRetiro != -1) {
			int codCurso = obtenerCodCurso();
			if(codCurso != -1) {
				Matricula m = am.buscar(ar.buscar(numRetiro).getNumMatricula());
				String datos = Util.saltoLinea("¿Los datos son correctos?") + 
							   Util.saltoLinea("Cod. curso  : " + codCurso) +
							   Util.saltoLinea("Asignatura  : " + ac.buscar(codCurso).getAsignatura());
				int confir = Mensaje.confirm(this, datos, TITU_MODIFICAR);
				if(confir == 0) {
					m.setCodCurso(codCurso);
					am.actualizarArchivo();
				}else {
					Mensaje.msg(this, "Se cancelo la operacion", TITU_MODIFICAR, Mensaje.ADVERTENCIA);
				}
			}
		}else {
			Mensaje.msg(this, "Debes seleccionar UN retiro", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		actualizarArreglos();
		int numRetiro = Util.obtenerCodigo(tbRetiro, model);
		if(numRetiro != -1) {
			int confir = Mensaje.confirm(this, "¿Estas seguro de cancelar esta retiro?", TITU_ELIMINAR);
			if(confir == 0) {
				Retiro r = ar.buscar(numRetiro);
				Alumno a = aa.buscar(am.buscar(r.getNumMatricula()).getCodAlumno());
				ar.eliminar(r);
				a.setEstado(1); // Pasar el estado del alumno a matriculado 
				aa.actualizarArchivo();
				ar.actualizarArchivo();
				mostrarRetiros();
				Mensaje.msg(this, "El retiro se cancelo", TITU_ELIMINAR, Mensaje.INFO);
			}		
		}else {
			Mensaje.msg(this, "Debes seleccionar UNA matricula", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarArreglos();
		mostrarRetiros();
	}

}
