public class Order {
    private Barang barang;
    private int banyakBarang;

    public Order(Barang barang, int banyakBarang) {
        this.barang = barang;
        this.banyakBarang = banyakBarang;
    }

    Barang getBarang() {
        return barang;
    }

    int getBanyakBarang(){
        return banyakBarang;
    }

    void setBarang(Barang barang) {
        this.barang = barang;
    }

    void setBanyakBarang(int banyakBarang){
        this.banyakBarang = banyakBarang;
    }
}