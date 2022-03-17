public class Barang {
  
    //TODO: Tambahkan modifier
    String nama;
    int harga;
    int beratBarang;
    int stock;

    //TODO: Buat Constructor
    public Barang(String nama, int harga, int beratBarang, int stock) {

    }
      
    //TODO: Silakan cek stock
    boolean cekStock(int stock){
        return false;    
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
