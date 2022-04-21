// Subclass jenis mobil truk dari parent class Mobil
public class MobilTruk extends Mobil{ 

    // Constructor mobil jenis "Truk" yang diwariskan dari parent class
    public MobilTruk(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        super(nama, engineBehaviour, bahanBakar, "Truk");
    }

    // Method override isiBahanBakar (mengisi bahan bakar mobil)
    @Override
    public String isiBahanBakar(){
        // Saat mobil sudah mati maka dapat diisi bahan bakarnya
        if (this.getIsOn() == false) {
            // Set bahan bakar penuh kembali
            super.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat digaskeun kembali!", this.getBahanBakar());
        } else {
            // Saat mobil masih hidup tidak dapat diisi bahan bakarnya
            return "Mobil masih menyala, matikan terlebih dahulu agar tidak meledak.";
        }
    }

    // Method override simulasi (menjalankan simulasi mobil berdasarkan jenisnya)
    @Override
    public String[] simulasi(){
        String[] langkah = new String[7];
        // Simulasi mobil dengan start -> 4x gas -> stop -> isi bahan bakar (7 langkah)
        langkah[0] = super.start(); // 1x start
        for (int i = 1; i <= 4; i++) { // 4x gas
            langkah[i] = super.gas(); 
        } 
        langkah[5] = super.stop(); // 1x stop
        langkah[6] = this.isiBahanBakar(); // 1x isi bahan bakar
        return langkah;
    }


}