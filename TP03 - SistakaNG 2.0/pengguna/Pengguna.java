package assignments.assignment3.pengguna;

import assignments.assignment3.buku.Buku;

public abstract class Pengguna {
    // Atribut pada kelas Pengguna : id dan nama
    private String id;
    private String nama;

    // Constructor seorang pengguna 
    protected Pengguna(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    // Method abstrak generateId() dan toString()
    protected abstract String generateId();
    public abstract String toString();
    public abstract String pinjam(Buku buku, String tanggalPinjaman);
    public abstract String kembali(Buku buku, String tanggalPengembalian);
    public abstract String bayarDenda(long jumlahBayar);
    public abstract void detail();

    // Getter and Setter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String newId) {
        this.id = newId;
    }

}
