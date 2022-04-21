// Subclass jenis mobil terbang dari parent class Mobil
public class MobilTerbang extends Mobil{
    
    // Constructor mobil jenis "Terbang" yang diwariskan dari parent class
    public MobilTerbang(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        super(nama, engineBehaviour, bahanBakar, "Terbang");
    }

    // Method override isiBahanBakar (mengisi bahan bakar mobil)
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

    // Method override simulasi (menjalankan simulasi mobil berdasarkan jenisnya)
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