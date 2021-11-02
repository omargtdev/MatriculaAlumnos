package libreria;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Util {
	
	public static void mensaje(JDialog context, String text, String title, String type) {
		switch (type) {
		case "error":
			JOptionPane.showMessageDialog(context, text, title, JOptionPane.ERROR_MESSAGE);
			break;
		case "warning":
			JOptionPane.showMessageDialog(context, text, title, JOptionPane.WARNING_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(context, text, title, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public static int obtenerCodigo(JTable table, DefaultTableModel model) {
		int select = table.getSelectedRow();
		//Verificar que solo se seleccione un objeto
		if(select != -1 && table.getSelectedRowCount() == 1) {
			//Obtener el valor de la celda seleccionada y la primera columna
			return (int) model.getValueAt(table.getSelectedRow(), 0);
		}
		return -1;
	}
	
	public static void habilitarElemento(JComboBox<Object> c) {
		c.setEnabled(true);
	}
	
	public static void habilitarElemento(JButton b) {
		b.setEnabled(true);
	}

}
