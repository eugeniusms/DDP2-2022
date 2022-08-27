package assignments.assignment4.backend.pengguna;

import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Peminjaman;

public class Mahasiswa extends Anggota {
    public static final int BATAS_JUMLAH_PEMINJAMAN_BUKU = 3;
    public static final long BATAS_MAKSIMAL_DENDA = 5000;

    private String tanggalLahir;
    private String programStudi;
    private String angkatan;

    public Mahasiswa(String nama, String tanggalLahir, String programStudi, String angkatan) {
        super(nama);
        this.tanggalLahir = tanggalLahir;
        this.programStudi = programStudi;
        this.angkatan = angkatan;
        this.setId(generateID());
    }

    @Override
    protected String generateID() {
        IdGenerator.buildMapCharToValue();
        return IdGenerator.generateId(programStudi, angkatan, tanggalLahir).substring(12);
    }

    @Override
    public String getTipe() {
        return "anggota";
    }

    public String pinjam(Buku buku, String tanggalPeminjaman) {
        if (daftarPeminjamanAktif.size() == BATAS_JUMLAH_PEMINJAMAN_BUKU) {
            return "Jumlah buku yang sedang dipinjam sudah mencapai batas maksimal";
        } else if (denda >= BATAS_MAKSIMAL_DENDA) {
            return "Denda lebih dari Rp" + BATAS_MAKSIMAL_DENDA;
        } else if (findBookOnLoan(buku) != -1) {
            return String.format("Buku %s oleh %s sedang dipinjam", buku.getJudul(), buku.getPenulis());
        } else {
            Peminjaman pinjaman = new Peminjaman(this, buku, tanggalPeminjaman);
            daftarPeminjaman.add(pinjaman);
            daftarPeminjamanAktif.add(pinjaman);
            buku.decreaseStock();
            buku.addPeminjam(this);
            return String.format("%s berhasil meminjam Buku %s!", getNama(), buku.getJudul());
        }
    };
}
