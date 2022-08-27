package assignments.assignment4.backend.pengguna;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Kategori;

public class Staf extends Pengguna {
    private static int jumlahStaf = 0;

    public Staf(String nama) {
        super(nama);
        this.setId(generateID());
        jumlahStaf++;
    }

    @Override
    protected String generateID() {
        return String.format("STAF-%d", jumlahStaf + 1);
    }

    @Override
    public String toString() {
        return String.format("ID Staf: %s%nNama Staf: %s", getId(), getNama());
    }

    @Override
    public String getTipe() {
        return "staf";
    }

    public Mahasiswa tambahMahasiswa(String nama, String tanggalLahir, String programStudi, String angkatan) {
        Mahasiswa mahasiswa = new Mahasiswa(nama, tanggalLahir, programStudi, angkatan);

        if (mahasiswa.getId().length() != 13) {
            System.out.println("Tidak dapat menambahkan anggota silahkan periksa kembali input anda!");
            return null;
        }

        System.out.println("Berhasil menambahkan mahasiswa dengan data:");
        System.out.println(mahasiswa);
        return mahasiswa;
    }

    public Dosen tambahDosen(String nama) {
        Dosen dosen = new Dosen(nama);

        System.out.println("Berhasil menambahkan dosen dengan data:");
        System.out.println(dosen);
        return dosen;
    }

    public Kategori tambahKategori(String nama, int poin) {
        Kategori kategori = SistakaNG.findKategori(nama);
        if (kategori != null) {
            System.out.printf("Kategori %s sudah pernah ditambahkan%n", kategori.getNama());
            return null;
        }
        kategori = new Kategori(nama, poin);
        System.out.printf("Kategori %s dengan poin %d berhasil ditambahkan%n", nama, poin);
        return kategori;
    }

    public Buku tambahBuku(String judul, String penulis, String penerbit, String kategori, int stok) {
        Buku buku = SistakaNG.findBuku(judul, penulis);
        if (buku != null) {
            System.out.printf("Buku %s oleh %s sudah pernah ditambahkan%n", buku.getJudul(), buku.getPenulis());
            return null;
        }

        Kategori kategoriObj= SistakaNG.findKategori(kategori);
        if (kategoriObj == null) {
            System.out.printf("Kategori %s tidak ditemukan%n", kategori);
            return null;
        }

        if (stok <= 0) {
            System.out.println("Stok harus lebih dari 0");
            return null;
        }

        buku = new Buku(judul, penulis, penerbit, stok, kategoriObj);
        System.out.printf("Buku %s oleh %s berhasil ditambahkan%n", judul, penulis);
        return buku;
    }

    public String hapusBuku(String judul, String penulis) {
        Buku buku = SistakaNG.findBuku(judul, penulis);

        if (buku == null) {
            System.out.printf("Buku %s oleh %s tidak ditemukan%n", judul, penulis);
            return String.format("Buku %s oleh %s tidak ditemukan%n", judul, penulis);
        }

        if (buku.getStokAwal() != buku.getStok()) {
            System.out.printf("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam",
                    buku.getJudul(), buku.getPenulis());
            return String.format("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam",
                    buku.getJudul(), buku.getPenulis());
        }

        SistakaNG.removeBuku(buku);
        System.out.printf("Buku %s oleh %s berhasil dihapus%n", buku.getJudul(), buku.getPenulis());
        return String.format("Buku %s oleh %s berhasil dihapus%n", buku.getJudul(), buku.getPenulis());
    }

    public String hapusBuku(Buku buku) {
        if (buku.getStokAwal() != buku.getStok()) {
            return String.format("Buku %s oleh %s tidak dapat dihapus karena sedang dipinjam%n",
                    buku.getJudul(), buku.getPenulis());
        }

        SistakaNG.removeBuku(buku);
        return String.format("Buku %s oleh %s berhasil dihapus%n", buku.getJudul(), buku.getPenulis());
    }

    // Must implemented 
    @Override
    public String pinjam(Buku buku, String tanggalPeminjaman) {
        // Pass
        return "";
    }

    @Override
    public String kembali(Buku buku, String tanggalPeminjaman) {
        // Pass
        return "";
    }

    @Override
    public String bayarDenda(long denda) {
        // Pass
        return "";
    }

    @Override
    public String detail() {
        // Pass
        return "";
    }

    @Override
    public int findBookOnLoan(Buku buku) {
        // Pass
        return 0;
    }
}
