package assignments.assignment4.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Kelas abstract untuk Sistaka Panel
public abstract class SistakaPanel extends JPanel {
    protected final HomeGUI main;

    // Constructor SistakaPanel
    public SistakaPanel(HomeGUI main) {
        this.main = main;
        setBorder(new EmptyBorder(10,10,10,10));
    }

    // Abstract method refresh
    public abstract void refresh();

    // Utility
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isDateValid(String tanggal){
        String[] arrOfTanggalLahir = tanggal.split("/");

        // Cek apakah ada 3 input (untuk dd, mm, dan yyyy)
        if (arrOfTanggalLahir.length != 3) {
            return false;
        }

        // Cek apakah semuanya numerik
        for (String s : arrOfTanggalLahir) {
            if (!isNumeric(s)) {
                return false;
            }
        }

        // Cek apakah jumlah digitnya memenuhi aturan (2 digit untuk tanggal dan bulan, serta 4 digit untuk year)
        if (arrOfTanggalLahir[0].length() != 2 || arrOfTanggalLahir[1].length() != 2 || arrOfTanggalLahir[2].length() != 4) {
            return false;
        }

        // Cek apakah memenuhi aturan tanggal (tanggal antara 1 - 31)
        int hari = Integer.parseInt(arrOfTanggalLahir[0]);
        if (hari < 1 || hari > 31) {
            return false;
        }

        // Cek apakah memenuhi aturan bulan (bulan antara 1 - 12)
        int bulan = Integer.parseInt(arrOfTanggalLahir[1]);
        return bulan >= 1 && bulan <= 12;
    }
}
