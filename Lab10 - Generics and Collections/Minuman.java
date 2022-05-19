// Ikuti UML diagram

public class Minuman extends Pesanan {
    // TODO: tambahkan attributes
    private boolean isPakeEs;

    public Minuman(String nama, int harga, int prioritas, boolean isPakeEs) {
        // TODO: Lengkapi Constructor berikut
        super(nama, harga, prioritas);
        this.isPakeEs = isPakeEs;
    }

    @Override
    public String toString() {
        // TODO: return deskripsi sesuai dengan ketentuan soal
        if (isPakeEs) {
            return String.format("%s dingin", this.getNama());
        } 
        return String.format("%s hangat", this.getNama());
    }

    // Tambahkan getter-setter bila diperlukan
}
