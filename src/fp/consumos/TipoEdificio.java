package fp.consumos;

import java.util.Arrays;
import java.util.Optional;

public enum TipoEdificio {
	CENTROS_CULTURALES_BIBLIOS ("Centros culturales y bibliotecas"), 
	CENTROS_MIXTOS("Centros mixtos"),
	CENTROS_DEPORTIVOS("Centros deportivos"),
	CENTROS_ESCOLARES("Centros escolares"),
	CENTROS_ADMINISTRATIVOS("Centros administrativos");
	
	private String value;
	
	TipoEdificio(String value){
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	
	public static TipoEdificio parsearTipoEdificio(String value) {
        return Arrays.stream(TipoEdificio.values())
            .filter(tipo -> tipo.getValue().equals(value))
            .findFirst()
            .orElse(null);
    }
}
