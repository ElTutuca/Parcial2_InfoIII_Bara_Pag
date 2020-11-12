public class GlobalStats {

    private int samples;
    private int infected;
    private int deceased;
    private int[] infectedAge = new int[11];
    private int[] deceasedAge = new int[11];

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