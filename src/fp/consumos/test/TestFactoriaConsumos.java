package fp.consumos.test;


import java.util.List;

import fp.consumos.Consumo;
import fp.consumos.FactoriaConsumos;

public class TestFactoriaConsumos {
	
	public static void main(String[] args) {
		List<Consumo> consumos = FactoriaConsumos.leeConsumos("./data/consumo_energia_edificios.csv");

		System.out.println("Se han leido " + consumos.size() + " consumos");
		for (Consumo c : consumos) {
			System.out.println(c);
		}
	}

}
