package arreglos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import clases.Curso;
import libreria.Util;

public class ArregloCursos {
	
	private List<Curso> cursos; 
	
	public ArregloCursos() {
		cursos = new ArrayList<Curso>();
		cargarCursos();
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

	public void eliminar(Curso c) {
		cursos.remove(c);
	}
	
	public Curso buscar(int codigo) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getCodCurso() == codigo) {
				return obtener(i);
			}
		}
		return null;
	}
	
	private void cargarCursos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/cursos.txt"));
			String[] data;
			String linea;
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");
				agregar(new Curso(
						Integer.parseInt(data[0]),
						data[1], 
						Integer.parseInt(data[2]), 
						Integer.parseInt(data[3]),
						Integer.parseInt(data[4])
				));
			}
			br.close();
		} catch (Exception e) {
		}
	}
	
	private void guardarCursos() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("data/cursos.txt"));
			String linea;
			for (int i = 0; i < longitud(); i++) {
				Curso c = obtener(i);
				linea = Util.puntoComa(c.getCodCurso()) +
						Util.puntoComa(c.getAsignatura()) +
						Util.puntoComa(c.getCiclo()) +
						Util.puntoComa(c.getCreditos()) +
						Util.puntoComa(c.getHoras());
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
		}
	}
	
	public void actualizarArchivo() {
		guardarCursos();
	}
	
}
