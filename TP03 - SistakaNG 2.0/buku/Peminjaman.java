package assignments.assignment3.buku;

import assignments.assignment3.pengguna.Anggota;
// Import Java Library
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// Kelas ini berperan dalam menjalankan peminjaman buku
public class Peminjaman {
    // Berikut merupakan daftar atribut dari kelas Peminjaman
    private final long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian;
    private long denda = 0;
    
    // Constructor Peminjaman
    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
        // Default attribute
        this.tanggalPengembalian = "-";
        this.denda = 0;
    }

    // Method yang mengembalikan representasi string dari Peminjaman
    public String toString() {
        String form = this.buku.toString() + "\n" // Representasi string kelas Buku
                    + "Tanggal Peminjaman: " + this.tanggalPeminjaman + "\n"
                    + "Tanggal Pengembalian: " + this.tanggalPengembalian + "\n"
                    + "Denda: Rp" + this.denda;
        return form; 
    }

    // Method ini dilakukan ketika buku dikembalikan
    public void kembalikanBuku(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    // Method ini digunakan untuk menghitung denda saat peminjaman diselesaikan
    public long hitungDenda() {
        long selisih = selisihHari(this.tanggalPeminjaman, this.tanggalPengembalian);
        // Saat selisih hari pengembalian masih dibawah tujuh maka tidak ada denda
        if (selisih <= 7) {
            this.denda = 0;
            return 0;
        } else {
            // Saat denda sudah mulai berlaku maka terapkan denda per harinya
            this.denda = DENDA_PER_HARI * (selisih-7);
            return DENDA_PER_HARI * (selisih-7);
        }
    }

    // Method untuk menghitung selisih hari pinjam dan kembali
    public long selisihHari(String tglPinjam, String tglKembali) {
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
            = new SimpleDateFormat(
                "dd/MM/yyyy");
  
        // Try Block
        try {
            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(tglPinjam);
            Date d2 = sdf.parse(tglKembali);
  
            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                = d2.getTime() - d1.getTime();
  
            // Calucalte time difference in
            long difference_In_Days
                = (difference_In_Time
                   / (1000 * 60 * 60 * 24))
                  % 365;
  
            return difference_In_Days;
        }
  
        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Getter and Setter
    public Buku getBuku() {
        return this.buku;
    }

    public Anggota getAnggota() {
        return this.anggota;
    }
}
