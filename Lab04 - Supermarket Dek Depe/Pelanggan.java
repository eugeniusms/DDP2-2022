public class Pelanggan {
  
    //TODO: Tambahkan modifier
    String nama;
    int uang;
    Order[] keranjang;
    int kapasitasKeranjang = 5000;

    //TODO: Buat Constructor
    public Pelanggan(String nama, int uang, int kapasitas) {

    }
    
    // TODO: lengkapi method di bawah ini
    String addBarang(Barang barang, int banyakBarang){
        return "";
    }
    
    // TODO: lengkapi method di bawah ini
    int totalHargaBarang(){      
        return 0;
    }
    
    // TODO: lengkapi method di bawah ini
    String cekUang(){
        return "";
    }

    // Setter and Getter dan lengkapi modifier
    String getNama() {
        return this.nama;
    }

    void setNama(String nama) {
        this.nama = nama;
    }

    int getUang() {
        return this.uang;
    }

    void setUang(int uang) {
        this.uang = uang;
    }

    Order[] getKeranjang() {
        return keranjang;
    }

}