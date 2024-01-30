package fp.consumos;

import java.time.LocalDate;

import fp.utiles.Checkers;

public record Consumo(LocalDate fecha, String id, TipoEdificio tipoEdificio, 
		          String edificio, String barrio, String clase, String grupo,
		          String unidades, Double consumo) {
	public Consumo{
		Checkers.checkNoNull(fecha, id, edificio, barrio, clase, grupo, unidades);
	}

}
