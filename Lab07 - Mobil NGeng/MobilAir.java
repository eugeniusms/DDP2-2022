// Subclass jenis mobil air dari parent class Mobil
public class MobilAir extends Mobil { 
  
    // Constructor mobil jenis "Air" yang diwariskan dari parent class
    public MobilAir(String nama, EngineBehaviour engineBehaviour, String bahanBakar){
        super(nama, engineBehaviour, bahanBakar, "Air");
    }

    // Method override isiBahanBakar (mengisi bahan bakar mobil)
    @Override
    public String isiBahanBakar(){
        // Saat mobil sudah mati maka dapat diisi bahan bakarnya
        if (this.getIsOn() == false) {
            // Set bahan bakar penuh kembali
            super.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat digunakan kembali!", this.getBahanBakar());
        } else {
            // Saat mobil masih hidup tidak dapat diisi bahan bakarnya
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak tenggelam.";
        }
    }

    // Method override simulasi (menjalankan simulasi mobil berdasarkan jenisnya)
    @Override
    public String[] simulasi(){
        String[] langkah = new String[8];
        // Simulasi mobil dengan start -> 5x gas -> stop -> isi bahan bakar (8 langkah)
        langkah[0] = super.start(); // 1x start
        for (int i = 1; i <= 5; i++) { // 5x gas
            langkah[i] = super.gas(); 
        } 
        langkah[6] = super.stop(); // 1x stop
        langkah[7] = this.isiBahanBakar(); // 1x isi bahan bakar
        return langkah;
    }   
}