// Class abstrak sebuah mobil
abstract class Mobil {

    // Terdapat beberapa data field private untuk sebuah mobil sebagai berikut
    private String nama;
    private int persenFuel;
    private String bahanBakar;
    private String jenis;
    private EngineBehaviour engineBehaviour;
    private boolean isOn;

    // Constructor sebuah mobil visibilitynya protected, dengan beberapa field termasuk default field yaitu 
    // 1. persenFuel = 100 (bahan bakar penuh)
    // 2. isOn = false (mobil mati)
    protected Mobil (String nama, EngineBehaviour engineBehaviour, String bahanBakar, String jenis){
        this.nama = nama;
        this.engineBehaviour = engineBehaviour;
        this.bahanBakar = bahanBakar;
        this.jenis = jenis;
        // Default data field
        this.persenFuel = 100;
        this.isOn = false;
    }

    // Method untuk start (memulai penghidupan mesin)
    public String start(){
        // Menghidupkan mobil 
        this.isOn = true; 
        return this.engineBehaviour.start(this);
    }

    // Method untuk gas (menjalankan sebuah mesin)
    public String gas(){
        // Method hanya berjalan ketika mobil menyala
        if (this.isOn == true) {
            // Mobil hanya akan digas saat bahan bakar tidak habis
            if (this.persenFuel > 0) {
                // Deklarasi variabel yang akan digunakan
                String N, Y;
                String Z = "";
                int X;

                // Mendapatkan permukaan kendara jenis mobil 
                if (this.jenis.equals("Air")) {
                    Z = "Laut";
                } else if (this.jenis.equals("Terbang")) {
                    Z = "Langit";
                } else if (this.jenis.equals("Truk")) {
                    Z = "Jalan Raya";
                }

                // Mendapatkan nama mobil
                N = this.getNama();
                // Mendapatkan jenis bahan bakar mobil
                Y = this.bahanBakar;
                // Mendapatkan sisa bahan bakar
                this.persenFuel = this.engineBehaviour.gas(this.persenFuel);
                X = this.persenFuel;

                return String.format("%s digas dengan cepat di %s! Bahan bakar mobil %s sekarang %d%%.", N, Z, Y, X);
            } else {
                // Saat bahan bakar habis // RETURN LISTRIK ATAU TETAP BENSIN? # CHECKME
                return "Bensin habis!";
            }
        } else {
            // Saat mobil masih belum menyala
            return "Nyalakan mobil dulu!";
        }
    }
  
    // Method untuk stop (menghentikan mesin)
    public String stop(){
        this.isOn = false;
        return this.engineBehaviour.stop(this);
    }
  
    // Method abstrak isiBahanBakar dan simulasi
    public abstract String isiBahanBakar();
    public abstract String[] simulasi();

    // Berikut adalah Getter & Setter
    public String getNama() {
        return nama; 
    }

    public int getPersenFuel() {
        return persenFuel; 
    }

    public void setPersenFuel(int persenFuel) {
        this.persenFuel = persenFuel;
    }

    public String getBahanBakar() {
        return bahanBakar; 
    }
  
    public boolean getIsOn() {
        return isOn; 
    }

}