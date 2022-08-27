package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.pengguna.Pengguna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Menyusun panel pembayaran
public class PembayaranPanel extends SistakaPanel {
    // Menyusun panel dan komponen
    JPanel pembayaranPanel = new JPanel(new GridLayout(11,1));

    JLabel bayarDendaLabel = new JLabel(main.judulHTML("Bayar Denda"));
    JLabel jumlahBayarLabel = new JLabel("Jumlah Denda");

    JTextArea bayarTextArea = new JTextArea();

    JButton bayarButton = new JButton(main.mainButtonHTML("Bayar"));
    JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

    public PembayaranPanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol bayar ketika ditekan
        bayarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                // Mengambil jumlah denda
                String dendaString = bayarTextArea.getText();

                try {
                    long denda = Long.valueOf(dendaString);
                    // Membayar denda
                    bayar(denda);
                } catch (Exception r) {
                    // Saat masukan bukan angka
                    // Message
                    JOptionPane.showMessageDialog(main.getFrame(),
                        "Jumlah bayar harus berupa angka!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                }
                
            }
        });

        // Action listener untuk tombol kembali ketika ditekan
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("anggota");
            }
        });

    }

    @Override
    public void refresh() {
        // Clear reset staLabel dulu
        pembayaranPanel.removeAll();

        // Menambahkan components
        pembayaranPanel.add(new JLabel());
        pembayaranPanel.add(new JLabel());
        pembayaranPanel.add(new JLabel());
        pembayaranPanel.add(new JLabel());
        pembayaranPanel.add(bayarDendaLabel);
        pembayaranPanel.add(new JLabel());
        pembayaranPanel.add(jumlahBayarLabel);
        pembayaranPanel.add(bayarTextArea);
        pembayaranPanel.add(new JLabel());
        pembayaranPanel.add(bayarButton);
        pembayaranPanel.add(kembaliButton);

        // Menambahkan panel
        add(pembayaranPanel, BorderLayout.NORTH);
    }

    // Method untuk membayar denda
    public void bayar(long denda) {
        // Mengambil data pengguna
        Pengguna user = main.getUser();
        // Membayar denda
        String pesan = user.bayarDenda(denda);

        // Message
        JOptionPane.showMessageDialog(main.getFrame(),
            String.format(pesan),
            "Info",
        JOptionPane.INFORMATION_MESSAGE);
    }
}
