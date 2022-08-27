package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.pengguna.Pengguna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk menyusun panel home anggota
public class AnggotaHomePanel extends SistakaPanel {

    // Menyusun panel dan komponen
    JPanel angPanel = new JPanel(new GridLayout(11, 1));

    JButton peminjamanButton = new JButton("Peminjaman");
    JButton pengembalianButton = new JButton("Pengembalian");
    JButton pembayaranDendaButton = new JButton("Pembayaran Denda");
    JButton detailAnggotaButton = new JButton("Detail Anggota");;
    JButton logoutButton = new JButton(main.kembaliButtonHTML("Logout"));

    public AnggotaHomePanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol peminjaman ketika ditekan
        peminjamanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peminjaman");
            }
        });

        // Action listener untuk tombol pengembalian ketika ditekan
        pengembalianButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pengembalian");
            }
        });

        // Action listener untuk tombol pembayaran ketika ditekan
        pembayaranDendaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("pembayaran");
            }
        });

        // Action listener untuk tombol detail anggota ketika ditekan
        detailAnggotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailUser");
            }
        });


        // Action listener untuk tombol logout ketika ditekan
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("login");
            }
        });
    }

    @Override
    public void refresh() {
        // Clear reset angLabel dulu
        angPanel.removeAll();

        // Tampilkan nama pengguna
        Pengguna user = main.getUser();
        JLabel anggotaLabel = new JLabel(main.judulHTML("Selamat datang kembali " + user.getNama() + "!"));

        // Menambahkan komponen ke panel
        angPanel.add(new JLabel());
        angPanel.add(new JLabel());
        angPanel.add(new JLabel());
        angPanel.add(anggotaLabel);
        angPanel.add(new JLabel());
        angPanel.add(peminjamanButton);
        angPanel.add(pengembalianButton);
        angPanel.add(pembayaranDendaButton);
        angPanel.add(detailAnggotaButton);
        angPanel.add(new JLabel());
        angPanel.add(logoutButton);

        // Menambahkan panel
        add(angPanel, BorderLayout.NORTH);
    }

}
