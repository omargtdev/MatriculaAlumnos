package gui;

import javax.swing.JDialog;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloAlumnos;
import clases.Alumno;
import libreria.Mensaje;
import libreria.Util;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DlgAlumno extends JDialog implements WindowListener, ActionListener {
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
	private JTable tbAlumno;
	private DefaultTableModel model;

	
	//Titulos mensajes
	private String TITU_AGREGAR = "Agregar alumno";
	private String TITU_MODIFICAR = "Modificar alumno";
	private String TITU_CONSULTAR = "Consultar alumno";
	private String TITU_ELIMINAR = "Eliminar alumno";

	/**
	 * Create the dialog.
	 */
	public DlgAlumno() {
		addWindowListener(this);

		setTitle("Alumno");
		
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
		
		tbAlumno = new JTable();
		scrollPane.setViewportView(tbAlumno);
		
		tbAlumno = new JTable();
		scrollPane.setViewportView(tbAlumno);
		
		model = new DefaultTableModel();
		model.addColumn("COD. ALUMNO");
		model.addColumn("NOMBRES");
		model.addColumn("APELLIDOS");
		model.addColumn("DNI");
		model.addColumn("EDAD");
		model.addColumn("CELULAR");
		model.addColumn("ESTADO");

		tbAlumno.setModel(model);
		
		mostrarAlumnos();
		
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

	/* arreglos */
	private ArregloAlumnos aa = new ArregloAlumnos();

	private void mostrarAlumnos() {
		model.setRowCount(0);
		for(int i = 0; i < aa.longitud(); i++) {
			Alumno a = aa.obtener(i);
			model.addRow(new Object[]{
					a.getCodAlumno(), 
					a.getNombres(), 
					a.getApellidos(), 
					a.getDni(), 
					a.getEdad(),
					a.getCelular(),
					Util.nombreEstado(a.getEstado())
				});
		}
	}
	
	private void actualizarArreglos() {
		aa = new ArregloAlumnos();
	}
	
	
	private String obtenerNombre(String titu, String nomPrevio) {
		String nom = null;
		while(nom == null) {
			nom = Mensaje.input(this, "Coloca el nombre del alumno", titu, nomPrevio);
			if(nom == null) {
				Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
				return nom;
			}else if(nom.trim().equals("")) {
				Mensaje.msg(this, "Ingrese un nombre", titu, Mensaje.ERROR);
				nom = null;
			}
		}
		return nom.trim();
		
	}
	
	private String obtenerApellidoPaterno(String titu, String apePrevio) {
		String ape = null;
		while(ape == null) {
			ape = Mensaje.input(this, "Coloca el apellido paterno del alumno", titu, apePrevio);
			if(ape == null) {
				Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
				return ape;
			}else if(ape.trim().equals("")) {
				Mensaje.msg(this, "Ingrese el apellido paterno", titu, Mensaje.ERROR);
				ape = null;
			}
		}
		return ape.trim();
	}
	
	private String obtenerApellidoMaterno(String titu, String apePrevio) {
		String ape = null;
		while(ape == null) {
			ape = Mensaje.input(this, "Coloca el apellido materno del alumno", titu, apePrevio);
			if(ape == null) {
				Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
				return ape;
			}else if(ape.trim().equals("")) {
				Mensaje.msg(this, "Ingrese el apellido materno", titu, Mensaje.ERROR);
				ape = null;
			}
		}
		return ape.trim();
	}
	
	private String obtenerDni() {
		String dni = null;
		while(dni == null) {
			try {
				dni = Mensaje.input(this, "Coloca el DNI del alumno", TITU_AGREGAR, "00000000");
				if(dni == null) {
					Mensaje.msg(this, "Se cancelo la operacion", TITU_AGREGAR, Mensaje.ADVERTENCIA);
					return dni;
				}else if(dni.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un DNI", TITU_AGREGAR, Mensaje.ERROR);
				}else if(aa.buscar(dni) != null) {
					Mensaje.msg(this, "Ya existe un alumno con este DNI", TITU_AGREGAR, Mensaje.ERROR);
				}else if(dni.trim().length() != 8) {
					Mensaje.msg(this, "El DNI debe ser de 8 numeros", TITU_AGREGAR, Mensaje.ERROR);
				}else {
					Integer.parseInt(dni);
					break;
				}
				dni = null;
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un DNI correcto", TITU_AGREGAR, Mensaje.ERROR);
				dni = null;
			}
		}
		return dni.trim();
	}
	
	private int obtenerEdad(String titu, String edadPrevio) {
		int edad = 0;
		while(edad == 0) {
			try {
				String ed = Mensaje.input(this, "Coloca la edad del alumno", TITU_AGREGAR, edadPrevio);
				if(ed == null){
					Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
					return -1;
				}else if(ed.trim().equals("")) {
					Mensaje.msg(this, "Ingrese una edad", titu, Mensaje.ERROR);
				}else {
					edad = Integer.parseInt(ed.trim());
					if(edad <= 0) {
						Mensaje.msg(this, "Ingrese una edad correcta", titu, Mensaje.ERROR);
						edad = 0;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una edad correcta", titu, Mensaje.ERROR);
			}
		}
		return edad;
	}
	
	private int obtenerCelular(String titu, String celPrevio) {
		int celular = 0;
		while(celular == 0) {
			try {
				String cel = Mensaje.input(this, "Coloca el celular del alumno", TITU_AGREGAR, celPrevio);
				if(cel == null){
					Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
					return -1;
				}else if(cel.trim().equals("")) {
					Mensaje.msg(this, "Ingrese una edad", titu, Mensaje.ERROR);
				}else if(cel.trim().length() != 9) {
					Mensaje.msg(this, "El celular debe ser de 9 numeros", TITU_AGREGAR, Mensaje.ERROR);
				}else {
					celular = Integer.parseInt(cel.trim());
					if(celular <= 0) {
						// TODO : validar que comeinze con 9
						Mensaje.msg(this, "Ingrese un celular correcto", TITU_AGREGAR, Mensaje.ERROR);
						celular = 0;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un celular correcto", titu, Mensaje.ERROR);
			}
		}
		return celular;
	}

	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		actualizarArreglos();
		String nom, ape = null, dni = null;
		int edad = -1, cel = -1;
		nom = obtenerNombre(TITU_AGREGAR, "Algun nombre");
		if(nom != null) {
			ape = obtenerApellidoPaterno(TITU_AGREGAR, "Algun apellido");
		}
		if(ape != null) {
			String apeMater = obtenerApellidoMaterno(TITU_AGREGAR, "Algun apellido");
			if(apeMater != null) {
				ape += " " + apeMater;
				dni = obtenerDni();
			}
		}
		if(dni != null) {
			edad = obtenerEdad(TITU_AGREGAR, "00");
		}
		if(edad != -1) {
			cel = obtenerCelular(TITU_AGREGAR, "000000000");
		}
		
		if(cel != -1) {
			String datos = Util.saltoLinea("¿Los datos son correctos?") + 
						   Util.saltoLinea("Nombre(s)  : " + nom) +
						   Util.saltoLinea("Apellidos  : " + ape) + 
						   Util.saltoLinea("DNI        : " + dni) +
						   Util.saltoLinea("Edad       : " + edad) +
						   Util.saltoLinea("Celular    : " + cel);
			int confir = Mensaje.confirm(this, datos, TITU_AGREGAR);
			if(confir == 0) {
				aa.agregar(new Alumno(aa.codigoCorrelativo(), nom, ape, dni, edad, cel));
				aa.actualizarArchivo();
				mostrarAlumnos();
				int seguir = Mensaje.confirm(this, "¿Desea agregar otro alumno?", TITU_AGREGAR);
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
		int cod = Util.obtenerCodigo(tbAlumno, model);
		if(cod != -1) {
			Alumno a = aa.buscar(cod);
			Mensaje.msg(this, datosAlumno(a), TITU_CONSULTAR, Mensaje.INFO);
		}else {
			Mensaje.msg(this, "Debes seleccionar UNA matricula", "Error", Mensaje.ERROR);
		}
	}
	
	private String datosAlumno(Alumno a) {
		return Util.saltoLinea("DATOS DEL ALUMNO") +
			   Util.saltoLinea("Codigo    : " + a.getCodAlumno()) +
			   Util.saltoLinea("Nombre(s) : " + a.getNombres()) +
			   Util.saltoLinea("Apellidos : " + a.getApellidos()) +
			   Util.saltoLinea("DNI       : " + a.getDni()) +
			   Util.saltoLinea("Edad      : " + a.getEdad()) +
			   Util.saltoLinea("Celular   : " + a.getCelular()) +
			   Util.saltoLinea("Estado    : " + Util.nombreEstado(a.getEstado()));
	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
		actualizarArreglos();
		int cod = Util.obtenerCodigo(tbAlumno, model);
		Alumno a = aa.buscar(cod);
		if(a != null) {
			String nom, ape = null;
			int edad = -1, cel = -1;
			String apellidos[] = a.getApellidos().split(" ");
			nom = obtenerNombre(TITU_MODIFICAR, a.getNombres());
			if(nom != null) {
				ape = obtenerApellidoPaterno(TITU_MODIFICAR, apellidos[0]);
			}
			if(ape != null) {
				String apeMater = obtenerApellidoMaterno(TITU_MODIFICAR, apellidos[1]);
				if(apeMater != null) {
					ape += " " + apeMater;
					edad = obtenerEdad(TITU_MODIFICAR, a.getEdad() + "");
				}
			}
			if(edad != -1) {
				cel = obtenerCelular(TITU_MODIFICAR, a.getCelular() + "");
			}
			
			if(cel != -1) {
				String datos = Util.saltoLinea("¿Los datos son correctos?") + 
							   Util.saltoLinea("Codigo     : " + cod) +
							   Util.saltoLinea("Nombre(s)  : " + nom) +
							   Util.saltoLinea("Apellidos  : " + ape) + 
							   Util.saltoLinea("Edad       : " + edad) +
							   Util.saltoLinea("Celular    : " + cel);
				int confir = Mensaje.confirm(this, datos, TITU_MODIFICAR);
				if(confir == 0) {
					a.setNombres(nom);
					a.setApellidos(ape);
					a.setEdad(edad);
					a.setCelular(cel);
					aa.actualizarArchivo();
					mostrarAlumnos();
				}else {
					Mensaje.msg(this, "Se cancelo la operacion", TITU_MODIFICAR, Mensaje.ADVERTENCIA);
				}
			}
		}else {
			Mensaje.msg(this, "Debes seleccionar UN alumno", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		actualizarArreglos();
		int cod = Util.obtenerCodigo(tbAlumno, model);
		if(cod != -1) {
			Alumno a = aa.buscar(cod);
			int confir = Mensaje.confirm(this, "¿Estas seguro de retirar este alumno?", TITU_ELIMINAR);
			if(confir == 0) {
				if(a.getEstado() != 0) {
					Mensaje.msg(this, "No puedes eliminar un alumno que esta matriculado o retirado", TITU_ELIMINAR, Mensaje.ERROR);
				}else {
					aa.eliminar(a);
					aa.actualizarArchivo();
					mostrarAlumnos();
					Mensaje.msg(this, "El alumno fue retirado correctamente", TITU_ELIMINAR, Mensaje.INFO);
				}
			}		
		}else {
			Mensaje.msg(this, "Debes seleccionar UN alumno", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarArreglos();
		mostrarAlumnos();
	}

}
