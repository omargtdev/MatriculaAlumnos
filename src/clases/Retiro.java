package clases;

public class Retiro {
	
	private static int PROX_NUM_MATRICULA;
	private int numRetiro, numMatricula;
	private String fecha, hora;
	
	static {
		PROX_NUM_MATRICULA = 200001;
	}

	public Retiro(int numMatricula) {
		this.numRetiro = PROX_NUM_MATRICULA;
		this.numMatricula = numMatricula;
		/*this.fecha = fecha;
		this.hora = hora;*/
		PROX_NUM_MATRICULA++;
	}

	public static int getPROX_NUM_MATRICULA() {
		return PROX_NUM_MATRICULA;
	}

	public static void setPROX_NUM_MATRICULA(int proxnum) {
		PROX_NUM_MATRICULA = proxnum;
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
