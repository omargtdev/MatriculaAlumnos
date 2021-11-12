package arreglos;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import clases.Matricula;
import libreria.Util;

public class ArregloMatriculas {
	
	private List<Matricula> matriculas;
	
	public ArregloMatriculas() {
		matriculas = new ArrayList<Matricula>();
		cargarMatriculas();
	}
	
	public void agregar(Matricula m) {
		matriculas.add(m); 
		guardarMatriculas();
	}
	
	public int longitud() {
		return matriculas.size();
	}
	
	public Matricula obtener(int i) {
		return matriculas.get(i);
	}
	
	public void eliminar(Matricula m) {
		matriculas.remove(m);
		guardarMatriculas();
	}
	
	private void cargarMatriculas() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/matriculas.txt"));
			String[] data;
			String linea;
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");
				agregar(new Matricula(
						Integer.parseInt(data[0]),
						Integer.parseInt(data[1]), 
						Integer.parseInt(data[2]), 
						data[3], 
						data[4] 
				));
			}
			br.close();
		} catch (Exception e) {
		}
	}
	
	private void guardarMatriculas() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("data/matriculas.txt"));
			String linea;
			for (int i = 0; i < longitud(); i++) {
				Matricula m = obtener(i);
				linea = Util.puntoComa(m.getNumMatricula()) +
						Util.puntoComa(m.getCodAlumno()) +
						Util.puntoComa(m.getCodCurso()) +
						Util.puntoComa(m.getFecha()) +
						Util.puntoComa(m.getHora());
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
		}
	}

	public void actualizarArchivo() {
		guardarMatriculas();
	}
	
	public Matricula buscar(int num) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getNumMatricula() == num) {
				return obtener(i);
			}
		}
		return null;
	}
	
	public int codigoCorrelativo() {
		if (longitud() == 0)
			return 100001;
		else
			return obtener(longitud()-1).getNumMatricula() + 1;		
	}
	
}
