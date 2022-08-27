package assignments.assignment3.buku;

import assignments.assignment3.pengguna.CanBorrow;
import assignments.assignment3.pengguna.Anggota;

import java.util.*;

// Kelas Buku merupakan representasi dari sebuah buku beserta atributnya
public class Buku {
    // Berikut merupakan atribut dari sebuah buku
    private String judul;
    private String penulis;
    private String penerbit;
    // Menyimpan stok buku saat pertama kali didaftarkan
    private int stokAwal; 
    // Value berubah tergantung stok saat ini
    private int stok;
    private Kategori kategori;
    // Menyimpan semua peminjam buku ini
    private ArrayList<CanBorrow> daftarPeminjam; 
    private ArrayList<Anggota> peminjamBukuAktif = new ArrayList<>();
    private ArrayList<Anggota> semuaPeminjamBuku = new ArrayList<>();

    // Constructor Buku
    public Buku(String judul, String penulis, String penerbit, int stokAwal, Kategori kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stokAwal = stokAwal;
        this.kategori = kategori;
        // Default attribute
        this.stok = stokAwal;
        this.peminjamBukuAktif = new ArrayList<>();
        this.semuaPeminjamBuku = new ArrayList<>();
    }

    // Method untuk mengembalikan representasi string dari buku
    public String toString() {
        String form = "Judul Buku: " + this.judul + "\n"
                    + "Penulis Buku: " + this.penulis + "\n"
                    + "Penerbit Buku: " + this.penerbit + "\n"
                    + this.kategori.toString(); // Representasi string dari kelas kategori
        return form;
    }

    // Method daftar peminjam
    public void detailPeminjam(){
        // Mengambil informasi mengenai buku
        String form = this.toString();
        System.out.println(form);

        System.out.println("---------- Daftar Peminjam ----------");
        // Cek dahulu apakah ada yang pernah meminjam buku
        if (this.semuaPeminjamBuku == null || this.semuaPeminjamBuku.size() == 0) {
            System.out.println("Belum ada anggota yang meminjam buku " + this.getJudul());
        } else {
            // Saat anggota sudah pernah meminjam buku
            int counter = 1;
            for (Anggota ang : this.semuaPeminjamBuku) {
                System.out.printf("----------------- %d -----------------\n", counter);
                System.out.println(ang.toString()); // Representasi dari toString kelas Anggota
                counter++;
            }
        }
    }

    // Method ini digunakan untuk menambah daftar peminjam buku ini baik aktif dan keseluruhan
    public void tambahPeminjam(Anggota anggota) {
        peminjamBukuAktif.add(anggota);
        // Pasti akan ditambahkan ke peminjam yang menyeluruh
        // Tetapi saat sudah ada ya tidak usah ditambahkan lagi
        // Saat masih kosong dapat langsung ditambahkan
        if (semuaPeminjamBuku == null || semuaPeminjamBuku.size() == 0) {
            semuaPeminjamBuku.add(anggota);
        } else {
            // Saat tidak mengandung anggota yang sama dapat ditambahkan
            if (!semuaPeminjamBuku.contains(anggota)) {
                semuaPeminjamBuku.add(anggota);
            }
        }
    }

    // Method ini digunakan untuk mengurangi daftar peminjam aktif saat ini
    public void kurangiPeminjam(Anggota anggota) {
        peminjamBukuAktif.remove(anggota);
    }

    // Method ini digunakan untuk mengecek apakah buku sedang dipinjam atau tidak
    public boolean sedangDipinjam() {
        // Saat peminjam aktif lebih dari 0 maka buku sedang dipinjam
        if (peminjamBukuAktif.size() > 0) {
            return true;
        }
        return false;
    }

    // Getter and Setter
    public String getJudul() {
        return this.judul;
    }

    public String getPenulis() {
        return this.penulis;
    }

    public int getStok() {
        return this.stok;
    }

    public Kategori getKategori() {
        return this.kategori;
    }

    public ArrayList<Anggota> getPeminjam() {
        return this.peminjamBukuAktif;
    }

    public void setStok(int newStok) {
        this.stok = newStok;
    }

}
