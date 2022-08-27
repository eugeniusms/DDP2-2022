package assignments.assignment4.frontend;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

// Kelas panel welcome
public class WelcomePanel extends SistakaPanel {

    public WelcomePanel(HomeGUI homeGUI) {
        super(homeGUI);

        // Inisiasi beberapa komponen
        JPanel welPanel = new JPanel(new GridLayout(11, 1));

        JLabel welcomeLabel = new JLabel(homeGUI.judulHTML("Welcome to SistakaNG!"));
        JLabel wordsLabel = new JLabel("<html><p align='center'>We live in a digital age and thrive on personal <br> connectivity. This is reflected in the availability <br> of our information resources.</p></html>");
        JLabel copyrightsLabel = new JLabel("Eugenius Mario @2022");

        JButton loginButton = new JButton("<html><b>Login</b></html>");
        JButton exitButton = new JButton("<html><p style='color:#990000'>Exit</p></html>");

        // Set horizontal aligment untuk copyrights
        copyrightsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Menambahkan komponen ke panel 
        welPanel.add(new JLabel()); // DUMMIE
        welPanel.add(new JLabel()); // DUMMIE
        welPanel.add(new JLabel()); // DUMMIE
        welPanel.add(welcomeLabel);
        welPanel.add(new JLabel()); // DUMMIE
        welPanel.add(wordsLabel); 
        welPanel.add(new JLabel()); // DUMMIE
        welPanel.add(loginButton);
        welPanel.add(exitButton);
        welPanel.add(new JLabel()); // DUMMIE
        welPanel.add(copyrightsLabel);

        // Menambahkan panel ke panel utama
        add(welPanel);

        // Action listener untuk tombol login ketika ditekan
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // Set panel ke login
                homeGUI.setPanel("login");
            }
        });

        // Action listener untuk tombol exit ketika ditekan
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // Memunculkan pesan
                JOptionPane.showMessageDialog(main.getFrame(),
                "Terima kasih telah menggunakan layanan kami!",
                "SistakaNG",
                JOptionPane.INFORMATION_MESSAGE);

                // Menghentikan eksekusi program
                System.exit(0);
            }
        });
    }

    @Override
    public void refresh() {
        // ignored
    }
}
