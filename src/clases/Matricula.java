package clases;

public class Matricula {
	

	private static int PROX_NUM_MATRICULA;
	private int numMatricula, codAlumno, codCurso;
	private String fecha, hora;
	
	static {
		PROX_NUM_MATRICULA = 100001;
	}

	public Matricula(int codAlumno, int codCurso) {
		this.numMatricula = PROX_NUM_MATRICULA;
		this.codAlumno = codAlumno;
		this.codCurso = codCurso;
		/* Obtener hora y fecha a traves de un metodo */
		/*this.fecha = fecha;
		this.hora = hora; */
		PROX_NUM_MATRICULA++;
	}

	public static int getPROX_NUM_MATRICULA() {
		return PROX_NUM_MATRICULA;
	}

	public static void setPROX_NUM_MATRICULA(int PROX_NUM_MATRICULA) {
		Matricula.PROX_NUM_MATRICULA = PROX_NUM_MATRICULA;
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
