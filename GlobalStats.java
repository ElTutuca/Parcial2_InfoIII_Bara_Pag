import java.util.Arrays;

public class GlobalStats {

    private int samples = 0;
    private int infected = 0;
    private int deceased = 0;
    private int[] infectedAge = new int[11];
    private int[] deceasedAge = new int[11];

    GlobalStats() {
        Arrays.fill(infectedAge, 0);
        Arrays.fill(deceasedAge, 0);
    }

    GlobalStats(int samples, int infected, int deceased, int[] infectedAge, int[] deceasedAge) {
        this.deceased = deceased;
        this.infected = infected;
        this.samples = samples;
        this.infectedAge = infectedAge;
        this.deceasedAge = deceasedAge;

    } // ***************************Builder

    public int SampleInfectedPercent() {
        return (infected * 100) / samples;
    }

    public int InfectedDeceasedPercent() {
        return (deceased * 100) / infected;
    }

    public void increaseSamples() {
        samples++;
    }

    public void increaseInfected() {
        infected++;
    }

    public void increaseDeceased() {
        deceased++;
    }

    public void increaseinfectedAge(int i) {
        infectedAge[i]++;
    }

    public void increasedeceasedAge(int i) {
        deceasedAge[i]++;
    }

    public void ShowStats() {
        System.out.println("Cantidad de muestras: " + samples);
        System.out.println("Cantidad de infectados: " + infected);
        System.out.println("Cantidad de fallecidos: " + deceased);
        System.out.println("Porcentaje de infectado por muestras: %" + SampleInfectedPercent());
        System.out.println("Porcentaje de fallecidos por infectados: %" + InfectedDeceasedPercent());

        for (int i = 0; i < infectedAge.length; i++)
            System.out.println("Cantidad de infectados en el rango de " + i * 10 + " hasta " + (i + 1) * 10 + ": "
                    + infectedAge[i]);
        System.out.println();
        for (int i = 0; i < infectedAge.length; i++)
            System.out.println("Cantidad de fallecidos en el rango de " + i * 10 + " hasta " + (i + 1) * 10 + ": "
                    + deceasedAge[i]);

    }

}