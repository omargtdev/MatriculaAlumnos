package arreglos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import clases.Retiro;
import libreria.Util;

public class ArregloRetiros {
	
	
	private List<Retiro> retiros; 
	
	public ArregloRetiros() {
		retiros = new ArrayList<Retiro>();
		cargarRetiros();
	}
	
	public void agregar(Retiro r) {
		retiros.add(r);
		guardarRetiros();
	}
	

	public Retiro obtener(int r) {
		return retiros.get(r);
	}
	
	public int longitud() {
		return retiros.size();
	}
	
	public void eliminar(Retiro r) {
		retiros.remove(r);
	}
	
	public int codigoCorrelativo() {
		if (longitud() == 0)
			return 200001;
		else
			return obtener(longitud()-1).getNumRetiro() + 1;		
	}
	
	public Retiro buscar(int num) {
		for(int i = 0; i < longitud(); i++) {
			if(obtener(i).getNumRetiro() == num) {
				return obtener(i);
			}
		}
		return null;
	}

	private void cargarRetiros() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/retiros.txt"));
			String[] data;
			String linea;
			while ((linea = br.readLine()) != null) {
				data = linea.split(";");
				agregar(new Retiro(
						Integer.parseInt(data[0]),
						Integer.parseInt(data[1]), 
						data[2], 
						data[3] 
				));
			}
			br.close();
		} catch (Exception e) {
		}
	}
	
	private void guardarRetiros() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("data/retiros.txt"));
			String linea;
			for (int i = 0; i < longitud(); i++) {
				Retiro r = obtener(i);
				linea = Util.puntoComa(r.getNumRetiro()) +
						Util.puntoComa(r.getNumMatricula()) +
						Util.puntoComa(r.getFecha()) +
						Util.puntoComa(r.getHora());
				pw.println(linea);
			}
			pw.close();
		} catch (Exception e) {
		}
	}

	public void actualizarArchivo() {
		guardarRetiros();
	}
	
	
}
