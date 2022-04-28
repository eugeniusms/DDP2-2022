public class HealthinessUnworthyException extends Exception {
    private String tidakLayak;

    public HealthinessUnworthyException(String nama) {
        super(nama + ": TIDAK LAYAK");
        this.tidakLayak = nama + ": TIDAK LAYAK";
    }

    public String toString() {
        return this.tidakLayak;
    }
}
