package arreglos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import clases.Alumno;
import libreria.Util;

public class ArregloAlumnos {
	
	private List<Alumno> alumnos;
	
	public ArregloAlumnos() {
		alumnos = new ArrayList<Alumno>();
		cargarAlumnos();
	}
	
	public void agregar(Alumno a) {
		alumnos.add(a);
		guardarAlumnos();
	}
	
	public Alumno obtener(int i) {
		return alumnos.get(i);
	}
	
	public int longitud() {
		return alumnos.size();
	}
	
	public void eliminar(Alumno a) {
		alumnos.remove(a);
		guardarAlumnos();
	}
	
	public Alumno buscar(int codigo) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getCodAlumno() == codigo) {
				return obtener(i);
			}
		}
		return null;
	}

	public Alumno buscar(String dni) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getDni().equals(dni)) {
				return obtener(i);
			}
		}
		return null;
	}
	
	private void cargarAlumnos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/alumnos.txt"));
			String[] data;
			String linea;
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");
				agregar(new Alumno(
						Integer.parseInt(data[0]),
						data[1], 
						data[2], 
						data[3], 
						Integer.parseInt(data[4]), 
						Integer.parseInt(data[5]),
						Integer.parseInt(data[6])
				));
			}
			br.close();
		} catch (Exception e) {
		}
	}
	
	private void guardarAlumnos() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("data/alumnos.txt"));
			String linea;
			for (int i = 0; i < longitud(); i++) {
				Alumno a = obtener(i);
				linea = Util.puntoComa(a.getCodAlumno()) +
						Util.puntoComa(a.getNombres()) +
						Util.puntoComa(a.getApellidos()) +
						Util.puntoComa(a.getDni()) +
						Util.puntoComa(a.getEdad()) +
						Util.puntoComa(a.getCelular()) +
						Util.puntoComa(a.getEstado());
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
		}
	}
	
	public void actualizarArchivo() {
		guardarAlumnos();
	}
	
	public int codigoCorrelativo() {
		if (longitud() == 0)
			return 202010001;
		else
			return obtener(longitud()-1).getCodAlumno() + 1;		
	}

}
