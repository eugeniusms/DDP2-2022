public class Order {
    // Merupakan kelas yang mengatur order barang pelanggan
    // Inisiasi data field order
    private Barang barang;
    private int banyakBarang;

    // Constructor dari order
    public Order(Barang barang, int banyakBarang) {
        this.barang = barang;
        this.banyakBarang = banyakBarang;
    }

    // Method untuk mendapatkan object barang
    public Barang getBarang() {
        return barang;
    }

    // Method untuk mengembalikan banyak barang dibeli
    public int getBanyakBarang(){
        return banyakBarang;
    }

    // Method untuk mengeset barang order
    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    // Method untuk mengeset banyak barang diorder
    public void setBanyakBarang(int banyakBarang){
        this.banyakBarang = banyakBarang;
    }
}