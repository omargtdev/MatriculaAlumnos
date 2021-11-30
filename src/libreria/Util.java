package libreria;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Util {
	
	public static int obtenerCodigo(JTable table, DefaultTableModel model) {
		int select = table.getSelectedRow();
		//Verificar que solo se seleccione un objeto
		if(select != -1 && table.getSelectedRowCount() == 1) {
			//Obtener el valor de la celda seleccionada y la primera columna
			return (int) model.getValueAt(table.getSelectedRow(), 0);
		}
		return -1;
	}
	
	public static String saltoLinea(String str) {
		return str + "\n";
	}
	
	public static String puntoComa(int i) {
		return i + ";";
	}
	
	public static String tituloTxtArea(String titulo) {
		return saltoLinea("-------------------------------------------------------------") +
			   saltoLinea("             " + titulo) +
			   saltoLinea("-------------------------------------------------------------");
	}

	public static String puntoComa(String i) {
		return i + ";";
	}
	
	public static String nombreEstado(int estado) {
		switch (estado) {
		case 0: return "Registrado";			
		case 1: return "Matriculado";
		default: return "Retirado";
		}
	}
	
	public static String numeroCiclo(int ciclo) {
		switch (ciclo) {
		case 0: return "Primero";
		case 1: return "Segundo";
		case 2: return "Tercero";
		case 3: return "Cuarto";
		case 4: return "Quinto";
		default: return "Sexto";
		}
	}
	
}
