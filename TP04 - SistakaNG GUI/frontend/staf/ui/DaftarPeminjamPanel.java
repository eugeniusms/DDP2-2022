package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Menyusun panel untuk daftar peminjam
public class DaftarPeminjamPanel extends SistakaPanel {

    // Menyusun panel dan komponen
    JPanel daftarPeminjamPanel = new JPanel(new BorderLayout());
    JPanel judulPanel = new JPanel(new GridLayout(3, 1));
    JPanel detailPanel = new JPanel(new GridLayout(1, 1));
    JPanel tombolPanel = new JPanel(new GridLayout(1, 2));

    JLabel daftarPeminjamLabel = new JLabel(main.judulHTML("Lihat Daftar Peminjam"));
    JLabel pilihBukuLabel = new JLabel("Pilih buku");
    static JLabel lihatDetailBukuLabel = new JLabel("- Anda belum memilih id -");

    // Menyusun drop down dari daftar id anggota
    JComboBox<String> bukuDropDown = new JComboBox<>();

    JButton lihatButton = new JButton(main.mainButtonHTML("Lihat"));
    JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

    public DaftarPeminjamPanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol kembali ketika ditekan
        lihatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // Mengambil id dalam drop down
                String bukuDipilih = bukuDropDown.getSelectedItem().toString();

                // Lakukan split untuk mendapatkan buku dan penulisnya dengan regex
                String[] splitted = bukuDipilih.split("\\s+"); // {<judul>, "oleh", <penulis>}
                String judulBuku = splitted[0];
                String penulisBuku = splitted[2];

                // Lakukan pencarian buku
                Buku buku = SistakaNG.findBuku(judulBuku, penulisBuku);

                // Mengatur hal yang ditampilkan pada label detail anggota
                lihatDetailBukuLabel.setText(SistakaNG.daftarPeminjam(buku));

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
        daftarPeminjamPanel.removeAll();
        
        // Mengatur Drop Down untuk id Anggota
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) bukuDropDown.getModel();
        model.removeAllElements();
        ArrayList<Buku> daftarBuku = SistakaNG.getDaftarBuku();
        for (Buku buku : daftarBuku) {
            model.addElement(buku.getJudul() + " oleh " + buku.getPenulis());
        }
        bukuDropDown.setModel(model);

        // Menyusun panel judul
        judulPanel.add(daftarPeminjamLabel);
        judulPanel.add(pilihBukuLabel);
        judulPanel.add(bukuDropDown);

        // Menyusun panel detail
        detailPanel.add(lihatDetailBukuLabel);

        // Menyusun panel tombol
        tombolPanel.add(lihatButton);
        tombolPanel.add(kembaliButton);

        daftarPeminjamPanel.add(judulPanel, BorderLayout.NORTH);
        daftarPeminjamPanel.add(detailPanel, BorderLayout.CENTER);
        daftarPeminjamPanel.add(tombolPanel, BorderLayout.SOUTH);

        // Menambahkan panel
        add(daftarPeminjamPanel);
    }
}
