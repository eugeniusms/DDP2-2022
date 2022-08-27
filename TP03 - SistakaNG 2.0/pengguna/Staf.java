package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;

// Kelas staf merupakan representasi staf yang menjadi pengguna SistakaNG
public class Staf extends Pengguna {
    // Atribut pada kelas Staf : jumlahStaf digunakan untuk menyimpan jumlah
    // staf yang telah terdaftar menjadi pengguna SistakaNG 
    // (digunakan pada method generateId)
    private static int jumlahStaf = 0;

    // Constructor pengguna jenis : "Staf" yang diwariskan dari parent class
    public Staf(String nama) {
        super("", nama);
        this.setId(generateId());
    }

    // Method ini digunakan untuk men-generate id staf dengan format:
    // STAF-<urutan_ketika_mendaftar>
    // Contoh: STAF-11
    protected String generateId() {
        // Menambahkan jumlahStaf saat ini
        jumlahStaf++;
        // Generate Id STAF-<urutan_ketika_mendaftar>
        return String.format("STAF-%d", jumlahStaf);
    }

    // Mencetak tampilan string dengan format
    // ID Staf: <id>
    // Nama Staf: <nama>
    public String toString() {
        return String.format("ID Staf: %s\nNama Staf: %s", this.getId(), this.getNama());
    }
    
    // Beberapa method di bawah sengaja kosong merupakan hasil warisan dari Pengguna
    public void detail() {
        // Method sengaja kosong
    }

    public String pinjam(Buku buku, String tanggalPinjaman) {
        return "";
    }

    public String kembali(Buku buku, String tanggalPengembalian) {
        return "";
    } 

    public String bayarDenda(long jumlahBayar) {
        return "";
    }

}
