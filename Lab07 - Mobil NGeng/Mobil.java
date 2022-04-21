abstract class Mobil {
    private String nama;
    private int persenFuel;
    private String bahanBakar;
    private String jenis;
    private EngineBehaviour engineBehaviour;
    private boolean isOn;

    protected Mobil (String nama, EngineBehaviour engineBehaviour, String bahanBakar, String jenis){
        // TODO: Lengkapi constructor berikut
        this.nama = nama;
        this.engineBehaviour = engineBehaviour;
        this.bahanBakar = bahanBakar;
        this.jenis = jenis;
        // Default data field
        this.persenFuel = 100;
        this.isOn = false;
    }

    // TODO: Lengkapi method ini // RETURN APA? # CHECKME
    public String start(){
        // Menghidupkan mobil 
        this.isOn = true; 
        return this.engineBehaviour.start(this);
    }

    // TODO: Lengkapi method ini
    public String gas(){
        // Method hanya berjalan ketika mobil menyala
        if (this.isOn == true) {
            // Mobil hanya akan digas saat bahan bakar tidak habis
            if (this.persenFuel > 0) {
                String N, Y;
                String Z = "";
                int X;
                // Mendapatkan permukaan kendara jenis mobil 
                if (this.jenis.equals("Air")) {
                    Z = "Air";
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
                X = this.engineBehaviour.gas(this.persenFuel);

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
  
    // TODO: Lengkapi method ini // RETURN APA? # CHECKME
    public String stop(){
        this.isOn = false;
        return this.engineBehaviour.stop(this);
    }
  
    public abstract String isiBahanBakar();
    public abstract String[] simulasi();

    // Getter & Setter
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