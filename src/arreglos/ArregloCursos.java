package arreglos;

import java.util.ArrayList;
import java.util.List;

import clases.Curso;

public class ArregloCursos {
	
	private List<Curso> cursos; 
	
	public ArregloCursos() {
		cursos = new ArrayList<Curso>();
	}
	
	public void agregar(Curso c) {
		cursos.add(c);
	}
	
	public Curso obtener(int i) {
		return cursos.get(i);
	}
	
	public int longitud() {
		return cursos.size();
	}

	public void eliminar(int i) {
		cursos.remove(i);
	}
	
}
