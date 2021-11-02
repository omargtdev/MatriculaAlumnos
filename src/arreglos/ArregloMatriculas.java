package arreglos;

import java.util.ArrayList;
import java.util.List;

import clases.Matricula;

public class ArregloMatriculas {
	
	private List<Matricula> matriculas;
	
	public ArregloMatriculas() {
		matriculas = new ArrayList<Matricula>();
	}
	
	public void agregar(Matricula m) {
		matriculas.add(m); 
	}
	
	public int longitud() {
		return matriculas.size();
	}
	
	public Matricula obtener(int i) {
		return matriculas.get(i);
	}
	
	/* Sobrecarga en elminar */
	public void eliminar(int i) {
		matriculas.remove(i);
	}
	
	public void eliminar(Matricula m) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getNumMatricula() == m.getNumMatricula()) {
				matriculas.remove(i);
				break;
			}
		}
	}
	
	public Matricula buscar(int num) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getNumMatricula() == num) {
				return obtener(i);
			}
		}
		return null;
	}
	
}
