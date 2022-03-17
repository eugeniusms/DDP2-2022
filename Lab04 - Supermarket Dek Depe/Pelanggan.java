import java.util.Arrays;

public class Pelanggan {
    // Merupakan kelas untuk mengatur hal-hal yang berkaitan dengan pelanggan
  
    // Inisiasi Data Field
    private String nama;
    private int uang;
    private Order[] keranjang;
    private int kapasitasKeranjang = 5000;

    // Membentuk constructor pelanggan
    public Pelanggan(String nama, int uang, int kapasitas) {
        this.nama = nama;
        this.uang = uang;
        this.kapasitasKeranjang = kapasitas;
        // Menginisiasikan isi order keranjang pengguna awal = kosong
        this.keranjang = new Order[]{};
    }
    
    // Method object untuk menambahkan barang dibeli ke dalam keranjang pelanggan
    public String addBarang(Barang barang, int banyakBarang){
        // Inisiasi berat barang yang dipilih
        int beratBarangDipilih = barang.getBeratBarang();
        int hargaBarangDipilih = barang.getHarga();

        // Saat stock tidak mencukupi
        if (barang.cekStock(banyakBarang) == false) {
            return ("Stock " + barang.getNama() + " kurang\n");
        } else if (this.kapasitasKeranjang < barang.getBeratBarang()*banyakBarang) {
            // Saat berat barang melebihi kapasitas keranjang

            // Cek barang yang dapat ditambahkan semaksimal mungkin
            int dapatDitambahkan = 1;
            while ((dapatDitambahkan * beratBarangDipilih) < this.kapasitasKeranjang) {
                dapatDitambahkan++;
            }
            dapatDitambahkan--;
            

            // Kurangi kapasitas keranjang pelanggan
            this.kapasitasKeranjang -= dapatDitambahkan*beratBarangDipilih;

            // Kurangi stok barang yang ada
            int stokBarang = barang.getStock();
            barang.setStock(stokBarang - banyakBarang);

            // Mengecek apakah sudah ada barang di dalam keranjang belum
            boolean barangSudahAda = false;
            for (Order ord: this.keranjang) {
                if (ord.getBarang().getNama().equals(barang.getNama())) {
                    // Jika sudah ada barang maka tambahkan saja barangnya
                    barangSudahAda = true;
                    int banyakBarangSebelum = ord.getBanyakBarang();
                    ord.setBanyakBarang(banyakBarangSebelum + dapatDitambahkan);
                }
            }
            // Jika belum ada maka inisiasikan object baru di dalam order
            if (barangSudahAda == false) {
                // Membuat objek baru dalam array
                Order orderBaru = new Order(barang, dapatDitambahkan);
                // Memasukkan barang ke keranjang
                // Tambahkan panjang array
                this.keranjang = Arrays.copyOf(this.keranjang, this.keranjang.length + 1);
                // Memasukkan barang berbentuk object ke indeks terakhir
                this.keranjang[this.keranjang.length - 1] = orderBaru;
            }

            // Mengembalikan nilai barang yang tidak bertambah penuh
            return ("Maaf " + banyakBarang + " " + barang.getNama() + " terlalu berat, "
                               + "tetapi " + dapatDitambahkan + " " + barang.getNama() + " berhasil ditambahkan\n");
        } else {
            // Saat barang dapat ditambahkan langsung karena tidak melebihi kapasitas

            // Kurangi kapasitas keranjang pelanggan
            this.kapasitasKeranjang -= banyakBarang*beratBarangDipilih;
            // Kurangi stok barang yang ada
            int stokBarang = barang.getStock();
            barang.setStock(stokBarang - banyakBarang);

            // Mengecek apakah barang sudah ada di dalam keranjang pelanggan atau belum
            boolean barangSudahAda = false;
            for (Order ord: this.keranjang) {
                if (ord.getBarang().getNama().equals(barang.getNama())) {
                    // Jika sudah ada maka tambahkan saja barang sesuai order
                    barangSudahAda = true;
                    int banyakBarangSebelum = ord.getBanyakBarang();
                    ord.setBanyakBarang(banyakBarangSebelum + banyakBarang);
                }
            }
            // Jika belum ada inisiasikan object order baru ke dalam keranjang
            if (barangSudahAda == false) {
                // Membuat objek baru dalam array
                Order orderBaru = new Order(barang, banyakBarang);
                // Memasukkan barang ke keranjang
                // Tambahkan panjang array
                this.keranjang = Arrays.copyOf(this.keranjang, this.keranjang.length + 1);
                // Memasukkan barang berbentuk object ke indeks terakhir
                this.keranjang[this.keranjang.length - 1] = orderBaru;
            }

            // Mengembalikan berhasil menambahkan barang
            return (this.getNama() + " berhasil menambahkan " + banyakBarang + " " + barang.getNama() + "\n");
        }
    }
    
    // Method ini digunakan untuk mendapatkan total harga barang
    public int totalHargaBarang(){     
        int totalHarga = 0; 
        // Mengambil total harga barang satu per satu dan mengembalikan total harganya
        for (Order ord: this.keranjang) {
            // Harga 1 barang * Jumlah barang
            totalHarga += ord.getBarang().getHarga() * (ord.getBanyakBarang());
        }
        return totalHarga;
    }
    
    // Method untuk mengembalikan uang yang ada di pelanggan
    public String cekUang(){
        // Mengembalikan uang pelanggan
        return Integer.toString(this.uang);
    }

    // Setter and Getter untuk class ini
    // Mengambil nama pelanggan
    public String getNama() {
        return this.nama;
    }

    // Mengeset nama pelanggan
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Mengambil uang yang ada di pelanggan
    public int getUang() {
        return this.uang;
    }

    // Mengeset uang pelanggan
    public void setUang(int uang) {
        this.uang = uang;
    }

    // Mengembalikan keranjang pelanggan
    public Order[] getKeranjang() {
        return keranjang;
    }

    // Reset keranjang pelanggan ke kosong
    public Order[] setKeranjangToZero() {
        this.keranjang = new Order[]{};
        return this.keranjang;
    }

}