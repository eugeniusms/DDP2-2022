package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Menanyusun panel detail user
public class DetailUserPanel extends SistakaPanel {
    // Menyusun komponen dan panel
    JPanel detailUserPanel = new JPanel(new BorderLayout());

    JLabel lihatDetailAnggotaLabel = new JLabel(main.judulHTML("Lihat Detail Anggota"));

    JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

    public DetailUserPanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol kembali ketika ditekan
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("anggota");
            }
        });
    }

    @Override
    public void refresh() {
        // Reset clear panel dulu
        detailUserPanel.removeAll();

        // Mengambil data user
        Pengguna user = main.getUser();
        JLabel detailUserLabel = new JLabel(user.detail());

        // Menambahkan komponen ke panel
        detailUserPanel.add(lihatDetailAnggotaLabel, BorderLayout.NORTH);
        detailUserPanel.add(detailUserLabel, BorderLayout.CENTER);
        detailUserPanel.add(kembaliButton, BorderLayout.SOUTH);

        // Menambahkan panel
        add(detailUserPanel);
    }
}
