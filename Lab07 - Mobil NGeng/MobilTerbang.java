public class MobilTerbang extends Mobil{ //TODO: impelementasikan sesuai UML diagram
    
    public MobilTerbang(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        // TODO: Lengkapi Constructor berikut
        super(nama, engineBehaviour, bahanBakar, "Terbang");
    }

    // TODO: Lengkapi method ini
    @Override
    public String isiBahanBakar(){
        // Saat mobil sudah mati maka dapat diisi bahan bakarnya
        if (this.getIsOn() == false) {
            // Set bahan bakar penuh kembali
            super.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat terbang kembali!", this.getBahanBakar());
        } else {
            // Saat mobil masih hidup tidak dapat diisi bahan bakarnya
            return "Mobil masih terbang, matikan terlebih dahulu agar tidak jatuh.";
        }
    }

    // TODO: Lengkapi method ini
    @Override
    public String[] simulasi(){
        String[] langkah = new String[5];
        // Simulasi mobil dengan start -> 2x gas -> stop -> isi bahan bakar (7 langkah)
        langkah[0] = super.start(); // 1x start
        for (int i = 1; i <= 2; i++) { // 2x gas
            langkah[i] = super.gas(); 
        } 
        langkah[3] = super.stop(); // 1x stop
        langkah[4] = this.isiBahanBakar(); // 1x isi bahan bakar
        return langkah;
    }
  
}