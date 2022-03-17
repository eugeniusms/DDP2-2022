public class Barang {
    // Merupakan kelas untuk menyimpan data mengenai barang di dalam toko
  
    // Inisiasi data field
    private String nama;
    private int harga;
    private int beratBarang;
    private int stock;

    // Constructor untuk object barang
    public Barang(String nama, int harga, int beratBarang, int stock) {
        this.nama = nama;
        this.harga = harga;
        this.beratBarang = beratBarang;
        this.stock = stock;
    }
      
    // Method untuk mengetahui apakah stok barang mencukupi atau tidak
    public boolean cekStock(int stock){
        // Saat stock kurang maka barang tidak akan bisa diambil
        if (this.stock < stock) {
            return false;
        }
        return true;    
    }
    
    // Getter untuk mendapat nama barang
    public String getNama() {
        return nama;
    }
    
    // Getter untuk mendapat jumlah stok
    public int getStock(){
        return stock;
    }
  
    // Setter untuk mengeset stock
    public void setStock(int kuantitas){
        this.stock = kuantitas;
    }
    
    // Getter untuk mendapat berat barang
    public int getBeratBarang(){
        return beratBarang;
    }

    // Getter untuk mendapat harga barang
    public int getHarga(){
        return harga;
    }
    
}
