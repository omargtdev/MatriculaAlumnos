package gui;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import clases.Curso;
import libreria.Mensaje;
import libreria.Util;

import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DlgCurso extends JDialog implements WindowListener, ActionListener {
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
	private JTable tbCurso;
	private DefaultTableModel model;

	
	//Titulos mensajes
	private String TITU_AGREGAR = "Agregar curso";
	private String TITU_MODIFICAR = "Modificar curso";
	private String TITU_CONSULTAR = "Consultar curso";
	private String TITU_ELIMINAR = "Eliminar curso";

	/**
	 * Create the dialog.
	 */
	public DlgCurso() {
		addWindowListener(this);

		setTitle("Curso");
		
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
		
		tbCurso = new JTable();
		scrollPane.setViewportView(tbCurso);
		
		tbCurso = new JTable();
		scrollPane.setViewportView(tbCurso);
		
		model = new DefaultTableModel();
		model.addColumn("COD. CURSO");
		model.addColumn("ASIGNATURA");
		model.addColumn("CICLO");
		model.addColumn("CREDITOS");
		model.addColumn("HORAS");

		tbCurso.setModel(model);
		
		mostrarCursos();
		
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
	private ArregloCursos ac = new ArregloCursos();
	private ArregloMatriculas am = new ArregloMatriculas();

	private void mostrarCursos() {
		model.setRowCount(0);
		for(int i = 0; i < ac.longitud(); i++) {
			Curso c = ac.obtener(i);
			model.addRow(new Object[]{
					c.getCodCurso(),
					c.getAsignatura(), 
					Util.numeroCiclo(c.getCiclo()), 
					c.getCreditos(), 
					c.getHoras()
				});
		}
	}
	
	private void actualizarArreglos() {
		ac = new ArregloCursos();
		am = new ArregloMatriculas();
	}

	private int obtenerCodigo() {
		int codigo = 0;
		while(codigo == 0) {
			try {
				String cod = Mensaje.input(this, "Coloca un codigo de curso", TITU_AGREGAR, "0000");
				if(cod == null){
					Mensaje.msg(this, "Se cancelo la operacion", TITU_AGREGAR, Mensaje.ADVERTENCIA);
					return -1;
				}else if(cod.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un codigo", TITU_AGREGAR, Mensaje.ERROR);
				}else if(cod.trim().length() != 4) {
					Mensaje.msg(this, "El codigo debe ser de 4 digitos", TITU_AGREGAR, Mensaje.ERROR);
				}else {
					codigo = Integer.parseInt(cod.trim());
					if(codigo == 0) {
						Mensaje.msg(this, "Coloca un codigo correcto", TITU_AGREGAR, Mensaje.ERROR);
					}else if(ac.buscar(codigo) != null) {
						Mensaje.msg(this, "Ya existe un curso con este codigo", TITU_AGREGAR, Mensaje.ERROR);
						codigo = 0;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un codigo correcto", TITU_AGREGAR, Mensaje.ERROR);
			}
		}
		return codigo;
	}
	
	private String obtenerAsignatura(String titu, String asigPrevio) {
		String asig = null;
		while(asig == null) {
			asig = Mensaje.input(this, "Coloca la asignatura", titu, asigPrevio);
			if(asig == null) {
				Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
				return asig;
			}else if(asig.trim().equals("")) {
				Mensaje.msg(this, "Ingrese la asignatura", titu, Mensaje.ERROR);
				asig = null;
			}
		}
		return asig.trim();
	}
	
	private int obtenerCiclo(String titu, String cicloPrevio) {
		int ciclo = 0;
		while(ciclo == 0) {
			try {
				String ci = Mensaje.input(this, "Coloca el ciclo (1 al 6)", titu, cicloPrevio);
				if(ci == null){
					Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
					return -1;
				}else if(ci.trim().equals("")) {
					Mensaje.msg(this, "Ingrese un ciclo", titu, Mensaje.ERROR);
				}else {
					ciclo = Integer.parseInt(ci.trim());
					if(ciclo <= 0 || ciclo > 6) {
						Mensaje.msg(this, "Ingrese un ciclo correcto", titu, Mensaje.ERROR);
						ciclo = 0;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese un ciclo correcto", titu, Mensaje.ERROR);
			}
		}
		return ciclo - 1;
	}
	
	private int obtenerCreditos(String titu, String crediPrevio) {
		int creditos = 0;
		while(creditos == 0) {
			try {
				String cre = Mensaje.input(this, "Coloca la cantidad de creditos", titu, crediPrevio);
				if(cre == null){
					Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
					return -1;
				}else if(cre.trim().equals("")) {
					Mensaje.msg(this, "Ingrese una cantidad de creditos", titu, Mensaje.ERROR);
				}else {
					creditos = Integer.parseInt(cre.trim());
					if(creditos <= 0) {
						Mensaje.msg(this, "Ingrese una cantidad correcta", TITU_AGREGAR, Mensaje.ERROR);
						creditos = 0;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una cantidad correcta", titu, Mensaje.ERROR);
			}
		}
		return creditos;
	}
	
	private int obtenerHoras(String titu, String horasPrevias) {
		int horas = 0;
		while(horas == 0) {
			try {
				String hr = Mensaje.input(this, "Coloca la cantidad de horas", TITU_AGREGAR, horasPrevias);
				if(hr == null){
					Mensaje.msg(this, "Se cancelo la operacion", titu, Mensaje.ADVERTENCIA);
					return -1;
				}else if(hr.trim().equals("")) {
					Mensaje.msg(this, "Ingrese una cantidad de horas", titu, Mensaje.ERROR);
				}else {
					horas = Integer.parseInt(hr.trim());
					if(horas <= 0) {
						Mensaje.msg(this, "Ingrese una cantidad correcta", TITU_AGREGAR, Mensaje.ERROR);
						horas = 0;
					}
				}
			} catch (Exception x) {
				Mensaje.msg(this, "Ingrese una cantidad correcta", titu, Mensaje.ERROR);
			}
		}
		return horas;
	}

	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		actualizarArreglos();
		String asig = null;
		int cod, ciclo = -1, cred = -1, horas = -1;
		cod = obtenerCodigo();
		if(cod != -1) {
			asig = obtenerAsignatura(TITU_AGREGAR, "Asignatura");
		}
		if(asig != null) {
			ciclo = obtenerCiclo(TITU_AGREGAR, "0");
		}
		if(ciclo != -1) {
			cred = obtenerCreditos(TITU_AGREGAR, "0");
		}
		if(cred != -1) {
			horas = obtenerHoras(TITU_AGREGAR, "00");
		}
		
		if(horas != -1) {
			String datos = Util.saltoLinea("¿Los datos son correctos?") + 
						   Util.saltoLinea("Codigo     : " + cod) +
						   Util.saltoLinea("Asignatura : " + asig) + 
						   Util.saltoLinea("Ciclo      : " + Util.numeroCiclo(ciclo)) +
						   Util.saltoLinea("Creditos   : " + cred) +
						   Util.saltoLinea("Horas      : " + horas);
			int confir = Mensaje.confirm(this, datos, TITU_AGREGAR);
			if(confir == 0) {
				ac.agregar(new Curso(cod, asig, ciclo, cred, horas));
				ac.actualizarArchivo();
				mostrarCursos();
				int seguir = Mensaje.confirm(this, "¿Desea agregar otro curso?", TITU_AGREGAR);
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
		int cod = Util.obtenerCodigo(tbCurso, model);
		if(cod != -1) {
			Curso c = ac.buscar(cod);
			Mensaje.msg(this, datosCurso(c), TITU_CONSULTAR, Mensaje.INFO);
		}else {
			Mensaje.msg(this, "Debes seleccionar UN curso", "Error", Mensaje.ERROR);
		}
	}
	
	private String datosCurso(Curso c) {
		return Util.saltoLinea("DATOS DEL CURSO") +
			   Util.saltoLinea("Codigo     : " + c.getCodCurso()) +
			   Util.saltoLinea("Asignatura : " + c.getAsignatura()) +
			   Util.saltoLinea("Ciclo      : " + Util.numeroCiclo(c.getCiclo())) +
			   Util.saltoLinea("Creditos   : " + c.getCreditos()) +
			   Util.saltoLinea("Horas      : " + c.getHoras());
	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
		actualizarArreglos();
		int cod = Util.obtenerCodigo(tbCurso , model);
		Curso c = ac.buscar(cod);
		if(c != null) {
			String asig;
			int ciclo = -1, cred = -1, horas = -1;
			asig = obtenerAsignatura(TITU_MODIFICAR, c.getAsignatura());
			if(asig != null) {
				ciclo = obtenerCiclo(TITU_MODIFICAR, (c.getCiclo() + 1) + "");
			}
			if(ciclo != -1) {
				cred = obtenerCreditos(TITU_MODIFICAR, c.getCreditos() + "");
			}
			if(cred != -1) {
				horas = obtenerHoras(TITU_MODIFICAR, c.getHoras() + "");
			}
			
			if(horas != -1) {
				String datos = Util.saltoLinea("¿Los datos son correctos?") + 
							   Util.saltoLinea("Codigo     : " + cod) +
							   Util.saltoLinea("Asignatura : " + asig) + 
							   Util.saltoLinea("Ciclo      : " + Util.numeroCiclo(ciclo)) +
							   Util.saltoLinea("Creditos   : " + cred) +
							   Util.saltoLinea("Horas      : " + horas);
				int confir = Mensaje.confirm(this, datos, TITU_MODIFICAR);
				if(confir == 0) {
					c.setAsignatura(asig);
					c.setCiclo(ciclo);
					c.setCreditos(cred);
					c.setHoras(horas);
					mostrarCursos();
				}else {
					Mensaje.msg(this, "Se cancelo la operacion", TITU_MODIFICAR, Mensaje.ADVERTENCIA);
				}
			}
		}else {
			Mensaje.msg(this, "Debes seleccionar UN curso", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		actualizarArreglos();
		int cod = Util.obtenerCodigo(tbCurso, model);
		if(cod != -1) {
			Curso c = ac.buscar(cod);
			int confir = Mensaje.confirm(this, "¿Estas seguro de retirar este curso?", TITU_ELIMINAR);
			if(confir == 0) {
				boolean eliminar = true;
				for(int i = 0; i < am.longitud(); i++) {
					if(am.obtener(i).getCodCurso() == c.getCodCurso()) {
						eliminar = false;
						break;
					}
				}
				if(eliminar) {
					ac.eliminar(c);
					ac.actualizarArchivo();
					mostrarCursos();
					Mensaje.msg(this, "El curso fue retirado correctamente", TITU_ELIMINAR, Mensaje.INFO);
				}else {
					Mensaje.msg(this, "No puedes eliminar un curso que tiene alumnos matriculados en el", TITU_ELIMINAR, Mensaje.ERROR);
				}
			}		
		}else {
			Mensaje.msg(this, "Debes seleccionar UN curso", "Error", Mensaje.ERROR);
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizarArreglos();
		mostrarCursos();
	}

}
