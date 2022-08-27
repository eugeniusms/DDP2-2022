package assignments.assignment4.backend.buku;

public class Kategori {
    private String nama;
    private int poin;

    public Kategori(String nama, int poin) {
        this.nama = nama;
        this.poin = poin;
    }

    @Override
    public String toString() {
        return String.format("Kategori: %s%nPoin: %d",
                nama, poin);
    }

    public int getPoin() {
        return poin;
    }

    public String getNama() {
        return nama;
    }
}
