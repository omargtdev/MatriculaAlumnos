package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	private DlgAlumno dlgAlumno;
	private DlgCurso dlgCurso; 
	private DlgConsultaAlumnosCursos dlgConsultaAlumnosCursos;
	private DlgConsultaMatriculasRetiros dlgConsultaMatriculasRetiros;
	private DlgAlumnosMatriculadosPorCurso dlgAlumnosMatriculadosPorCurso;
	private DlgAlumnosMatriculaPendiente dlgAlumnosMatriculaPendiente;
	private DlgAlumnosMatriculaVigente dlgAlumnosMatriculaVigente;

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
		
		setTitle("Cibertec - Registro y Matr\u00EDcula de Alumnos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 472);
		
		mnuBar = new JMenuBar();
		setJMenuBar(mnuBar);
		
		mnuMantenimiento = new JMenu("Mantenimiento");
		mnuBar.add(mnuMantenimiento);
		
		mnuItemAlumno = new JMenuItem("Alumno");
		mnuItemAlumno.addActionListener(this);
		mnuMantenimiento.add(mnuItemAlumno);
		
		mnuItemCurso = new JMenuItem("Curso");
		mnuItemCurso.addActionListener(this);
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
		mnuItemAlumYCursos.addActionListener(this);
		mnuConsulta.add(mnuItemAlumYCursos);
		
		mnuItemMatriYRetiros = new JMenuItem("Matr\u00EDculas y retiros");
		mnuItemMatriYRetiros.addActionListener(this);
		mnuConsulta.add(mnuItemMatriYRetiros);
		
		mnuReporte = new JMenu("Reporte");
		mnuBar.add(mnuReporte);
		
		mnuItemAlumMatriPen = new JMenuItem("Alumnos con matr\u00EDcula pendiente");
		mnuItemAlumMatriPen.addActionListener(this);
		mnuReporte.add(mnuItemAlumMatriPen);
		
		mnuItemAlumMatriVige = new JMenuItem("Alumnos con matr\u00EDcula vigente");
		mnuItemAlumMatriVige.addActionListener(this);
		mnuReporte.add(mnuItemAlumMatriVige);
		
		mnuItemAlumMatriCurso = new JMenuItem("Alumnos matriculados por curso");
		mnuItemAlumMatriCurso.addActionListener(this);
		mnuReporte.add(mnuItemAlumMatriCurso);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnuItemAlumMatriCurso) {
			actionPerformedMnuItemAlumMatriCurso(e);
		}
		if (e.getSource() == mnuItemAlumMatriVige) {
			actionPerformedMnuItemAlumMatriVige(e);
		}
		if (e.getSource() == mnuItemAlumMatriPen) {
			actionPerformedMnuItemAlumMatriPen(e);
		}
		if (e.getSource() == mnuItemMatriYRetiros) {
			actionPerformedMnuItemMatriYRetiros(e);
		}
		if (e.getSource() == mnuItemAlumYCursos) {
			actionPerformedMnuItemAlumYCursos(e);
		}
		if (e.getSource() == mnuItemCurso) {
			actionPerformedMnuItemCurso(e);
		}
		if (e.getSource() == mnuItemAlumno) {
			actionPerformedMnuItemAlumno(e);
		}
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

	protected void actionPerformedMnuItemAlumno(ActionEvent e) {
		if(!DlgAlumno.exists) {
			dlgAlumno = new DlgAlumno();
			dlgAlumno.setVisible(true);
		}else {
			dlgAlumno.toFront();
		}
	}

	protected void actionPerformedMnuItemCurso(ActionEvent e) {
		if(!DlgCurso.exists) {
			dlgCurso = new DlgCurso();
			dlgCurso.setVisible(true);
		}else {
			dlgCurso.toFront();
		}
	}

	protected void actionPerformedMnuItemAlumYCursos(ActionEvent e) {
		if(!DlgConsultaAlumnosCursos.exists) {
			dlgConsultaAlumnosCursos = new DlgConsultaAlumnosCursos();
			dlgConsultaAlumnosCursos.setVisible(true);
			
		}else {
			dlgConsultaAlumnosCursos.toFront();
		}
	}

	protected void actionPerformedMnuItemMatriYRetiros(ActionEvent e) {
		if(!DlgConsultaMatriculasRetiros.exists) {
			dlgConsultaMatriculasRetiros = new DlgConsultaMatriculasRetiros();
			dlgConsultaMatriculasRetiros.setVisible(true);
		}else {
			dlgConsultaMatriculasRetiros.toFront();
		}
	}

	protected void actionPerformedMnuItemAlumMatriPen(ActionEvent e) {
		if(!DlgAlumnosMatriculaPendiente.exists) {
			dlgAlumnosMatriculaPendiente = new DlgAlumnosMatriculaPendiente();
			dlgAlumnosMatriculaPendiente.setVisible(true);
		}else {
			dlgAlumnosMatriculaPendiente.toFront();
		}
	}

	protected void actionPerformedMnuItemAlumMatriVige(ActionEvent e) {
		if(!DlgAlumnosMatriculaVigente.exists) {
			dlgAlumnosMatriculaVigente = new DlgAlumnosMatriculaVigente();
			dlgAlumnosMatriculaVigente.setVisible(true);
		}else {
			dlgAlumnosMatriculaVigente.toFront();
		}
	}

	protected void actionPerformedMnuItemAlumMatriCurso(ActionEvent e) {
		if(!DlgAlumnosMatriculadosPorCurso.exists) {
			dlgAlumnosMatriculadosPorCurso = new DlgAlumnosMatriculadosPorCurso();
			dlgAlumnosMatriculadosPorCurso.setVisible(true);
		}else {
			dlgAlumnosMatriculadosPorCurso.toFront();
		}
	}

}
