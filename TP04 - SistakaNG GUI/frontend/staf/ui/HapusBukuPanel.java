package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Buku;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Menyusun panel hapus buku
public class HapusBukuPanel extends SistakaPanel {
    // Menyusun drop down dari daftar kategori
    JComboBox<String> bukuDropDown = new JComboBox<>();

    public HapusBukuPanel(HomeGUI main) {
        super(main);

        // Menyusun panel dan komponen 
        JFrame frame = main.getFrame();

        JPanel hapusBukuPanel = new JPanel(new GridLayout(11, 1));

        JLabel hapusBukuLabel = new JLabel(main.judulHTML("Hapus Buku"));
        JLabel bukuLabel = new JLabel("Buku");
    
        JButton hapusButton = new JButton("<html><b><p style='color:red;'>Hapus</p></b></html>");
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Menambahkan komponen ke dalam panel
        hapusBukuPanel.add(new JLabel());
        hapusBukuPanel.add(new JLabel());
        hapusBukuPanel.add(new JLabel());
        hapusBukuPanel.add(new JLabel());
        hapusBukuPanel.add(hapusBukuLabel);
        hapusBukuPanel.add(new JLabel());
        hapusBukuPanel.add(bukuLabel);
        hapusBukuPanel.add(bukuDropDown);
        hapusBukuPanel.add(new JLabel());
        hapusBukuPanel.add(hapusButton);
        hapusBukuPanel.add(kembaliButton);

        // Menambahkan panel
        add(hapusBukuPanel, BorderLayout.NORTH);

        // Action listener untuk tombol hapus ketika ditekan
        hapusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("HAPUS BUKU"); // TESTING

                // Mengambil masukan pengguna
                String bukuDipilih = bukuDropDown.getSelectedItem().toString();

                // Handle saat pilihan masih kosong
                if (bukuDropDown.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(frame,
                        "Silahkan memilih buku!",
                        "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {

                    // Lakukan split untuk mendapatkan buku dan penulisnya dengan regex
                    String[] splitted = bukuDipilih.split("\\s+"); // {<judul>, "oleh", <penulis>}
                    String judulBuku = splitted[0];
                    String penulisBuku = splitted[2];

                    // Lakukan pencarian buku
                    Buku bukuDihapus = SistakaNG.findBuku(judulBuku, penulisBuku);

                    String pesan = SistakaNG.deleteBuku(bukuDihapus);
                    // Tampilkan message
                    JOptionPane.showMessageDialog(frame,
                        pesan,
                        "Info",
                    JOptionPane.INFORMATION_MESSAGE);
                }
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
        // TODO: Implementasikan hal-hal yang diperlukan
        // Jika tidak diperlukan, Anda dapat mengabaikannya (kosongkan method ini)
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) bukuDropDown.getModel();
        model.removeAllElements();
        ArrayList<Buku> daftarBuku = SistakaNG.getDaftarBuku();
        for (Buku buku : daftarBuku) {
            model.addElement(String.format("%s oleh %s", buku.getJudul(), buku.getPenulis())); // formatting <judul> oleh <penulis>
        }
        bukuDropDown.setModel(model);
    }
}
