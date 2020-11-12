import java.util.List;
import java.util.ArrayList;

class Main {

	public static void main(String[] args) {

		String fileName = "Covid19Casos.csv";// Nombre del archivo

		// GlobalStats gs = FileUtil.readCases(fileName);
		// gs.ShowStats();

		// HashTableOpen<Integer, String> tableHash = new HashTableOpen<Integer,
		// String>(10);

		List<TestSubject> arr = FileUtil.readCasesList(fileName);
		List<String> provincias = new ArrayList<>();
		List<Integer> idProvincias = new ArrayList<>();

		int aux = 0;
		for (int i = 0; i < arr.size(); i++) {
			boolean agregar = true;
			for (int j = 0; j < idProvincias.size(); j++) {
				if(arr.get(i).getCargaProvinciaId() == idProvincias.get(j))
					agregar = false;
			}
			if(agregar) {
				provincias.add(arr.get(i).getCargaProvincia());
				idProvincias.add(arr.get(i).getCargaProvinciaId());
				aux++;
			}
		}
		
		for (int i = 0; i < provincias.size(); i++) {
			System.out.println(provincias.get(i) + ": " + idProvincias.get(i));
		}


	}
}

		/*
		int size = 29;
		String[] arr = { "Salta", "Jujuy", "Formosa", "Catamarca", "Chaco", "Tucumán", "Córdoba", "Buenos Aires",
				"CABA", "Mendoza", "San Juan", "San Luis", "Corrientes", "La Rioja", "La Pampa", "Santa Cruz", "Chubut",
				"Tierra del Fuego", "Santiago del Estero", "Neuquén", "Río Negro", "Misiones", "Santa Fe",
				"Entre Ríos" };
		int[] hashes = new int[arr.length];


		for (int i = 0; i < arr.length; i++) {
			hashes[i] = Math.abs(arr[i].hashCode() % size);
			System.out.println(arr[i] + ": " + hashes[i]);
		}

		System.out.println();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < hashes.length && i != j; j++) {
				if (hashes[i] == hashes[j])
					System.out.println(arr[i] + " colisiono con " + arr[j]);
			}
		}

	}
}

*/