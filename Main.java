import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

class Main {

	public static void main(String[] args) {

		FileUtil file = new FileUtil();
		String fileName = "Covid19Casos-10.csv";// Nombre del archivo


		GlobalStats gs;

		boolean hayEstad = Arrays.stream(args).anyMatch(arg -> arg.equals("-estad"));
		// boolean estadistica = Arrays.asList(args).contains("-estad");

		//if (hayEstad) {

			if(args.length < 2){
				file.readCases(fileName,0,true,false);
				gs=file.getStats();
				System.out.println("\n\n\nESTADISTICAS:\n");
				gs.ShowStats();
			}
				Arrays.stream(args).filter(arg -> !arg.equalsIgnoreCase("-estad"));


			//FileUtil.readCases(fileName).ShowStats();
		//}

		// TODO: Si pinta arreglar la sig. situacion // covid19.jar -p_casos -estad 5
		// TODO: Me parece, que es ineficiente tener un metodo solo para el -estad y otro para otra plabra clave. Si piden las dos cosas, va a tener que leer el archivo dos veces.
		// TODO: Para solucionar lo de arriba, podríamos hacer en la lectura del archivo banderas para ir activando cuando corresponda.
		// covid19.jar -p_casos 5 6

		if (args.length == 0) {
			System.out.println("Sin argumentos");
			return;
		} else if (args.length < 3) {
			System.out.println("Exceso de argumentos");
			return;
		}

		switch (args[0]) {
			case "-p_casos":
				if (args.length > 1) { // Parametro enviado
					try {
						int n = Integer.parseInt(args[1]);
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}
				} else {

				} // Sin parametro
				break;
			case "-p_muertes":
				if (args.length > 1) { // Parametro enviado
					try {
						int n = Integer.parseInt(args[1]);
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}
				} else {

				} // Sin parametro
				break;
			case "-casos_edad":
				if (args.length > 1) { // Parametro enviado
					try {
						int n = Integer.parseInt(args[1]);
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}
				} else {

				} // Sin parametro
				break;
			case "-casos_cui":
				if (args.length > 1) { // Parametro enviado
					try {
						SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
						Date fecha = formatDate.parse(args[1]);
						
					} catch (ParseException ex) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}
				} else {

				} // Sin parametro
				break;
		}

	}
}
// HashTableOpen<Integer, String> tableHash = new HashTableOpen<Integer,
// String>(10);
// Map<String, Integer> provMuertes = new Map<String, Integer>();

/*
 * List<String> provincias = Arrays.asList("Buenos Aires", "Córdoba", "Mendoza",
 * "Formosa", "CABA", "Santa Fe", "San Luis", "Entre Ríos", "Tucumán", "Chaco",
 * "Corrientes", "Río Negro", "Santa Cruz", "Neuquén", "San Juan", "La Pampa",
 * "Chubut", "Jujuy", "Santiago del Estero", "Catamarca", "Tierra del Fuego",
 * "Misiones", "La Rioja", "Salta");
 * 
 * int size = 127; int[] hashes = new int[provincias.size()];
 * 
 * //HASH cuadratico
 * 
 * for (int i = 0; i < provincias.size(); i++) { hashes[i] =
 * (idProvincias.get(i) + i * i) % size; System.out.println(provincias.get(i) +
 * " (" + idProvincias.get(i) + ") : " + hashes[i]); }
 * 
 * System.out.println();
 * 
 * // Mostrar colisiones for (int i = 0; i < provincias.size(); i++) { for (int
 * j = 0; j < hashes.length && i != j; j++) { if (hashes[i] == hashes[j])
 * System.out.println(provincias.get(i) + " colisiono con " +
 * provincias.get(j)); } }
 */

/*
 * ProvinciaCatga : idProvinciaCarga
 * 
 * Buenos Aires: 6 Córdoba: 14 Mendoza: 50 Formosa: 34 CABA: 2 Santa Fe: 82 San
 * Luis: 74 Entre Ríos: 30 Tucumán: 90 Chaco: 22 Corrientes: 18 Río Negro: 62
 * Santa Cruz: 78 Neuquén: 58 San Juan: 70 La Pampa: 42 Chubut: 26 Jujuy: 38
 * Santiago del Estero: 86 Catamarca: 10 Tierra del Fuego: 94 Misiones: 54 La
 * Rioja: 46 Salta: 66
 * 
 */

// HASH Lineal
/*
 * for (int i = 0; i < provincias.size(); i++) { hashes[i] =
 * ((idProvincias.get(i) + i)) % size; System.out.println(provincias.get(i) +
 * " (" + idProvincias.get(i) + ") : " + hashes[i]); }
 */

// HASH cuadratico doble
/*
 * for (int i = 0; i < provincias.size(); i++) { hashes[i] =
 * (((idProvincias.get(i) + i * i) % size) + i * i) % size;
 * System.out.println(provincias.get(i) + " (" + idProvincias.get(i) + ") : " +
 * hashes[i]); }
 */
