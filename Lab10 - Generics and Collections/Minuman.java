// Kelas Minuman merupakan child class dari Pesanan
public class Minuman extends Pesanan {
    // Atribut kelas Minuman
    private boolean isPakeEs;

    // Constructor Minuman
    public Minuman(String nama, int harga, int prioritas, boolean isPakeEs) {
        super(nama, harga, prioritas);
        this.isPakeEs = isPakeEs;
    }

    // Method toString digunakan untuk mengembalikan wujud string dari Minuman
    @Override
    public String toString() {
        if (isPakeEs) {
            return String.format("%s dingin", this.getNama());
        } 
        return String.format("%s hangat", this.getNama());
    }

}
