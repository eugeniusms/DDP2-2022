package assignments.assignment4.backend.pengguna;

import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.backend.buku.Peminjaman;

import java.util.ArrayList;

public abstract class Anggota extends Pengguna implements Comparable<Anggota>, CanBorrow {
    protected long denda = 0;
    protected int poin = 0;
    protected ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<>();
    protected ArrayList<Peminjaman> daftarPeminjamanAktif = new ArrayList<>();

    protected Anggota(String nama) {
        super(nama);
    }

    @Override
    public int compareTo(Anggota other) {
        if (this.poin > other.getPoin()) return -1;
        if (this.poin < other.getPoin()) return 1;
        return this.getNama().compareTo(other.getNama());
    }

    public String detail() {
        StringBuilder stringBuilder = new StringBuilder("<html><body>");
        stringBuilder.append(this).append("<br/>");
        stringBuilder.append("Riwayat Peminjaman Buku: <br/>");
        if (daftarPeminjaman.size() <= 0) {
            stringBuilder.append("Belum pernah meminjam buku<br/></body></html>");
            return stringBuilder.toString();
        }
        for (int i = 0 ; i < daftarPeminjaman.size(); i++){
            stringBuilder.append(String.format("----------------- %d -----------------<br/>", i+1));
            stringBuilder.append(daftarPeminjaman.get(i)).append("<br/>");
        }
        return stringBuilder.append("<br/></body></html>").toString();
    }

    public String bayarDenda(long jumlahBayar) {
        if (denda == 0) {
            return String.format("%s tidak memiliki denda", getNama());
        } else if (denda > jumlahBayar) {
            String response = String.format("%s berhasil membayar denda sebesar Rp%d%n", getNama(), jumlahBayar);
            denda -= jumlahBayar;
            return response + String.format("Sisa denda saat ini: Rp%d", denda);
        } else {
            String response = String.format("%s berhasil membayar lunas denda%n", getNama());
            long uangKembalian = jumlahBayar - denda;
            denda = 0;
            return response + String.format("Jumlah kembalian: Rp%d", uangKembalian);
        }
    }

    @Override
    public String toString() {
        return String.format("ID Anggota: %s<br/>" +
                        "Nama Anggota: %s<br/>" +
                        "Total Poin: %d<br/>" +
                        "Denda: Rp%s<br/>",
                getId(), getNama(), poin, denda
        );
    }

    public int findBookOnLoan(Buku buku) {
        for (int i = 0; i < daftarPeminjamanAktif.size(); i++) {
            if (daftarPeminjamanAktif.get(i).getBuku().equals(buku)) return i;
        }
        return -1;
    }

    public String kembali(Buku buku, String tanggalPengembalian) {
        int idxOnLoan = findBookOnLoan(buku);
        if (idxOnLoan == -1) {
            return String.format("Buku %s tidak sedang dipinjam oleh %s", buku.getJudul(), getNama());
        } else {
            Peminjaman peminjaman = daftarPeminjamanAktif.get(idxOnLoan);
            returnBook(peminjaman, tanggalPengembalian);
            daftarPeminjamanAktif.remove(idxOnLoan);
            return String.format("Buku %s berhasil dikembalikan oleh %s dengan denda Rp%d!",
                    buku.getJudul(), getNama(), peminjaman.getDenda());
        }
    }

    protected void returnBook(Peminjaman peminjaman, String tanggalPengembalian) {
        peminjaman.kembalikanBuku(tanggalPengembalian);
        denda += peminjaman.hitungDenda();
        poin += peminjaman.getBuku().getKategori().getPoin();
    }

    public int getPoin() {
        return poin;
    }
}
