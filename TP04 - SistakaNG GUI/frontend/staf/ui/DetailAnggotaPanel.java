package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Anggota;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Kelas untuk menyusun panel detail anggota
public class DetailAnggotaPanel extends SistakaPanel {
    // Menyusun panel dan komponen
    JPanel peringkatPanel = new JPanel(new BorderLayout());
    JPanel judulPanel = new JPanel(new GridLayout(3, 1));
    JPanel detailPanel = new JPanel(new GridLayout(1, 1));
    JPanel tombolPanel = new JPanel(new GridLayout(1, 2));

    JLabel detailAnggotaLabel = new JLabel(main.judulHTML("Lihat Detail Anggota"));
    JLabel pilihIdAnggotaLabel = new JLabel("Pilih id Anggota");
    static JLabel lihatDetailAnggotaLabel = new JLabel("- Anda belum memilih id -");

    // Menyusun drop down dari daftar id anggota
    JComboBox<String> idAnggotaDropDown = new JComboBox<>();

    JButton lihatButton = new JButton(main.mainButtonHTML("Lihat"));
    JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

    public DetailAnggotaPanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol kembali ketika ditekan
        lihatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // Mengambil id dalam drop down
                String id = idAnggotaDropDown.getSelectedItem().toString();
                // Mengatur hal yang ditampilkan pada label detail anggota
                lihatDetailAnggotaLabel.setText(SistakaNG.findAnggota(id).detail());

            }
        });

        // Action listener untuk tombol kembali ketika ditekan
        kembaliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        // Reset clear isi panel terlebih dahulu
        judulPanel.removeAll();
        detailPanel.removeAll();
        tombolPanel.removeAll();
        peringkatPanel.removeAll();
        
        // Mengatur Drop Down untuk id Anggota
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) idAnggotaDropDown.getModel();
        model.removeAllElements();
        ArrayList<Anggota> daftarAnggota = SistakaNG.getDaftarAnggota();
        for (Anggota anggota : daftarAnggota) {
            model.addElement(anggota.getId());
        }
        idAnggotaDropDown.setModel(model);

        // Menyusun panel judul
        judulPanel.add(detailAnggotaLabel);
        judulPanel.add(pilihIdAnggotaLabel);
        judulPanel.add(idAnggotaDropDown);

        // Menyusun panel detail
        detailPanel.add(lihatDetailAnggotaLabel);

        // Menyusun panel tombol
        tombolPanel.add(lihatButton);
        tombolPanel.add(kembaliButton);

        peringkatPanel.add(judulPanel, BorderLayout.NORTH);
        peringkatPanel.add(detailPanel, BorderLayout.CENTER);
        peringkatPanel.add(tombolPanel, BorderLayout.SOUTH);

        // Menambahkan panel
        add(peringkatPanel);
    }
}   
