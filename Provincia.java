import java.util.List;
import java.util.ArrayList;

public class Provincia implements Comparable<Provincia> {

    private int idProvincia;
    private String provincia;
    private List<TestSubject> tests = new ArrayList<>();

    public Provincia(int idProvincia, String provincia) {
        this.idProvincia = idProvincia;
        this.provincia = provincia;
    }

    public void insert(TestSubject ts) {
        tests.add(ts);
    }

    public int getCantTests() {
        return tests.size();
    }

    public String getProvincia() {
        return provincia;
    }

    public List<TestSubject> getTests() {
        return tests;
    }

    public int getIdProvincia() {
        return idProvincia;
    }

    @Override
    public int compareTo(Provincia prov){
        if(getTests().size() > prov.getTests().size())
            return 1;
        else if(getTests().size() < prov.getTests().size())
            return -1;
        else
            return 0;
    }
}