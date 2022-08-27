package assignments.assignment4.backend.pengguna;

import assignments.assignment4.backend.buku.Buku;

public abstract class Pengguna {
    private String id;
    private String nama;

    protected Pengguna(String nama) {
        this.nama = nama;
    }

    // MY ABSTRACT METHOD
    public abstract String pinjam(Buku buku, String tanggal);
    public abstract String kembali(Buku buku, String tanggal);
    public abstract String bayarDenda(long denda);
    public abstract String detail();
    public abstract int findBookOnLoan(Buku buku);

    protected abstract String generateID();

    @Override
    public abstract String toString();

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract String getTipe();
}
