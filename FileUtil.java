import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class FileUtil {

    public static GlobalStats readCases(String fileName) {

        int deceasedCount = 0;
        int samplesCount = 0;
        int infectedCount = 0;
        int[] deceasedAge = new int[11];
        int[] infectedAge = new int[11];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                values[14] = values[14].substring(1, values[14].length() - 1);
                values[20] = values[20].substring(1, values[20].length() - 1);
                values[2] = values[2].substring(1, values[2].length() - 1);
                values[3] = values[3].substring(1, values[3].length() - 1);

                if ((values[14]).equalsIgnoreCase("SI")) {
                    deceasedCount++;
                    if ((values[3].equalsIgnoreCase("Años"))) {
                        deceasedAge[(Integer.parseInt(values[2]) - 1) / 10]++;

                    } else
                        deceasedAge[0]++;
                }

                if ((values[20]).equalsIgnoreCase("Confirmado")) {
                    infectedCount++;
                    if ((values[3].equalsIgnoreCase("Años"))) {
                        infectedAge[(Integer.parseInt(values[2]) - 1) / 10]++;

                    } else
                        infectedAge[0]++;
                }

                samplesCount++;

            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalStats stats = new GlobalStats(samplesCount, infectedCount, deceasedCount, infectedAge, deceasedAge);
        return stats;
    }

    public static <TypeKey extends Comparable> HashTableOpen<TypeKey, TestSubject> readCases(String fileName, int n,
            Function<TestSubject, TypeKey> getKey, Predicate<TestSubject> isAprobado) {

        HashTableOpen<TypeKey, TestSubject> table = new HashTableOpen<TypeKey, TestSubject>(n);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                // Carga de TestSubjects
                TestSubject t = loadTestSubject(values);

                if (isAprobado.test(t))
                    table.insert(getKey.apply(t), t);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static List<TestSubject> readCasesList(String fileName) {
        List<TestSubject> table = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                // Carga de TestSubjects
                TestSubject t = loadTestSubject(values);

                table.add(t);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    private static TestSubject loadTestSubject(String[] values) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        TestSubject t = new TestSubject();
        t.setIdEventoCaso(Integer.parseInt(values[0]));
        t.setSexo(values[1].charAt(0));
        t.setEdad(Integer.parseInt(values[2]));
        t.setEdadTipo(values[3].equalsIgnoreCase("Meses"));
        t.setResidenciaPais(values[4]);
        t.setResidenciaProvincia(values[5]);
        t.setResidenciaDepartamento(values[6]);
        t.setCargaProvincia(values[7]);

        try {
            t.setFechaInicioSintomas(formatDate.parse(values[8]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        try {
            t.setFechaApertura(formatDate.parse(values[9]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        try {
            t.setFechaInternacion(formatDate.parse(values[11]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        try {
            t.setFechaCuidadoIntensivo(formatDate.parse(values[13]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        try {
            t.setFechaFallecimiento(formatDate.parse(values[15]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        try {
            t.setFechaDiagnostico(formatDate.parse(values[22]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        try {
            t.setUltimaActualizacion(formatDate.parse(values[24]));
        } catch (ParseException ex) {
            System.out.println("Invalid Dates");
        }

        t.setSepiApertura(values[10]);
        t.setCuidadoIntensivo(values[12].equalsIgnoreCase("SI"));
        t.setFallecido(values[14].equalsIgnoreCase("SI"));
        t.setAsistenciaRespiratoriaMecanica(values[16].equalsIgnoreCase("SI"));
        t.setCargaProvinciaId(Integer.parseInt(values[17]));
        t.setOrigenFinanciamiento((values[18].equalsIgnoreCase("SI")));
        t.setClasificacion(values[19]);
        t.setClasificacionResumen(values[20]);
        t.setResidenciaProvinciaId(Integer.parseInt(values[21]));
        t.setResidenciaDepartamentoId(Integer.parseInt(values[23]));

        return t;
    }
}
