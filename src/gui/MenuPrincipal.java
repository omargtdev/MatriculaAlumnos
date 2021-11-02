package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import arreglos.ArregloAlumnos;
import arreglos.ArregloCursos;
import arreglos.ArregloMatriculas;
import arreglos.ArregloRetiros;
import clases.Alumno;
import clases.Curso;
import clases.Matricula;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar mnuBar;
	private JMenu mnuMantenimiento;
	private JMenuItem mnuItemAlumno;
	private JMenuItem mnuItemCurso;
	private JMenu mnuRegistro;
	private JMenuItem mnuItemMatricula;
	private JMenuItem mnuItemRetiro;
	private JMenu mnuConsulta;
	private JMenuItem mnuItemAlumYCursos;
	private JMenuItem mnuItemMatriYRetiros;
	private JMenu mnuReporte;
	private JMenuItem mnuItemAlumMatriPen;
	private JMenuItem mnuItemAlumMatriVige;
	private JMenuItem mnuItemAlumMatriCurso;
	
	// Dialogs
	private DlgMatricula dlgMatricula;
	private DlgRetiro dlgRetiro;
	
	
	// arrays
	public static ArregloAlumnos arrAlumnos;
	public static ArregloCursos arrCursos;
	public static ArregloMatriculas arrMatriculas;
	public static ArregloRetiros arrRetiros;

	// inicializador
	static {
		arrAlumnos = new ArregloAlumnos();
		arrCursos = new ArregloCursos();
		arrMatriculas = new ArregloMatriculas();
		arrRetiros = new ArregloRetiros();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		
		/* Create student via gui*/
		arrAlumnos.agregar(new Alumno("Omar", "Gutierrez Tafur", "74772272", 18, 998877665));
		arrAlumnos.agregar(new Alumno("Javier", "Gutierrez Tafur", "74772272", 18, 998877665));
		arrAlumnos.agregar(new Alumno("Juan", "Gutierrez Tafur", "74772272", 18, 998877665));
		arrAlumnos.agregar(new Alumno("Aldair", "Gutierrez Tafur", "74772272", 18, 998877665));
		System.out.println("Alumnos " + arrAlumnos.longitud());
		
		arrCursos.agregar(new Curso(1001, "MATEMATICA", 0, 3, 30));
		arrCursos.agregar(new Curso(1002, "COMUNICACION", 0, 3, 25));
		arrCursos.agregar(new Curso(1003, "DISEÑO WEB", 0, 4, 35));
		arrCursos.agregar(new Curso(1004, "INTRODUCCION A JAVASCRIPT", 0, 3, 30));
		System.out.println("Cursos " + arrCursos.longitud());

		setTitle("Cibertec - Registro y Matr\u00EDcula de Alumnos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 472);
		
		mnuBar = new JMenuBar();
		setJMenuBar(mnuBar);
		
		mnuMantenimiento = new JMenu("Mantenimiento");
		mnuBar.add(mnuMantenimiento);
		
		mnuItemAlumno = new JMenuItem("Alumno");
		mnuMantenimiento.add(mnuItemAlumno);
		
		mnuItemCurso = new JMenuItem("Curso");
		mnuMantenimiento.add(mnuItemCurso);
		
		mnuRegistro = new JMenu("Registro");
		mnuBar.add(mnuRegistro);
		
		mnuItemMatricula = new JMenuItem("Matr\u00EDcula");
		mnuItemMatricula.addActionListener(this);
		mnuRegistro.add(mnuItemMatricula);
		
		mnuItemRetiro = new JMenuItem("Retiro");
		mnuItemRetiro.addActionListener(this);
		mnuRegistro.add(mnuItemRetiro);
		
		mnuConsulta = new JMenu("Consulta");
		mnuBar.add(mnuConsulta);
		
		mnuItemAlumYCursos = new JMenuItem("Alumnos y cursos");
		mnuConsulta.add(mnuItemAlumYCursos);
		
		mnuItemMatriYRetiros = new JMenuItem("Matr\u00EDculas y retiros");
		mnuConsulta.add(mnuItemMatriYRetiros);
		
		mnuReporte = new JMenu("Reporte");
		mnuBar.add(mnuReporte);
		
		mnuItemAlumMatriPen = new JMenuItem("Alumnos con matr\u00EDcula pendiente");
		mnuReporte.add(mnuItemAlumMatriPen);
		
		mnuItemAlumMatriVige = new JMenuItem(" Alumnos con matr\u00EDcula vigente");
		mnuReporte.add(mnuItemAlumMatriVige);
		
		mnuItemAlumMatriCurso = new JMenuItem("Alumnos matriculados por curso");
		mnuReporte.add(mnuItemAlumMatriCurso);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnuItemRetiro) {
			actionPerformedMnuItemRetiro(e);
		}
		if (e.getSource() == mnuItemMatricula) {
			actionPerformedMnuItemMatricula(e);
		}
	}

	protected void actionPerformedMnuItemMatricula(ActionEvent e) {
		if(!DlgMatricula.exists) {
			dlgMatricula = new DlgMatricula();
			dlgMatricula.setVisible(true);
		}else {
			dlgMatricula.toFront();
		}
	}

	protected void actionPerformedMnuItemRetiro(ActionEvent e) {
		if(!DlgRetiro.exists) {
			dlgRetiro = new DlgRetiro();
			dlgRetiro.setVisible(true);
		}else {
			dlgRetiro.toFront();
		}
	}
}
