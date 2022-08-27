package assignments.assignment4.frontend.anggota.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.pengguna.Pengguna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Menyusun panel peminjaman
public class PeminjamanPanel extends SistakaPanel {
    // Menyusun drop down dari daftar kategori
    JComboBox<String> bukuDropDown = new JComboBox<>();

    public PeminjamanPanel(HomeGUI main) {
        super(main);

        // Menyusun panel dan komponen
        JFrame frame = main.getFrame();

        JPanel pinjamBukuPanel = new JPanel(new GridLayout(12, 1));

        JLabel pinjamBukuLabel = new JLabel(main.judulHTML("Pinjam Buku"));
        JLabel bukuLabel = new JLabel("Buku");
        JLabel tanggalPeminjamanLabel = new JLabel("Tanggal Peminjaman (DD/MM/YYYY)");
    
        JTextField tanggalPeminjamanTextField = new JTextField();

        JButton pinjamButton = new JButton(main.mainButtonHTML("Pinjam"));
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Set align
        pinjamBukuLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Menambahkan komponen ke panel
        pinjamBukuPanel.add(new JLabel());
        pinjamBukuPanel.add(new JLabel());
        pinjamBukuPanel.add(new JLabel());
        pinjamBukuPanel.add(pinjamBukuLabel);
        pinjamBukuPanel.add(new JLabel());
        pinjamBukuPanel.add(bukuLabel);
        pinjamBukuPanel.add(bukuDropDown);
        pinjamBukuPanel.add(tanggalPeminjamanLabel);
        pinjamBukuPanel.add(tanggalPeminjamanTextField);
        pinjamBukuPanel.add(new JLabel());
        pinjamBukuPanel.add(pinjamButton);
        pinjamBukuPanel.add(kembaliButton);

        // Menambahkan panel
        add(pinjamBukuPanel, BorderLayout.NORTH);

        // Action listener untuk tombol pinjam ketika ditekan
        pinjamButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                // Lakukan pengecekan validasi masukan
                String tanggal = tanggalPeminjamanTextField.getText();
                if (SistakaNG.isTanggalValid(tanggal) == false) {
                    // Saat tanggal tidak valid maka keluarkan pesan
                    JOptionPane.showMessageDialog(frame,
                        "Tanggal yang dimasukkan harus dalam format DD/MM/YYYY!",
                        "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    // Ambil data buku
                    // Mengambil masukan pengguna
                    String bukuDipilih = bukuDropDown.getSelectedItem().toString();
                    // Lakukan split untuk mendapatkan buku dan penulisnya dengan regex
                    String[] splitted = bukuDipilih.split("\\s+"); // {<judul>, "oleh", <penulis>}
                    String judulBuku = splitted[0];
                    String penulisBuku = splitted[2];

                    // Lakukan pencarian buku
                    Buku buku = SistakaNG.findBuku(judulBuku, penulisBuku);

                    // Saat tanggal valid maka cek kembali apakah buku sedang dipinjam olehnya
                
                    // Meminjam buku
                    pinjam(buku, tanggal);
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
        // Method combobox set default dan refresh
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) bukuDropDown.getModel();
        model.removeAllElements();
        ArrayList<Buku> daftarBuku = SistakaNG.getDaftarBuku();
        for (Buku buku : daftarBuku) {
            model.addElement(String.format("%s oleh %s", buku.getJudul(), buku.getPenulis())); // formatting <judul> oleh <penulis>
        }
        bukuDropDown.setModel(model);
    }

    // Method melakukan peminjaman buku
    public void pinjam(Buku buku, String tanggalPinjam) {
        Pengguna user = main.getUser();

        // Saat masih kosong maka bisa langsung lakukan peminjaman
        if (buku.getDaftarPeminjam().size() == 0) {
            user.pinjam(buku, tanggalPinjam);

            // Munculkan pesan
            JOptionPane.showMessageDialog(main.getFrame(),
                String.format("%s berhasil meminjam Buku %s!", user.getNama(), buku.getJudul()),
                "Info",
            JOptionPane.INFORMATION_MESSAGE);
        } else {

            // Saat ada yang meminjam buku cek dulu apakah dia sedang meminjam atau tidak
            if (user.findBookOnLoan(buku) == -1) {
                // Saat find == -1 maka tidak ada dalam daftar pinjaman -> lakukan peminjaman
                user.pinjam(buku, tanggalPinjam);

                // Munculkan pesan
                JOptionPane.showMessageDialog(main.getFrame(),
                    String.format("%s berhasil meminjam Buku %s!", user.getNama(), buku.getJudul()),
                    "Info",
                JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Selain itu maka buku sedang ia pinjam
                // Munculkan pesan
                JOptionPane.showMessageDialog(main.getFrame(),
                    String.format("Buku %s oleh %s sedang dipinjam", buku.getJudul(), buku.getPenulis()),
                    "Info",
                JOptionPane.WARNING_MESSAGE);
            }
        }

        
    }

}
