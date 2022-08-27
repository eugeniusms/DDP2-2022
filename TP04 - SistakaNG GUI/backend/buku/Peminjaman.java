package assignments.assignment4.backend.buku;

import assignments.assignment4.backend.pengguna.Anggota;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Peminjaman {
    private long DENDA_PER_HARI = 3000;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPeminjaman;
    private String tanggalPengembalian = "-";
    private long denda = 0;
    private boolean status = false;

    public Peminjaman(Anggota anggota, Buku buku, String tanggalPeminjaman) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    @Override
    public String toString() {
        return String.format("%s<br />Tanggal Peminjaman: %s<br />Tanggal Pengembalian: %s<br />Denda: Rp%d",
                buku.getData(), tanggalPeminjaman, tanggalPengembalian, denda);
    }

    public void kembalikanBuku(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
        this.status = true;
        buku.increaseStock();
    }

    public long hitungDenda() {
        String[] loanSplit = tanggalPeminjaman.split("/");
        String[] returnSplit = tanggalPengembalian.split("/");
        Date loan = new Calendar.Builder().setDate(Integer.parseInt(loanSplit[2]), Integer.parseInt(loanSplit[1]) - 1, Integer.parseInt(loanSplit[0])).build().getTime();
        Date ret = new Calendar.Builder().setDate(Integer.parseInt(returnSplit[2]), Integer.parseInt(returnSplit[1]) - 1, Integer.parseInt(returnSplit[0])).build().getTime();
        Long durasi = TimeUnit.DAYS.convert(ret.getTime() - loan.getTime(), TimeUnit.MILLISECONDS);
        Long denda = (durasi - 7) * DENDA_PER_HARI;
        if (denda > 0 ){
            this.denda = denda;
            return denda;
        }
        return 0;
    }

    public Buku getBuku() {
        return buku;
    }

    public long getDenda() {
        return denda;
    }
}
