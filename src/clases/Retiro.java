package clases;

import libreria.Tiempo;

public class Retiro {
	
	private int numRetiro, numMatricula;
	private String fecha, hora;
	
	public Retiro(int num, int numMatricula) {
		this.numRetiro = num;
		this.numMatricula = numMatricula;
		this.fecha = Tiempo.fecha();
		this.hora = Tiempo.hora();
	}
	
	public Retiro(int num, int numMatricula, String fecha, String hora) {
		this.numRetiro = num;
		this.numMatricula = numMatricula;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getNumRetiro() {
		return numRetiro;
	}

	public void setNumRetiro(int numRetiro) {
		this.numRetiro = numRetiro;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
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
