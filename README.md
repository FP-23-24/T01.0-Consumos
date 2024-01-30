# T01.0-Consumos


Disponemos de datos el consumo energético de los edificios públicos gestionados por el ayuntamiento de Madrid en un fichero csv. Cuatro líneas incluidas en el fichero son las siguientes:

```
"AÑO";"MES";"ID";"TIPOEDIFICIO";"EDIFICIO";"BARRIO";"CLASE";"GRUPO";"UNIDADES";"CONSUMO"
"2020";"3";"2096";"Centros culturales y bibliotecas";"Biblioteca Eugenio Trías";"JERÓNIMOS";"Energia activa";"Activa Interruptor Gral";"kWh";"14630"
"2020";"3";"2096";"Centros culturales y bibliotecas";"Biblioteca Eugenio Trías";"JERÓNIMOS";"Energia reactiva";"Reactiva tarifa 3.1A";"kVAr";"4173"
"2020";"3";"2096";"Centros culturales y bibliotecas";"Biblioteca Eugenio Trías";"JERÓNIMOS";"Gas";"Gas";"m3";"1266,7"
```
La información de cada línea se corresponde con lo siguiente:
•	año:  año en el que se hace la medición de consumo, de tipo int.
•	mes: mes en el que se produce la fecha de consumo, de tipo int.
•	id: identificador único del edificio para el que se da el consumo, de tipo str.
•	tipo_edificio: tipo de edificio, de tipo str.
•	edificio: nombre del edificio para el que se hace la medición, de tipo str.
•	barrio: barrio en el que está el edificio, de tipo str.
•	clase: clase de energía para la que se hace la medición, de tipo str.
•	grupo: grupo al que pertenece la enercía para la que se realiza la medición, de tipo str.
•	unidad: unidad de medida de la medición del consumo, de tipo str.
•	consumo: cantidad consumida, de tipo float. 

Se han implementado en Java una serie de tipos (clases, enumerados y `records`) para explotar estos datos. 

El objetivo de este proyecto es aprender a crear objetos e invocar a los métodos disponibles en esos objetos, aunque no entiendan el código situado tras el proyecto. Para ello, en el paquete `fp.consumos.test` cree una clase llamada `TestEstadisticasConsumo` y en su método `main` escriba el código para responder a las siguientes preguntas. Note que antes de responder a las preguntas debe crear una lista de objetos tipo `Consumo` con los datos del archivo `consumo_energia_edificios_csv`. Fíjese en linea 12 de la clase `TestFactoriaConsumos` para hacerlo. Una vez obtenida esta lista, cree un objeto de tipo `EstadisticasConsumo`. Use este objeto para responder a las preguntas-

1. ¿Cuáles son los tres barrios con más consumo de GAS en 2020? ¿Y los tres barrios que tienen más consumo de ENERGIA ACTIVA en ese año? ¿Y ls que tienen más consumo en ENERGIA REACTIVA?
2. ¿Cuál es la media de conuso de todos los edificios de clase ENERGIA ACTIVA? ¿y de GAS? ¿Y de ENERGIA REACTIVA?
3. ¿Cuál es la media en el año 2020 de consumo de clase GAS por cada tipo de edificio? ¿Y de clase ENERGIA ACTIVA? ¿Y de clase ENERGIA REACTIVA?
4. ¿Cuál es el incremento de consumo anual por unidad de kWh? ¿y por m3? ¿Y por KVar?


Cree una clase `TestConsumo` y en su método `main`:

1) Cree un objeto de tipo `Consumo` con las siguientes propiedades

	* Fecha del consumo: 1/12/2023
	* Identificador: 20
	• Tipo de edificio: Centros culturales y bibliotecas.
	• Edificio: Biblioteca Pío Baroja.
	• Barrio: ACACIAS
	• Clase de energía: Gas.
	• Grupo: Gas.
	• Unidad: m3
	• Consumo: 807,6

2) Cree un objeto de tipo `Consumo` con las siguientes propiedades

	* Fecha del consumo: 1/11/2023
	* Identificador: 20
	• Tipo de edificio: Centros culturales y bibliotecas.
	• Edificio: Biblioteca Pío Baroja.
	• Barrio: ACACIAS
	• Clase de energía: Gas.
	• Grupo: Gas.
	• Unidad: m3
	• Consumo: 800,6


3) Dados esos dos objetos, muestre el mes en el que ha habido un mayor consumo.