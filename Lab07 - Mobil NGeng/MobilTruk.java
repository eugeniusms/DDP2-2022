public class MobilTruk extends Mobil{ //TODO: impelementasikan sesuai UML diagram

    public MobilTruk(String nama, EngineBehaviour engineBehaviour, String bahanBakar) {
        // TODO: Lengkapi Constructor berikut
        super(nama, engineBehaviour, bahanBakar);
    }

    // TODO: Lengkapi method ini
    @Override
    public String isiBahanBakar(){
        // Saat mobil sudah mati maka dapat diisi bahan bakarnya
        if (this.getIsOn().equals(false)) {
            // Set bahan bakar penuh kembali
            super.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat digaskeun kembali!", this.getBahanBakar());
        } else {
            // Saat mobil masih hidup tidak dapat diisi bahan bakarnya
            return String.format("Mobil masih menyala, matikan terlebih dahulu agar tidak meledak.", this.getBahanBakar());
        }
    }

    // TODO: Lengkapi method ini
    @Override
    public String[] simulasi(){
        String[] langkah = new String[7];
        // Simulasi mobil dengan start -> 4x gas -> stop -> isi bahan bakar (7 langkah)
        langkah[0] = super.start(); // 1x start
        for (int i = 1; i <= 4; i++) { // 4x gas
            langkah[i] = super.gas(); 
        } 
        langkah[6] = super.stop(); // 1x stop
        langkah[7] = this.isiBahanBakar(); // 1x isi bahan bakar
        return langkah;
    }


}