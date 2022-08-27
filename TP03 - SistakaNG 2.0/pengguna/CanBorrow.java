package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;

// CanBorrow merupakan sebuah interface yang akan diimplement untuk 
// kelas yang dapat melakukan peminjaman
public interface CanBorrow {
    // Method yang disediakan dalam interface ini adalah pinjam dan kembali
    public String pinjam(Buku buku, String tanggalPeminjaman);
    public String kembali(Buku buku, String tanggalPengembalian);
}
