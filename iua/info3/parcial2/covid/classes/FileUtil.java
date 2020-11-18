package iua.info3.parcial2.covid.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;

import iua.info3.parcial2.covid.structures.HashTableOpen;

/* ToDo: Asegurar casos nulos */

public class FileUtil {

    private GlobalStats stats = new GlobalStats();

    // ****Lectura de -casos_edad****//
    public TreeMap<String, List<TestSubject>> readCasesByAge(String fileName, int edad, boolean hayEstad) {
        TreeMap<String, List<TestSubject>> casesByAge = new TreeMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                if (hayEstad)
                    loadStats(values);

                // Condiciones basicas
                if (!values[20].equalsIgnoreCase("Confirmado")
                        || (values[2].equals("") ? -1 : Integer.parseInt(values[2])) != edad)
                    continue;

                // Carga de TestSubjects
                TestSubject t = loadTestSubject(values);

                if (casesByAge.containsKey(t.getCargaProvincia())) {
                    casesByAge.get(t.getCargaProvincia()).add(t);
                } else {
                    List<TestSubject> ts = new ArrayList<>();
                    ts.add(t);

                    casesByAge.put(t.getCargaProvincia(), ts);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el documento");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return casesByAge;
    }

    // ****Lectura de -p_casos****//
    public TreeSet<List<TestSubject>> readCasesByProvince(String fileName, boolean hayEstad) {
        List<Integer> listIdProvince = Arrays.asList(6, 14, 50, 34, 2, 82, 74, 30, 90, 22, 18, 62, 78, 58, 70, 42, 26,
                38, 86, 10, 94, 54, 46, 66);

        TreeSet<List<TestSubject>> ts = new TreeSet<List<TestSubject>>(new Comparator<List<TestSubject>>() {
            @Override
            public int compare(List<TestSubject> arg0, List<TestSubject> arg1) {
                return arg0.size() - arg1.size();
            }
        });

        HashTableOpen<Integer, List<TestSubject>> ht = new HashTableOpen<>(27);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                if (hayEstad)
                    loadStats(values);

                // Condiciones basicas
                if (!values[20].equalsIgnoreCase("Confirmado"))
                    continue;

                // Carga de TestSubjects
                TestSubject t = loadTestSubject(values);

                try {
                    ht.get(t.getCargaProvinciaId()).add(t);
                } catch (Exception e) {
                    List<TestSubject> lt = new ArrayList<>();
                    lt.add(t);
                    ht.insert(t.getCargaProvinciaId(), lt);
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el documento");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Integer idProvince : listIdProvince) {
            try {
                List<TestSubject> lt1;
                lt1 = ht.get(idProvince);
                ts.add(lt1);
            } catch (Exception e) {
                System.out.println("No hay casos confirmados en la provincia de id : " + idProvince);
            }
        }

        return ts;

    }

    // ****Lectura de -p_muertes****//
    public TreeSet<List<TestSubject>> readDeacesedByProvince(String fileName, boolean hayEstad) {
        List<Integer> listIdProvince = Arrays.asList(6, 14, 50, 34, 2, 82, 74, 30, 90, 22, 18, 62, 78, 58, 70, 42, 26,
                38, 86, 10, 94, 54, 46, 66);

        TreeSet<List<TestSubject>> ts = new TreeSet<List<TestSubject>>(new Comparator<List<TestSubject>>() {
            @Override
            public int compare(List<TestSubject> arg0, List<TestSubject> arg1) {
                return arg0.size() - arg1.size();
            }
        });

        HashTableOpen<Integer, List<TestSubject>> ht = new HashTableOpen<>(27);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                if (hayEstad)
                    loadStats(values);

                // Condiciones basicas
                if (!values[14].equalsIgnoreCase("SI"))
                    continue;

                // Carga de TestSubjects
                TestSubject t = loadTestSubject(values);

                try {
                    ht.get(t.getCargaProvinciaId()).add(t);
                } catch (Exception e) {
                    List<TestSubject> lt = new ArrayList<>();
                    lt.add(t);
                    ht.insert(t.getCargaProvinciaId(), lt);
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el documento");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Integer idProvince : listIdProvince) {
            try {
                List<TestSubject> lt1;
                lt1 = ht.get(idProvince);
                ts.add(lt1);
            } catch (Exception e) {
                System.out.println("No hay casos confirmados en la provincia de id : " + idProvince);
                // e.printStackTrace();
            }
        }

        return ts;

    }

    // ****Lectura de -casos_cui****//
    public TreeMap<Date, List<TestSubject>> readCasesCui(String fileName, Date fecha, boolean hayEstad) {
        TreeMap<Date, List<TestSubject>> casesCui = new TreeMap<>();
        Predicate<Date> isFechaBien;
        if (fecha != null)
            isFechaBien = x -> x.compareTo(fecha) >= 0;
        else
            isFechaBien = x -> true;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                if (hayEstad)
                    loadStats(values);

                // Condiciones basicas
                if (!values[12].equalsIgnoreCase("SI") || values[13].equals("")
                        || !isFechaBien.test(formatDate.parse(values[13])))
                    continue;

                // Carga de TestSubjects
                TestSubject t = loadTestSubject(values);

                if (casesCui.containsKey(t.getFechaCuidadoIntensivo())) {
                    casesCui.get(t.getFechaCuidadoIntensivo()).add(t);
                } else {
                    List<TestSubject> ts = new ArrayList<>();
                    ts.add(t);

                    casesCui.put(t.getFechaCuidadoIntensivo(), ts);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el documento");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return casesCui;
    }

    // ****Lectura de solo estadísticas****//
    public GlobalStats readStats(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++)
                    if (values[i].length() > 1)
                        values[i] = values[i].substring(1, values[i].length() - 1);

                loadStats(values);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontró el documento");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stats;
    }

    // ****Carga de TestSubjects****//
    private static TestSubject loadTestSubject(String[] values) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

        TestSubject t = new TestSubject();
        t.setIdEventoCaso(Integer.parseInt(values[0]));
        t.setSexo(values[1].charAt(0));
        t.setEdad(values[2].equals("") ? -1 : Integer.parseInt(values[2]));
        t.setEdadTipo(values[3].equalsIgnoreCase("Meses"));
        t.setResidenciaPais(values[4]);
        t.setResidenciaProvincia(values[5]);
        t.setResidenciaDepartamento(values[6]);
        t.setCargaProvincia(values[7]);

        try {
            t.setFechaInicioSintomas(formatDate.parse(values[8]));
        } catch (ParseException ex) {
            // System.out.println("Invalid Dates");
        }

        try {
            t.setFechaApertura(formatDate.parse(values[9]));
        } catch (ParseException ex) {
            // System.out.println("Invalid Dates");
        }

        try {
            t.setFechaInternacion(formatDate.parse(values[11]));
            t.setFechaCuidadoIntensivo(formatDate.parse(values[13]));
        } catch (ParseException ex) {
            // System.out.println("Invalid Dates");
        }

        try {
            t.setFechaFallecimiento(formatDate.parse(values[15]));
        } catch (ParseException ex) {
            // System.out.println("Invalid Dates");
        }

        try {
            t.setFechaDiagnostico(formatDate.parse(values[22]));
        } catch (ParseException ex) {
            // System.out.println("Invalid Dates");
        }

        try {
            t.setUltimaActualizacion(formatDate.parse(values[24]));
        } catch (ParseException ex) {
            // System.out.println("Invalid Dates");
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

    // ****Carga de estadísticas****//
    private void loadStats(String[] values) {
        if (values[2].equals("") || Integer.parseInt(values[2]) > 129)
            return;

        if ((values[14]).equalsIgnoreCase("SI")) {
            stats.increaseDeceased();
            if ((values[3].equalsIgnoreCase("Años"))) {
                if (!values[2].equals(""))
                    stats.increasedeceasedAge((Integer.parseInt(values[2]) - 1) / 10);

            } else
                stats.increasedeceasedAge(0);
        }

        if ((values[20]).equalsIgnoreCase("Confirmado")) {
            stats.increaseInfected();
            if ((values[3].equalsIgnoreCase("Años"))) {
                if (!values[2].equals(""))
                    stats.increaseinfectedAge((Integer.parseInt(values[2]) - 1) / 10);
            } else
                stats.increaseinfectedAge(0);
        }
        stats.increaseSamples();
    }

    // ****Retorno de estadísticas****//
    public GlobalStats getStats() {
        return stats;
    }
}
