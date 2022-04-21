public class MobilAir extends Mobil { //TODO: impelementasikan sesuai UML diagram
  
    public MobilAir(String nama, EngineBehaviour engineBehaviour, String bahanBakar){
        // TODO: Lengkapi constructor berikut
        super(nama, engineBehaviour, bahanBakar, jenis);
    }

    // TODO: Lengkapi method ini
    @Override
    public String isiBahanBakar(){
        // Saat mobil sudah mati maka dapat diisi bahan bakarnya
        if (this.getIsOn().equals(false)) {
            // Set bahan bakar penuh kembali
            super.setPersenFuel(100);
            return String.format("%s sekarang sudah penuh, mobil dapat digunakan kembali!", this.getBahanBakar());
        } else {
            // Saat mobil masih hidup tidak dapat diisi bahan bakarnya
            return String.format("Mobil masih menyala, matikan terlebih dahulu agar tidak tenggelam.", this.getBahanBakar());
        }
    }

    // TODO: Lengkapi method ini
    @Override
    public String[] simulasi(){
        // Simulasi mobil dengan start -> 5x gas -> stop -> isi bahan bakar
        super.start(); // 1x start
        for (int i = 0; i < 5; i++) { // 5x gas
            super.gas(); 
        } 
        super.stop(); // 1x stop
        this.isiBahanBakar(); // 1x isi bahan bakar
        return null;
    }   
}