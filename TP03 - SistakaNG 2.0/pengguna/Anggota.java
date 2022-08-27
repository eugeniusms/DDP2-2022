package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

// Import Java Library
import java.util.*;

// Kelas anggota merupakan abstract class yang merupakan subclass dari Pengguna
// dan mengimplementasi interface Comparable untuk memudahkan pengurutan anggota
// berdasarkan poin dan abjadnya (kelas ini merepresentasikan Anggota dari SistakaNG)
// Sumber : https://stackoverflow.com/questions/14162788/combination-of-extend-abstract-class-and-implement-interface
public abstract class Anggota extends Pengguna implements Comparable<Anggota> {
    // Atribut yang dimiliki kelas Anggota : denda, poin, dan daftarPeminjaman
    protected long denda = 0;
    protected int poin = 0;
    protected ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<>();
    protected int jumlahPeminjamanAktif = 0;

    // Constructor Anggota
    public Anggota(String id, String nama) {
        super(id, nama);
        // Atribut Default Anggota
        this.denda = 0;
        this.poin = 0;
        this.daftarPeminjaman = new ArrayList<>();
        this.jumlahPeminjamanAktif = 0;
    }

    // Method compareTo implementasi dari interface Comparable bawaan Java untuk
    // mempermudah pengurutan anggota berdasarkan poin dan abjadnya
    // Sumber : https://www.tutorialspoint.com/what-are-the-differences-between-compareto-and-compare-methods-in-java
    @Override
    public int compareTo(Anggota other) {
        // Bandingkan berdasarkan poin terlebih dahulu
        if (this.poin - other.poin != 0) { 
            // Saat poin tidak sama maka bisa langsung dibandingkan
            return this.poin - other.poin;
        } else {
            // Saat poin sama maka bandingkan berdasar lexicographically namanya
            return other.getNama().compareTo(this.getNama());
        }
    }

    // Method untuk melakukan pencetakan tampilan dengan format:
    // ID Anggota: <id>
    // Nama Anggota: <nama>
    // Total poin: <poin>
    // Denda: <denda>
    public String toString() {
        String form = "ID Anggota: " + this.getId() + "\n"
                    + "Nama Anggota: " + this.getNama() + "\n"
                    + "Total Poin: " + this.poin + "\n"
                    + "Denda: Rp" + this.denda;
        return form;
    }

    // Method ini digunakan untuk mencetak detail dari setiap anggota
    // yang berisi data anggota dan riwayat peminjamannya
    public void detail() {
        String form = "ID Anggota: " + this.getId() + "\n"
                    + "Nama Anggota: " + this.getNama() + "\n"
                    + "Total Poin: " + this.poin + "\n"
                    + "Denda: Rp" + this.denda + "\n"
                    + "Riwayat Peminjaman Buku:";
        System.out.println(form);

        // Cek dahulu apakah memiliki peminjaman buku
        if (this.daftarPeminjaman == null || this.daftarPeminjaman.size() == 0) {
            System.out.println("Belum pernah meminjam buku");
        } else {
            // Saat anggota sudah pernah meminjam buku
            int counter = 1;
            for (Peminjaman pm : this.daftarPeminjaman) {
                System.out.printf("----------------- %d -----------------\n", counter);
                System.out.println(pm.toString()); // Representasi dari toString kelas Peminjaman
                counter++;
            }
        }
    }

    // Method untuk memproses pembayaran denda anggota
    public String bayarDenda(long jumlahBayar) {
        // Jika anggota tidak memiliki denda maka kembalikan pesan
        if (this.denda == 0) {
            return String.format("%s tidak memiliki denda", this.getNama());
        } 
        // Check selisih jumlahBayar dengan denda
        // Saat selisih (-) negatif -> artinya anggota belum lunas dendanya (jumlah bayar masih kurang)
        // Saat selisih (+) positif atau (0) nol -> artinya anggota telah melunasi dendanya
        // Sumber : https://docs.oracle.com/javase/tutorial/java/data/numberformat.html
        long selisih = jumlahBayar - denda;
        if (selisih < 0) {
            // Mengurangi jumlah denda
            this.denda -= jumlahBayar;
            // Mengembalikan pesan
            return String.format("%s berhasil membayar denda sebesar Rp%d\nSisa denda saat ini: Rp%d", this.getNama(), jumlahBayar, this.denda);
        } else {
            // Denda langsung kosong
            this.denda = 0;
            // Kembalian = selisih
            return String.format("%s berhasil membayar lunas denda\nJumlah kembalian: Rp%d", this.getNama(), selisih);
        }
    }

    // Method untuk memproses pengembalian buku anggota
    public String kembali(Buku buku, String tanggalPengembalian) {
        // Check validitas dulu daftar buku yang sedang dipinjam lebih dari 0
        if (this.daftarPeminjaman == null || this.daftarPeminjaman.size() == 0) {
            return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getJudul(), this.getNama());
        }
        // Check apakah buku sedang dipinjam oleh anggota
        for (Peminjaman pm : this.daftarPeminjaman) {
            // Mengambil judul semua buku dipinjam dan yang ingin dikembalikan dalam case insensitive
            String bukuDipinjam = pm.getBuku().getJudul().toLowerCase();
            String bukuDikembalikan = buku.getJudul().toLowerCase();

            // Saat ditemukan maka buku akan kembali dan mengirim pesan
            if (bukuDipinjam.equals(bukuDikembalikan)){
                pm.kembalikanBuku(tanggalPengembalian);
                // Mengembalikan stok buku +1
                Buku dipinjam = pm.getBuku();
                dipinjam.setStok(dipinjam.getStok() + 1);

                // Denda bertambah
                long dendaDidapati = pm.hitungDenda();
                this.setDenda(this.getDenda() + dendaDidapati);
                // Poin bertambah
                this.setPoin(this.getPoin() + dipinjam.getKategori().getPoin());
                // Menghilangkan peminjam buku ini
                dipinjam.kurangiPeminjam(this);
                // Mengurangi jumlah peminjaman aktif anggota
                this.jumlahPeminjamanAktif--;
                
                // Mengembalikan pesan
                return String.format("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%d!", buku.getJudul(), this.getNama(), dendaDidapati);
            }
        }

        // Program akan ke sini saat tidak ditemukan buku yang dipinjam
        return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getJudul(), this.getNama());
    }

    // Method ini digunakan untuk menambah daftar peminjaman anggota
    public void tambahDaftarPinjaman(Peminjaman pinjaman) {
        this.daftarPeminjaman.add(pinjaman);
    }

    // Getter and Setter
    public int getJumlahPeminjamanAktif() {
        return this.jumlahPeminjamanAktif;
    }

    public long getDenda() {
        return this.denda;
    }

    public int getPoin() {
        return this.poin;
    }

    public ArrayList<Peminjaman> getDaftarPeminjaman() {
        return this.daftarPeminjaman;
    }

    public void setJumlahPeminjamanAktif(int jumlah) {
        this.jumlahPeminjamanAktif = jumlah;
    }

    public void setDenda(long denda) {
        this.denda = denda;
    }

    public void setPoin(int poin) {
        this.poin = poin;
    }

}
