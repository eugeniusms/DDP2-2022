package assignments.assignment4.backend.pengguna;

import assignments.assignment4.backend.buku.Buku;

public interface CanBorrow {
    public String pinjam(Buku buku, String tanggalPeminjaman);
    public String kembali(Buku buku, String tanggalPengembalian);
}
