public class Pesanan implements Comparable<Pesanan> {
    // TODO: tambahkan attributes
    private String nama;
    private int harga;
    private int prioritas;

    public Pesanan(String nama, int harga, int prioritas) {
        // TODO: Lengkapi Constructor berikut
        this.nama = nama;
        this.harga = harga;
        this.prioritas = prioritas;
    }

    // CEK ULANG METHODNYA
    @Override
    public int compareTo(Pesanan o) {
        // TODO: Lengkapi method ini
        if (this.prioritas > o.prioritas) {
            return 1;
        }
        return 0;
    }

    // Tambahkan getter-setter bila diperlukan
    public String getNama() {
        return this.nama;
    }
}