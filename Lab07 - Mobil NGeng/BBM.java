public class BBM implements EngineBehaviour { // TODO: implementasikan sesuai UML diagram
    // TODO: tambahkan method-method yang diperlukan
    public String start(Mobil mobil) {
        return String.format("%s menyalakan mesin, NGENG!", mobil.getNama());
    }

    public int gas(int persenFuel) {
        return persenFuel-25;
    }

    public String stop(Mobil mobil) {
        return String.format("%s mesin mati, mobil istirahat dulu.", mobil.getNama());
    }
}