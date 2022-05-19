// Kelas Pesanan merupakan implementasi dari Comparable<Pesanan>
// digunakan sebagai blueprint dari sebuah pesanan oleh user
public class Pesanan implements Comparable<Pesanan> {
    // Atribut kelas Pesanan
    private String nama;
    private int harga;
    private int prioritas;

    // Constructor Pesanan
    public Pesanan(String nama, int harga, int prioritas) {
        this.nama = nama;
        this.harga = harga;
        this.prioritas = prioritas;
    }

    // compareTo method digunakan untuk membandingkan nilai prioritas pesanan
    @Override
    public int compareTo(Pesanan o) {
        // Jika nilai prioritas lebih tinggi maka return 1
        if (this.prioritas > o.prioritas) {
            return 1;
        }
        return 0;
    }

    // Getter & Setter

    // Getter nama pesanan
    public String getNama() {
        return this.nama;
    }
}