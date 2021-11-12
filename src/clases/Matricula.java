package clases;

import libreria.Tiempo;

public class Matricula {
	

	private int numMatricula, codAlumno, codCurso;
	private String fecha, hora;

	public Matricula(int num, int codAlumno, int codCurso) {
		this.numMatricula = num;
		this.codAlumno = codAlumno;
		this.codCurso = codCurso;
		this.fecha = Tiempo.fecha();
		this.hora = Tiempo.hora();
	}
	
	public Matricula(int num, int codAlumno, int codCurso, String fecha, String hora) {
		this.numMatricula = num;
		this.codAlumno = codAlumno;
		this.codCurso = codCurso;
		this.fecha = fecha;
		this.hora = hora; 
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public int getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(int codAlumno) {
		this.codAlumno = codAlumno;
	}

	public int getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
