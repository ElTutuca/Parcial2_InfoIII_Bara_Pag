import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


class FileUtil {

    public static List<TestSubject> readCases(String fileName) {

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        
        List<TestSubject> subjectList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                // Carga de TestSubjects
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

                subjectList.add(t);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }
}