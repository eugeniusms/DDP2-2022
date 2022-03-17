public class Barang {
  
    //TODO: Tambahkan modifier (CLEAR)
    private String nama;
    private int harga;
    private int beratBarang;
    private int stock;

    //TODO: Buat Constructor (CLEAR)
    public Barang(String nama, int harga, int beratBarang, int stock) {
        this.nama = nama;
        this.harga = harga;
        this.beratBarang = beratBarang;
        this.stock = stock;
    }
      
    //TODO: Silakan cek stock (CLEAR)
    boolean cekStock(int stock){
        // Saat stock kurang maka barang tidak akan bisa diambil
        if (this.stock < stock) {
            return false;
        }
        return true;    
    }
    
    String getNama() {
        return nama;
    }
    
    int getStock(){
        return stock;
    }
  
    void setStock(int kuantitas){
        this.stock = kuantitas;
    }
    
    int getBeratBarang(){
        return beratBarang;
    }

    int getHarga(){
        return harga;
    }
    
}
