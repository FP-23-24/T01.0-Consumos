package fp.consumos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;

public class EstadisticasConsumo {

	private List<Consumo> consumos;
	private static final Predicate<Consumo> PRED_CONSUMO_NO_NULL = 	consumo-> consumo.consumo()!= null;
	public EstadisticasConsumo(List<Consumo> consumos) {
		this.consumos = consumos; 
		
	}

	public List<Consumo> getConsumos() {
		return new ArrayList<Consumo> (consumos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(consumos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticasConsumo other = (EstadisticasConsumo) obj;
		return Objects.equals(consumos, other.consumos);
	}

	@Override
	public String toString() {
		String s= consumos.stream()
					.map(Consumo::toString)
					.collect(Collectors.joining(",\n"));
		return "EstadisticasConsumo [consumos=" + consumos + "]";
	}

	
	public List<Map.Entry<String, Double>> getBarriosTopConsumo(Integer anyo, String clase, Integer n){
		Map<String, Double> m = mediaPorBarrio(anyo, clase);
		Function<Map.Entry<String,Double>, Double> f = e->e.getValue();
		Comparator<Map.Entry<String, Double>> c1 = Comparator.comparing(f);
		
		return m.entrySet().stream()
				           .sorted(c1.reversed())
						   .limit(n)
						   .collect(Collectors.toList());
	}

	private Map<String, Double> mediaPorBarrio(Integer anyo, String clase) {
		Predicate<Consumo> predCons = consumo-> consumo.fecha().getYear() == anyo;
		Predicate<Consumo> predClas = consumo-> consumo.clase().toUpperCase().equals(clase.toUpperCase());
	
		return consumos.stream()
				.filter(predCons.and(predClas).and(PRED_CONSUMO_NO_NULL))
				.collect(Collectors.groupingBy(Consumo::barrio,
										Collectors.summingDouble(c->c.consumo()/12)));
	}
	
	public Double getMediaConsumoEdificios(String clase) {
		return calculaMediaConsumoEdificios(consumos, clase);
	}
	
	private static Double calculaMediaConsumoEdificios(List<Consumo> lista_consumos, String clase) {
		Predicate<Consumo> predClas = consumo-> consumo.clase().toUpperCase().equals(clase.toUpperCase());
		Double total_consumo = lista_consumos.stream()
								.filter(PRED_CONSUMO_NO_NULL.and(predClas))
								.mapToDouble(Consumo::consumo)
								.sum();
		Long total_edificios = lista_consumos.stream()
										.map(Consumo::id)
										.distinct()
										.count();
		Double res = null;
		if (total_edificios>0) {
			res = total_consumo/total_edificios;
		}
		return res;
	}
	public Map<TipoEdificio, Double>  getMediaConsumosDeEdificioPorTipo(Integer anyo, String clase){
		Map<TipoEdificio, List<Consumo>> m = agruparPorTipoEdificio(anyo, clase);
		return m.entrySet().stream()
					.collect(Collectors.toMap(e->e.getKey(), 
											  e->calculaMediaConsumoEdificios(e.getValue(), clase)));
	}

	private Map<TipoEdificio, List<Consumo>> agruparPorTipoEdificio(Integer anyo, String clase) {
		Predicate<Consumo> predCons = consumo-> consumo.fecha().getYear() == anyo;
		Predicate<Consumo> predClas = consumo-> consumo.clase().toUpperCase().equals(clase.toUpperCase());
		return consumos.stream()
				.filter(predCons.and(predClas).and(PRED_CONSUMO_NO_NULL))
				.collect(Collectors.groupingBy(Consumo::tipoEdificio));
	}
	
	public SortedMap<String, Double> incrementoAnualDeConsumoPorUnidad(String unidad){
		SortedMap<Integer, Double> m = consumoDeUnidadPorAnyo(unidad);
		
		List<Integer> ls = new ArrayList<>();
		ls.add(null);
		List<Pair<Integer>> pares_años = m.keySet().stream()
					.map(anyo->new Pair<Integer>(ls.get(0), anyo))	
					.peek(pair-> ls.set(0,pair.second()))
					.filter(pair-> pair.first()!=null)
					.collect(Collectors.toList());
		
// Versi�n con range:		
//		List<Integer> claves = new ArrayList<Integer>(m.keySet());
//		List<Pair<Integer>> pares_a�os = IntStream.range(0, claves.size()-1)
//				    					.mapToObj(i-> new Pair(claves.get(i), claves.get(i+1)))
//				    					.collect(Collectors.toList());
//		
		return pares_años.stream()
					.collect(Collectors.toMap(p ->p.second()+"-"+ p.first(), 
											  p-> m.get(p.second())-m.get(p.first()),
											  (p1,p2) -> p1,
											  TreeMap::new));
	}
	
	public SortedMap<String, Double> incrementoAnualDeConsumoPorUnidad2(String unidad){
		SortedMap<Integer, Double> m = consumoDeUnidadPorAnyo(unidad);
		  System.out.println(m);	
		Integer previous = null;
		Integer current = null;
		Boolean esPrimero= true;
		SortedMap<String, Double> res = new TreeMap<>();
		for (Integer year: m.keySet()) {
			if (esPrimero) {
				current= year;
				esPrimero = false;
			}else {
				previous = current;
				current = year;
				Double inc = m.get(current)-m.get(previous);
				res.put(current+"-"+previous, inc);
			}
			
		}
	 
	   return res;
	}
	
	
	private SortedMap<Integer, Double> consumoDeUnidadPorAnyo(String unidad) {
		Predicate<Consumo> predUnidad = consumo->consumo.unidades().equals(unidad);
		return consumos.stream()
					.filter(predUnidad.and(PRED_CONSUMO_NO_NULL))
					.collect(Collectors.groupingBy(consumo->consumo.fecha().getYear(),
													TreeMap::new,
												    Collectors.summingDouble(Consumo::consumo)));
	}

}
