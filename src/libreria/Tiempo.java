package libreria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tiempo {
	
	public static String fecha() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now());
	}
	
	public static String formatearFecha(String fecha) {
		String dia, mes, year;
		String [] f = fecha.split("/"); 
		dia = f[0];
		mes = f[1];
		year = f[2];
		switch (mes) {
			case "01": mes = "Enero"; break;
			case "02": mes = "Febrero"; break;
			case "03": mes = "Marzo"; break;
			case "04": mes = "Abril"; break;
			case "05": mes = "Mayo"; break;
			case "06": mes = "Junio"; break;
			case "07": mes = "Julio"; break;
			case "08": mes = "Agosto"; break;
			case "09": mes = "Septiembre"; break;
			case "10": mes = "Octubre"; break;
			case "11": mes = "Noviembre"; break;
			case "12": mes = "Diciembre"; break;
		}
		return dia + " de " + mes + " de " + year;
	}
	
	public static String hora() {
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
	}
	
	public static String formatearHora(String hora) {
		String h[] = hora.split(":");
		int horas = Integer.parseInt(h[0]);
		String minSeg = h[1] + ":" + h[2];
		if(horas == 0) {
			return "12:" + minSeg + " AM";
		}else if(horas > 0 && horas < 12) {
			if(horas < 10) {
				return "0" + horas + ":" + minSeg + " AM";
			}else {
				return horas + ":" + minSeg + " AM";
			}
		}else if(horas == 12) {
			return horas + ":" + minSeg + " PM";
		}else {
			horas = horas - 12;
			if(horas < 10) {
				return "0" + horas + ":" + minSeg + " PM";
			}else {
				return horas + ":" + minSeg + " PM";
			}
		}
	}
}
