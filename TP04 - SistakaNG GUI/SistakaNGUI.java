package assignments.assignment4;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.*;

import javax.swing.*;

// Kelas utama program berjalan
public class SistakaNGUI {
    public static void main(String[] args) {
        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SistakaNG");
        SistakaNG.registerStaf();

        // Memanggil welcome panel dan memulai GUI
        HomeGUI homepage = new HomeGUI(frame);
        new WelcomePanel(homepage);

        frame.setVisible(true);
    }

}
