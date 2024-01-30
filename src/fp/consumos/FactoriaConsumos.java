package fp.consumos;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;
import java.util.stream.Collectors;

public class FactoriaConsumos {

	private static Consumo parsearConsumo(String lineaCSV) {
		Checkers.checkNoNull(lineaCSV);
		String[] trozos = lineaCSV.split(";");
		String msg = String.format("Formato Video no válido <%s>", lineaCSV);
		Checkers.check(msg, trozos.length == 10);

		LocalDate fecha = parseaFecha(removeQuotesSpaces(trozos[0]), removeQuotesSpaces(trozos[1]));
		String id = removeQuotesSpaces(trozos[2]);
		TipoEdificio tipoEdificio = TipoEdificio.parsearTipoEdificio(removeQuotesSpaces(trozos[3]));
		String edificio = removeQuotesSpaces(trozos[4]);
		String barrio = removeQuotesSpaces(trozos[5]);
		String clase = removeQuotesSpaces(trozos[6]);
		String grupo = removeQuotesSpaces(trozos[7]);
		String unidades = removeQuotesSpaces(trozos[8]);
		Double consumo = parsearDouble(removeQuotesSpaces(trozos[9]));
		return new Consumo(fecha, id, tipoEdificio, edificio, barrio, clase, grupo, unidades, consumo);
	}

	private static String removeQuotesSpaces(String cadena) {
		return cadena.trim().replace("\"", "");
	}
	private static Double parsearDouble(String cadena) {
		Double res = null;
		if (!cadena.equals("") && !cadena.equals("NO DATA")) {
			res = Double.parseDouble(cadena.replace(',', '.'));
		}
		return res;
	}

	private static LocalDate parseaFecha(String anyo, String mes) {
		Integer a = Integer.parseInt(anyo);
		Integer m = Integer.parseInt(mes);
		return LocalDate.of(a, m, 1);
	}

	public static List<Consumo> leeConsumos(String fichero) {
		Checkers.checkNoNull(fichero);
		String errMsg = String.format("Error leyendo fichero <%s>", fichero);
		List<String> lineas = Ficheros.leeFichero(errMsg, fichero, StandardCharsets.UTF_8);

		List<Consumo> res = lineas.stream()
				                  .skip(1)
				                  .map(linea -> parsearConsumo(linea))
				                  .collect(Collectors.toList());
		return res;
	}

}
