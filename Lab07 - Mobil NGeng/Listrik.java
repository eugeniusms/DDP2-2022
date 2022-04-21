// Listrik implements dari EngineBehaviour Interface
public class Listrik implements EngineBehaviour { 

    // Default start method untuk mobil dengan daya listrik
    public String start(Mobil mobil) {
        return String.format("%s menyalakan listrik, SIAP DIGAS!", mobil.getNama());
    }

    // Default gas method untuk mobil dengan daya listrik
    public int gas(int persenFuel) {
        return persenFuel-20;
    }

    // Default stop method untuk mobil dengan daya listrik
    public String stop(Mobil mobil) {
        return String.format("%s listrik dimatikan, mobil telah berhenti.", mobil.getNama());
    }
}