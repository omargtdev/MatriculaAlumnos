package clases;

public class Alumno {
	
	
	private static int PROX_CODIGO_ALUMNO;
	private String nombres, apellidos, dni;
	private int codAlumno, edad, celular, estado;
	
	static {
		PROX_CODIGO_ALUMNO = 202010001;
	}

	public Alumno(String nombres, String apellidos, String dni, int edad, int celular) {
		this.codAlumno = PROX_CODIGO_ALUMNO;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
		this.celular = celular;
		this.estado = 0;
		PROX_CODIGO_ALUMNO++;
	}

	public static int getPROX_CODIGO_ALUMNO() {
		return PROX_CODIGO_ALUMNO;
	}

	public static void setPROX_CODIGO_ALUMNO(int proxcodigo) {
		PROX_CODIGO_ALUMNO = proxcodigo;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(int codAlumno) {
		this.codAlumno = codAlumno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
