abstract class Mobil {
    private String nama;
    private int persenFuel;
    private String bahanBakar;
    private String jenis;
    private EngineBehaviour engineBehaviour;
    private boolean isOn;

    protected Mobil (String nama, EngineBehaviour engineBehaviour, String bahanBakar, String jenis){
        // TODO: Lengkapi constructor berikut
    }

    // TODO: Lengkapi method ini
    public String start(){
        return "";
    }

    // TODO: Lengkapi method ini
    public String gas(){
        return "";
    }
  
    // TODO: Lengkapi method ini
    public String stop(){
        return "";
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