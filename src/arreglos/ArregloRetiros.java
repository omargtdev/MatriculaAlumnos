package arreglos;

import java.util.ArrayList;
import java.util.List;

import clases.Retiro;

public class ArregloRetiros {

	private List<Retiro> retiros; 
	
	public ArregloRetiros() {
		retiros = new ArrayList<Retiro>();
	}
	
	public void agregar(Retiro r) {
		retiros.add(r);
	}
	
	public Retiro obtener(int r) {
		return retiros.get(r);
	}
	
	public int longitud() {
		return retiros.size();
	}
	
	public void eliminar(int i) {
		retiros.remove(i);
	}
	
}
