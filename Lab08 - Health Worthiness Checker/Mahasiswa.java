// Kelas ini merupakan kelas Mahasiswa
public class Mahasiswa {
    // Data field dalam kelas 
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;
    private String nama;
    private int tingkatKesehatan;

    // Constructor Mahasiswa
    public Mahasiswa(String nama, int tingkatKesehatan) {
        this.nama = nama;
        this.tingkatKesehatan = tingkatKesehatan;
    }

    // Method untuk mendapatkan status dari mahasiswa
    public String getStatus() {
        // Saat tingkatKesehatan lebih atau sama dengan MINIMUM_TINGKAT_KESEHATAN maka layak
        if (this.tingkatKesehatan >= MINIMUM_TINGKAT_KESEHATAN) {
            return "Layak mengikuti program";
        } else {
            // Setidaknya maka tidak layak
            return "Tidak layak mengikuti program";
        }
        
    }

    // Untuk mencetak sebagai output di file
    @Override
    public String toString() {
        return String.format("%-24s| %s\n", this.nama, this.getStatus());
    }

    // Getter and Setter
    public String getNama() {
        return this.nama;
    }

    public int getTingkatKesehatan() {
        return this.tingkatKesehatan;
    }
}
