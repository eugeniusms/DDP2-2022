package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;
import assignments.assignment3.buku.Peminjaman;

// Kelas ini merupakan representasi dari dosen yang menjadi pengguna SistakaNG
public class Dosen extends Anggota {
    // Berikut merupakan attribute dari kelas Dosen
    private static int jumlahDosen = 0;
    // Batas ini merupakan batas maksimal buku yang sedang dipinjam (belum dikembalikan)
    public final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 5;
    // Batas ini merupakan batas maksimal denda untuk dosen dapat meminjam buku
    public final long BATAS_MAKSIMAL_DENDA = 10000;

    // Constructor Dosen
    public Dosen(String nama) {
        super("", nama);
        this.setId(generateId());
    }

    // Method yang digunakan untuk generate ID
    protected String generateId() {
        // Menambahkan jumlahDosen saat ini
        jumlahDosen++;
        // Generate Id DOSEN-<urutan_ketika_mendaftar>
        return String.format("DOSEN-%d", jumlahDosen);
    }

    // Method pinjam digunakan untuk memproses peminjaman oleh dosen
    public String pinjam(Buku buku, String tanggalPeminjaman) {
        // STEP 1 : Cek batas peminjaman aktif
        // Saat masih ada < 5 buku maka mahasiswa dapat lanjut le step 2
        if (this.getJumlahPeminjamanAktif() < BATAS_JUMLAH_PEMINJAMAN_BUKU) {
            // STEP 2 : Cek jumlah denda 
            // Saat masih di bawah batas maksimal denda maka dapat lanjut ke step 3
            if (this.getDenda() < BATAS_MAKSIMAL_DENDA) {
                // STEP 3 : Cek buku sedang ia pinjam
                // Saat buku yang sedang dipinjam kosong maka dapat langsung melakukan peminjaman
                if (this.getJumlahPeminjamanAktif() == 0) {

                    Peminjaman pinjaman = new Peminjaman(this, buku, tanggalPeminjaman);
                    this.tambahDaftarPinjaman(pinjaman);
                    // Pengurangan stok buku
                    buku.setStok(buku.getStok()-1);
                    // Menambahkan buku sedang dipinjam anggota ini
                    buku.tambahPeminjam(this);
                    // Menambahkan jumlah peminjaman aktif anggota
                    this.setJumlahPeminjamanAktif(this.getJumlahPeminjamanAktif() + 1);

                    return String.format("%s berhasil meminjam Buku %s!", this.getNama(), buku.getJudul());
                }
                // Cek apakah buku sedang dipinjam sendiri
                // Jika dalam daftar peminjam terdapat anggota ini maka peminjaman tidak bisa dilakukan
                if (buku.getPeminjam().contains(this)) { 
                    // Saat buku sedang ia pinjam, maka ia tidak dapat meminjamnya lagi
                    return String.format("Buku %s oleh %s sedang dipinjam", buku.getJudul(), buku.getPenulis());
                }

                // Saat buku tidak ia pinjam maka peminjaman dapat dilakukan
                Peminjaman pinjaman = new Peminjaman(this, buku, tanggalPeminjaman);
                this.tambahDaftarPinjaman(pinjaman);
                // Pengurangan stok buku
                buku.setStok(buku.getStok()-1);
                // Menambahkan buku sedang dipinjam anggota ini
                buku.tambahPeminjam(this);
                // Menambahkan jumlah peminjaman aktif anggota
                this.setJumlahPeminjamanAktif(this.getJumlahPeminjamanAktif() + 1);

                return String.format("%s berhasil meminjam Buku %s!", this.getNama(), buku.getJudul());
            } else {
                // Saat denda >= BATAS MAKSIMAL DENDA
                return String.format("Denda lebih dari Rp%d", BATAS_MAKSIMAL_DENDA);
            }
        } else {
            // Saat jumlah peminjaman buku melebihi kapasitas pinjaman
            return String.format("Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal");
        }
    }
}
