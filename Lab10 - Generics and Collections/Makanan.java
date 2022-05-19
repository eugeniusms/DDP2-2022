// Kelas Makanan merupakan child class dari Pesanan
public class Makanan extends Pesanan {
    // Atribut Kelas Makanan
    private int tingkatKepedasan;

    // Constructor Makanan
    public Makanan(String nama, int harga, int prioritas, int tingkatKepedasan) {
        super(nama, harga, prioritas);
        this.tingkatKepedasan = tingkatKepedasan;
    }

    // Method toString digunakan untuk mengembalikan wujud string dari Makanan
    @Override
    public String toString() {
        return String.format("%s level %d", this.getNama(), this.tingkatKepedasan);
    }
    
}
