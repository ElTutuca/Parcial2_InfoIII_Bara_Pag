package iua.info3.parcial2.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import iua.info3.parcial2.covid.classes.FileUtil;
import iua.info3.parcial2.covid.classes.TestSubject;

class CovidAr {

	public static void main(String[] args) {

		long ti = System.nanoTime();
		FileUtil file = new FileUtil();
		String fileName = "";// Nombre del archivo

		if (args.length == 0) {
			System.out.println("Sin argumentos");
			return;
		} else if (args.length > 4) {
			System.out.println("Exceso de argumentos");
			return;
		}

		boolean hayDir = args[args.length - 1].contains(".csv");
		if (hayDir) {
			fileName = args[args.length - 1];// Nombre del archivo
			args = Arrays.stream(args).filter(arg -> !arg.contains(".csv")).toArray(String[]::new);
		} else {
			System.out.println("No se ingresó el documento de lectura");
			return;
		}

		// *****Chequeo de -estad*****//

		boolean hayEstad = Arrays.stream(args).anyMatch(arg -> arg.equals("-estad"));
		if (hayEstad && args.length > 1)
			args = Arrays.stream(args).filter(arg -> !arg.equalsIgnoreCase("-estad")).toArray(String[]::new);

		// *****Chequeo de el resto de comandos*****//
		switch (args[0]) {
			case "-p_casos":
				TreeSet<List<TestSubject>> ts = file.readCasesByProvince(fileName, hayEstad);

				if (hayEstad) {
					System.out.println("ESTADISTICO");
					file.getStats().ShowStats();
				}

				if (args.length > 1) { // Parametro enviado
					try {
						int n = Integer.parseInt(args[1]);
						for (List<TestSubject> e : ts.descendingSet()) {
							if (n != 0) {
								n--;
								System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " casos:");
								// for(TestSubject t : e)
								// System.out.println("*"+t.toString());
							}
						}
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}

				} else {
					for (List<TestSubject> e : ts.descendingSet()) {
						System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " casos:");
						// for (TestSubject t : e)
						// System.out.println("*" + t.toString());
					}
				} // Sin parametro

				break;

			case "-p_muertes":
				TreeSet<List<TestSubject>> tsd = file.readDeacesedByProvince(fileName, hayEstad);

				if (hayEstad) {
					System.out.println("ESTADISTICO");
					file.getStats().ShowStats();
				}

				if (args.length > 1) { // Parametro enviado
					try {
						int n = Integer.parseInt(args[1]);
						for (List<TestSubject> e : tsd.descendingSet()) {
							if (n != 0) {
								n--;
								System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " fallecidos:");
								// for(TestSubject t : e)
								// System.out.println("*"+t.toString());
							}
						}

					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}

				} else {
					for (List<TestSubject> e : tsd.descendingSet()) {
						System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " fallecidos:");
						// for (TestSubject t : e)
						// System.out.println("*" + t.toString());
					}
				} // Sin parametro

				break;

			case "-casos_edad":
				if (args.length > 1) { // Parametro enviado
					try {
						int n = Integer.parseInt(args[1]);
						TreeMap<String, List<TestSubject>> tm = file.readCasesByAge(fileName, n, hayEstad);
						if (hayEstad) {
							System.out.println("ESTADISTICO");
							file.getStats().ShowStats();
						}
						boolean found = false;
						for (Map.Entry<String, List<TestSubject>> e : tm.entrySet()) {
							found = true;
							System.out.println(e.getKey());

							// ? Ver si imprimimos todo el caso o ni
							e.getValue().forEach(x -> System.out.println(
									x.getIdEventoCaso() + ", " + x.getCargaProvinciaId() + ", " + x.getEdad()));
							System.out.println();
						}
						if (!found)
							System.out.println("No se encontro casos con la edad \"" + n + "\"");
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}

				} else {
					System.out.println("Debe pasar una edad.");
				} // Sin parametro
				break;

			case "-casos_cui":
				try {
					SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
					Date fecha = null;
					if (args.length > 1)
						fecha = formatDate.parse(args[1]);

					TreeMap<Date, List<TestSubject>> tm = file.readCasesCui(fileName, fecha, hayEstad);

					if (hayEstad) {
						System.out.println("ESTADISTICO");
						file.getStats().ShowStats();
					}

					boolean found = false;
					for (Map.Entry<Date, List<TestSubject>> e : tm.entrySet()) {
						found = true;
						System.out.println(formatDate.format(e.getKey()));

						// ? Ver si imprimimos todo el caso o ni
						e.getValue().forEach(x -> System.out
								.println(formatDate.format(x.getFechaCuidadoIntensivo()) + ", " + x.getIdEventoCaso()));

						System.out.println();
					}
					if (!found)
						System.out.println("No se encontro casos con la fecha \"" + fecha + "\"");

				} catch (ParseException ex) {
					System.out.println("Argumento \"" + args[1] + "\" no valido.");
				}
				break;

			default:

				if (hayEstad) { // Solo se pide -estad
					file.readStats(fileName).ShowStats();
				} else {
					System.out.println("Parametros no reconocidos");
				}

		}
		long tf = System.nanoTime();
		System.out.println();
		System.out.println("Se tardó: " + (tf - ti) / 1e9);
	}
}
