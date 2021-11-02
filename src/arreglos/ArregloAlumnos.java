package arreglos;
import java.util.ArrayList;
import java.util.List;

import clases.Alumno;

public class ArregloAlumnos {
	
	private List<Alumno> alumnos;
	
	public ArregloAlumnos() {
		alumnos = new ArrayList<Alumno>();
	}
	
	public void agregar(Alumno a) {
		alumnos.add(a);
	}
	
	public Alumno obtener(int i) {
		return alumnos.get(i);
	}
	
	public int longitud() {
		return alumnos.size();
	}
	
	public void eliminar(int i) {
		alumnos.remove(i);
	}
	
	public Alumno buscar(int codigo) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getCodAlumno() == codigo) {
				return obtener(i);
			}
		}
		return null;
	}

}
