package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Alumno;
import clases.Matricula;
import libreria.Util;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DlgMatricula extends JDialog implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Si existe la ventana no abrimos otra, usamos la que tenemos
	public static boolean exists = false;
	private JLabel lblNumMatricula;
	private JLabel lblCodAlumno;
	private JTextField txtNumMatricula;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tbMatricula;
	private JComboBox<Object> cboCodAlumno;
	private JComboBox<Object> cboCodCurso;
	private DefaultTableModel model;

	/**
	 * Create the dialog.
	 */
	public DlgMatricula() {
		
		addWindowListener(this);
		
		exists = true;

		setTitle("Matr\u00EDcula");
		setBounds(100, 100, 755, 440);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		lblNumMatricula = new JLabel("N\u00FAmero de la Matr\u00EDcula");
		lblNumMatricula.setBounds(10, 11, 114, 14);
		getContentPane().add(lblNumMatricula);
		
		lblCodAlumno = new JLabel("C\u00F3digo del Alumno");
		lblCodAlumno.setBounds(10, 36, 94, 14);
		getContentPane().add(lblCodAlumno);
		
		txtNumMatricula = new JTextField();
		txtNumMatricula.setEditable(false);
		txtNumMatricula.setBounds(134, 8, 100, 20);
		getContentPane().add(txtNumMatricula);
		txtNumMatricula.setColumns(10);
		
		JLabel lblCodCurso = new JLabel("C\u00F3digo del Curso");
		lblCodCurso.setBounds(10, 61, 100, 14);
		getContentPane().add(lblCodCurso);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(10, 90, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(598, 7, 131, 23);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(598, 36, 131, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(598, 65, 131, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 719, 266);
		getContentPane().add(scrollPane);
		
		/* cargar matriculas */
		tbMatricula = new JTable();
		scrollPane.setViewportView(tbMatricula);
		
		model = new DefaultTableModel();
		model.addColumn("NUM. MATRICULA");
		model.addColumn("COD. ALUMNO");
		model.addColumn("COD. CURSO");
		model.addColumn("FECHA");
		model.addColumn("HORA");

		tbMatricula.setModel(model);

		cboCodAlumno = new JComboBox<Object>();
		cboCodAlumno.setEnabled(false);
		cboCodAlumno.setBounds(134, 32, 100, 22);
		getContentPane().add(cboCodAlumno);
		
		cboCodCurso = new JComboBox<Object>();
		cboCodCurso.setEnabled(false);
		cboCodCurso.setBounds(134, 57, 100, 22);
		getContentPane().add(cboCodCurso);
		
		cargarCodigos();
		mostrarMatriculas();
	}
	
	
	private void cargarCodigos() {
		cboCodAlumno.removeAllItems();
		cboCodCurso.removeAllItems();
		for(int i = 0; i < MenuPrincipal.arrAlumnos.longitud(); i++) {
			// Mostraremos los alumnos que esten solo registrados
			if(MenuPrincipal.arrAlumnos.obtener(i).getEstado() == 0) {
				cboCodAlumno.addItem(MenuPrincipal.arrAlumnos.obtener(i).getCodAlumno());
			}
		}
		for (int i = 0; i < MenuPrincipal.arrCursos.longitud(); i++) {
			cboCodCurso.addItem(MenuPrincipal.arrCursos.obtener(i).getCodCurso());
		}
	}
	
	private void mostrarMatriculas() {
		model.setRowCount(0);
		for(int i = 0; i < MenuPrincipal.arrMatriculas.longitud(); i++) {
			Matricula m = MenuPrincipal.arrMatriculas.obtener(i);
			model.addRow(new Object[]{
					m.getNumMatricula(), 
					m.getCodAlumno(), 
					m.getCodCurso(), 
					m.getFecha(), 
					m.getHora()
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

	// Colocar la ventana como "inexistente" al cerrarla
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
	
	// metodo para libreria (lib)
/*	private void habilitarCampos(JTextField[] campos, JButton boton) {
		for(int i = 0; i < campos.length; i++) {
			campos[i].setEditable(true);
		}
		boton.setEnabled(true);
	}*/
	/* util */
	
	private void deshabilitarCampos() {
		cboCodAlumno.setEnabled(false);
		cboCodCurso.setEnabled(false);
		btnAceptar.setEnabled(false);
		txtNumMatricula.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
		if (e.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(e);
		}
	}

	// Agregar matricular
	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		//Actualizar codigos
		cargarCodigos();
		deshabilitarCampos();
		if(cboCodAlumno.getItemCount() != 0 && cboCodCurso.getItemCount() != 0) {
			//Cambiar al estado del btnAceptar a 0 (adicionar)
			estadoBtnAceptar = 0;
			Util.habilitarElemento(cboCodAlumno);
			Util.habilitarElemento(cboCodCurso);
			Util.habilitarElemento(btnAceptar);
			txtNumMatricula.setText(Matricula.getPROX_NUM_MATRICULA() + "");
		}else{
			Util.mensaje(this, "No se puede crear una matricula si no hay almenos un curso y un alumno (registrado)", 
					"Error de Matricula",
					"error"
				);
		}
	}
	
	private int obtenerCodigo(JComboBox<Object> cbo) {
		return (int) cbo.getSelectedItem();
	}
	
	/* Adicionar -> 0
	 * Modificar -> 1
	 */

	private int estadoBtnAceptar = 0;

	protected void actionPerformedBtnAceptar(ActionEvent e) {
		//Adicionar
		if(estadoBtnAceptar == 0) {
			// Creando y agregando la nueva matricula
			MenuPrincipal.arrMatriculas.agregar(new Matricula(
					obtenerCodigo(cboCodAlumno),
					obtenerCodigo(cboCodCurso)
			));
			//Buscar y cambiar el estado a matriculado (1)
			Alumno a = MenuPrincipal.arrAlumnos.buscar(obtenerCodigo(cboCodAlumno));
			if(a != null) {
				a.setEstado(1);
			}else {
				Util.mensaje(this, "Ocurrio un error", "Error", "error");
			}
		// Modificar
		}else {
			//Buscar la matricula y actualizar el curso seleccionado , si este cambia
			Matricula m = MenuPrincipal.arrMatriculas.buscar(Integer.parseInt(txtNumMatricula.getText()));
			int codCurso = obtenerCodigo(cboCodCurso);
			boolean cambio = (codCurso == m.getCodCurso()) ? false : true;
			if(cambio) {
				m.setCodCurso(codCurso);
			}else {
				Util.mensaje(this, "Debes cambiar el codigo del curso para modificar", "Alerta", "warning");
				return;
			}
		}
		//Actualizar codigos y matriculas
		deshabilitarCampos();
		cargarCodigos();
		mostrarMatriculas();
	}
	
	
	private void habilitarParaModificar(Matricula m) {
		txtNumMatricula.setText(m.getNumMatricula() + "");
		//Mostramos solo el codigo del alumno que vamos a modificar
		cboCodAlumno.removeAllItems();
		cboCodAlumno.addItem(m.getCodAlumno() + "");
		cboCodAlumno.setEnabled(false);
		Util.habilitarElemento(cboCodCurso);
		Util.habilitarElemento(btnAceptar);
	}

	protected void actionPerformedBtnModificar(ActionEvent e) {
		int num = Util.obtenerCodigo(tbMatricula, model);
		if(num != -1) {
			//Cambiar al estado del btnAceptar a 1 (modificar)
			estadoBtnAceptar = 1;
			Matricula m = MenuPrincipal.arrMatriculas.buscar(num);
			if(m != null) {
				habilitarParaModificar(m);
			}else {
				Util.mensaje(this, "Ocurrio un error", "Error", "error");
			}
		}else {
			Util.mensaje(this, "Debes seleccionar UNA matricula", "Error", "error");
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		int num = Util.obtenerCodigo(tbMatricula, model);
		if(num != -1) {
			deshabilitarCampos();
			Matricula m = MenuPrincipal.arrMatriculas.buscar(num);
			//Preguntar por la eliminacion 
			int confir = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar esta matricula?");
			System.out.println(confir);
			if(confir == 0) {
				if(evaluarEliminacion(m.getCodAlumno())) { // Verificar si la matricula no esta retirado 
					MenuPrincipal.arrMatriculas.eliminar(m);
					//Cambiar el codigo del alumno en registrado
					MenuPrincipal.arrAlumnos.buscar(m.getCodAlumno()).setEstado(0);
					cargarCodigos();
				}else {
					Util.mensaje(this, "No puedes eliminar una matricula que esta retirada", "Error de cancelacion", "error");
				}
			}
		}else {
			Util.mensaje(this, "Debes seleccionar UNA matricula", "Error", "error");
		}

	}
	
	private boolean evaluarEliminacion(int cod) {
		Alumno a = MenuPrincipal.arrAlumnos.buscar(cod);
		System.out.println(a);
		return (a.getCodAlumno() == 2)  ? false : true;
	}

}