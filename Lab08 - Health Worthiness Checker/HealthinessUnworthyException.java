// Kelas ini merupakan kelas custom exception error untuk HealthinessWorthy
public class HealthinessUnworthyException extends Exception {
    private String tidakLayak;

    // Constructor
    public HealthinessUnworthyException(String nama) {
        super(nama + ": TIDAK LAYAK");
        this.tidakLayak = nama + ": TIDAK LAYAK";
    }

    // Method toString()
    public String toString() {
        return this.tidakLayak;
    }
}
