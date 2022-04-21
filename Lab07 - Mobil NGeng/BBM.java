// BBM implements dari EngineBehaviour Interface
public class BBM implements EngineBehaviour { 

    // Default start method untuk mobil dengan bahan bakar BBM
    public String start(Mobil mobil) {
        return String.format("%s menyalakan mesin, NGENG!", mobil.getNama());
    }

    // Default gas method untuk mobil dengan bahan bakar BBM
    public int gas(int persenFuel) {
        return persenFuel-25;

    }
    // Default stop method untuk mobil dengan bahan bakar BBM
    public String stop(Mobil mobil) {
        return String.format("%s mesin mati, mobil istirahat dulu.", mobil.getNama());
    }
}