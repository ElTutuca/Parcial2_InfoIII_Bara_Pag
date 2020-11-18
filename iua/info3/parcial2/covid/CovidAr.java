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
		String fileName = "";

		if (args.length == 0) {
			System.out.println("Sin argumentos");
			return;
		} else if (args.length > 4) {
			System.out.println("Exceso de argumentos");
			return;
		}

		// Chequeo de directorio
		boolean hayDir = args[args.length - 1].contains(".csv");
		if (hayDir) {
			fileName = args[args.length - 1];// Nombre del archivo
			args = Arrays.stream(args).filter(arg -> !arg.contains(".csv")).toArray(String[]::new);
		} else {
			System.out.println("No se ingresó el documento de lectura");
			return;
		}

		// Chequeo de -estad
		boolean hayEstad = Arrays.stream(args).anyMatch(arg -> arg.equals("-estad"));
		if (hayEstad && args.length > 1)
			args = Arrays.stream(args).filter(arg -> !arg.equalsIgnoreCase("-estad")).toArray(String[]::new);

		// Selector del siguiente argumento
		switch (args[0]) {
			case "-p_casos":
				// Lectura de contagios por provincia
				TreeSet<List<TestSubject>> ts = file.readCasesByProvince(fileName, hayEstad);

				if (hayEstad) {
					System.out.println("ESTADISTICO");
					file.getStats().ShowStats();
				}

				// Parametro enviado
				if (args.length > 1) {
					try {
						int n = Integer.parseInt(args[1]);
						for (List<TestSubject> e : ts.descendingSet()) {
							if (n != 0) {
								n--;
								System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " casos.");
								e.forEach(t -> System.out.println(t));
								System.out.println();
							}
						}
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}
				} else { // Sin parametro
					for (List<TestSubject> e : ts.descendingSet()) {
						System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " casos.");
						e.forEach(t -> System.out.println(t));
						System.out.println();
					}
				}
				break;

			case "-p_muertes":
				// Lectura de muertes por provincia
				TreeSet<List<TestSubject>> tsd = file.readDeacesedByProvince(fileName, hayEstad);

				if (hayEstad) {
					System.out.println("ESTADISTICO");
					file.getStats().ShowStats();
				}

				// Parametro enviado
				if (args.length > 1) {
					try {
						int n = Integer.parseInt(args[1]);
						for (List<TestSubject> e : tsd.descendingSet()) {
							if (n != 0) {
								n--;
								System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " fallecidos.");
								e.forEach(t -> System.out.println(t));
								System.out.println();
							}
						}
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}
				} else { // Sin parametro
					for (List<TestSubject> e : tsd.descendingSet()) {
						System.out.println(e.get(0).getCargaProvincia() + " con " + e.size() + " fallecidos.");
						e.forEach(t -> System.out.println(t));
						System.out.println();
					}
				}
				break;

			case "-casos_edad":
				// Parametro enviado
				if (args.length > 1) {
					try {
						int n = Integer.parseInt(args[1]);
						// Lectura de casos contagiados con la edad especificada
						TreeMap<String, List<TestSubject>> tm = file.readCasesByAge(fileName, n, hayEstad);

						if (hayEstad) {
							System.out.println("ESTADISTICO");
							file.getStats().ShowStats();
						}

						boolean found = false;
						for (Map.Entry<String, List<TestSubject>> e : tm.entrySet()) {
							found = true;
							System.out.println(e.getKey());

							e.getValue().forEach(t -> System.out.println(t));
							System.out.println();
						}
						if (!found)
							System.out.println("No se encontro casos con la edad \"" + n + "\"");
					} catch (Exception e) {
						System.out.println("Argumento \"" + args[1] + "\" no valido.");
					}

				} else { // Sin parametro
					System.out.println("Debe pasar una edad.");
				}
				break;

			case "-casos_cui":
				try {
					SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
					Date fecha = null;
					if (args.length > 1)
						fecha = formatDate.parse(args[1]);

					// Lectura de casos en cuidados intensivos por fecha
					TreeMap<Date, List<TestSubject>> tm = file.readCasesCui(fileName, fecha, hayEstad);

					if (hayEstad) {
						System.out.println("ESTADISTICO");
						file.getStats().ShowStats();
					}

					boolean found = false;
					for (Map.Entry<Date, List<TestSubject>> e : tm.entrySet()) {
						found = true;
						System.out.println(formatDate.format(e.getKey()));

						e.getValue().forEach(t -> System.out.println(t));
						System.out.println();
					}
					if (!found)
						System.out.println("No se encontro casos con la fecha \"" + fecha + "\"");
				} catch (ParseException ex) {
					System.out.println("Argumento \"" + args[1] + "\" no valido.");
				}
				break;

			// Solo se pide -estad
			default:
				if (hayEstad)
					file.readStats(fileName).ShowStats();
				else
					System.out.println("Parametros no reconocidos");
		}

		long tf = System.nanoTime();
		System.out.println();
		System.out.println("Se tardó: " + (tf - ti) / 1e9);
	}
}
