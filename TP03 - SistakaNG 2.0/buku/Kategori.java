package assignments.assignment3.buku;

// Kategori merupakan kelas klasifikasi kategori buku
public class Kategori {
    // Atribut yang ada pada kelas Kategori adalah : nama dan poin
    // Nama merupakan nama kategori dan poin merupakan poin yang akan
    // didapat oleh pengguna saat menyelesaikan buku dengan kategori tertentu
    private String nama;
    private int poin;

    // Constructor Kategori
    public Kategori(String nama, int poin) {
        this.nama = nama;
        this.poin = poin;
    }

    // Method ini mengembalikan representasi string dari Kategori dengan format:
    // Kategori: <nama>
    // Poin: <poin>
    public String toString() {
        return String.format("Kategori: %s\nPoin: %d", this.nama, this.poin);
    }

    // Getter and Setter
    public String getNama() {
        return this.nama;
    }

    public int getPoin() {
        return this.poin;
    }

}
