package libreria;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Mensaje {
	
	public static int ERROR = 0;
	public static int ADVERTENCIA = 1;
	public static int INFO = 2;
	
	public static void msg(JDialog contexto, String texto, String titulo, int type) {
		switch (type) {
		case 0:
			JOptionPane.showMessageDialog(contexto, texto, titulo, JOptionPane.ERROR_MESSAGE);
			break;
		case 1:
			JOptionPane.showMessageDialog(contexto, texto, titulo, JOptionPane.WARNING_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(contexto, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
			break;
		default:
			JOptionPane.showMessageDialog(contexto, texto, titulo, JOptionPane.INFORMATION_MESSAGE);
			break;
		}
	}
	
	public static String input(JDialog contexto, String texto, String titulo, String valorInicial) {
		return (String) JOptionPane.showInputDialog(contexto, texto, titulo, -1, null, null, valorInicial);
	}

	public static int confirm(JDialog contexto, String texto, String titulo) {
		return (int) JOptionPane.showConfirmDialog( contexto, texto, titulo, JOptionPane.YES_NO_OPTION);
	}
}
