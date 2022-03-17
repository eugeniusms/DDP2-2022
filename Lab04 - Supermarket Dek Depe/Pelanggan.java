import java.util.Arrays;

public class Pelanggan {
  
    //TODO: Tambahkan modifier (CLEAR)
    private String nama;
    private int uang;
    private Order[] keranjang;
    private int kapasitasKeranjang = 5000;

    //TODO: Buat Constructor (CLEAR) *KERANJANG? AMAN?
    public Pelanggan(String nama, int uang, int kapasitas) {
        this.nama = nama;
        this.uang = uang;
        this.kapasitasKeranjang = kapasitas;
        // Menginisiasikan isi order keranjang pengguna awal = kosong
        this.keranjang = new Order[]{};
    }
    
    // TODO: lengkapi method di bawah ini (CLEAR) * KURANG CEK SUDAH ADA BARANG SAMA BELUM
    String addBarang(Barang barang, int banyakBarang){
        // Inisiasi berat barang yang dipilih
        int beratBarangDipilih = barang.getBeratBarang();

        // Saat stock tidak mencukupi
        if (barang.cekStock(banyakBarang) == false) {
            return ("Stock " + barang.getNama() + " kurang\n");
        } else if (this.kapasitasKeranjang < barang.getBeratBarang()*banyakBarang) {
            // Saat berat barang melebihi kapasitas keranjang
            // Cek barang yang dapat ditambahkan 
            int dapatDitambahkan = 1;
            while ((dapatDitambahkan * beratBarangDipilih) < this.kapasitasKeranjang) {
                dapatDitambahkan++;
            }
            dapatDitambahkan--;

            // SEHARUSNYA DI SINI DI CEK APAKAH SUDAH ADA BARANG SAMA BELUM ------------------------------------------
            // KURANGI UANG PELANGGAN
            // KURANGI KAPASITAS KERANJANG YG ADA
            this.kapasitasKeranjang -= dapatDitambahkan*beratBarangDipilih;
            // KURANGI STOCK BARANG YANG ADA
            int stokBarang = barang.getStock();
            barang.setStock(stokBarang - banyakBarang);
            // * TAMBAHKAN BATAS MAKSIMAL DAPAT DIBELI

            // Membuat objek baru dalam array
            Order orderBaru = new Order(barang, dapatDitambahkan);
            // Memasukkan barang ke keranjang
            // Tambahkan panjang array
            this.keranjang = Arrays.copyOf(this.keranjang, this.keranjang.length + 1);
            // Memasukkan barang berbentuk object ke indeks terakhir
            this.keranjang[this.keranjang.length - 1] = orderBaru;

            return ("Maaf " + banyakBarang + " " + barang.getNama() + " terlalu berat, "
                               + "tetapi " + dapatDitambahkan + " " + barang.getNama() + " berhasil ditambahkan\n");
        } else {
            // Saat barang dapat ditambahkan langsung karena tidak melebihi kapasitas
            // Cek barang yang dapat ditambahkan 
            // SEHARUSNYA DI SINI DI CEK APAKAH SUDAH ADA BARANG SAMA BELUM ------------------------------------------
            // KURANGI UANG PELANGGAN
            // KURANGI KAPASITAS KERANJANG YG ADA
            this.kapasitasKeranjang -= banyakBarang*beratBarangDipilih;
            // KURANGI STOCK BARANG YANG ADA
            int stokBarang = barang.getStock();
            barang.setStock(stokBarang - banyakBarang);

            // Membuat objek baru dalam array
            Order orderBaru = new Order(barang, banyakBarang);

            // Memasukkan barang ke keranjang
            // Tambahkan panjang array
            this.keranjang = Arrays.copyOf(this.keranjang, this.keranjang.length + 1);
            // Memasukkan barang berbentuk object ke indeks terakhir
            this.keranjang[this.keranjang.length - 1] = orderBaru;

            return (this.getNama() + " berhasil menambahkan " + banyakBarang + " " + barang.getNama() + "\n");
        }
    }
    
    // TODO: lengkapi method di bawah ini (CLEAR) ? CEK
    int totalHargaBarang(){     
        int totalHarga = 0; 
        // Mengambil total harga barang dan mengembalikan total harganya
        for (int i = 0; i < this.keranjang.length - 1; i++) {
            totalHarga += this.keranjang[i].getBarang().getHarga();
        }
        return totalHarga;
    }
    
    // TODO: lengkapi method di bawah ini (CLEAR)
    String cekUang(){
        // Mengembalikan uang pelanggan
        return Integer.toString(this.uang);
    }

    // Setter and Getter dan lengkapi modifier (CLEAR)
    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUang() {
        return this.uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public Order[] getKeranjang() {
        return keranjang;
    }

}