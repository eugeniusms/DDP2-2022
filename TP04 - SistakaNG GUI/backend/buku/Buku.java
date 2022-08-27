package assignments.assignment4.backend.buku;

import assignments.assignment4.backend.pengguna.CanBorrow;

import java.util.ArrayList;

public class Buku {
    private String judul;
    private String penulis;
    private String penerbit;
    private int stok;
    private Kategori kategori;
    private int stokAwal;
    private ArrayList<CanBorrow> daftarPeminjam = new ArrayList<>();

    public Buku(String judul, String penulis, String penerbit, int stok, Kategori kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.stok = stok;
        this.kategori = kategori;
        this.stokAwal = stok;
    }

    public boolean equals(Buku otherBook) {
        if(this.judul.equalsIgnoreCase(otherBook.getJudul()) && this.penulis.equalsIgnoreCase(otherBook.getPenulis())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return judul + " oleh " + penulis;
    }

    public String getData() {
        return String.format("Judul Buku: %s<br/>" +
                        "Penulis Buku: %s<br/>" +
                        "Penerbit Buku: %s<br/>" +
                        "Kategori: %s<br/>" +
                        "Point: %d",
                judul, penulis, penerbit, kategori.getNama(), kategori.getPoin());
    }

    public void addPeminjam(CanBorrow anggota) {
        daftarPeminjam.add(anggota);
    }

    public void decreaseStock() {
        stok--;
    }

    public void increaseStock() {
        stok++;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public int getStok() {
        return stok;
    }

    public int getStokAwal() {
        return stokAwal;
    }

    public ArrayList<CanBorrow> getDaftarPeminjam() {
        return daftarPeminjam;
    }
}
