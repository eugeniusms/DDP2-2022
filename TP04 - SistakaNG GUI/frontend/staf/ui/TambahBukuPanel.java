package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.buku.Kategori;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Kelas untuk menyusun panel tambah buku
public class TambahBukuPanel extends SistakaPanel {
    // Menyusun drop down dari daftar kategori
    JComboBox<String> kategoriDropDown = new JComboBox<>();

    public TambahBukuPanel(HomeGUI main) {
        super(main);

        // Menyusun komponen yang akan digunakan
        JFrame frame = main.getFrame();

        JPanel tambahBukuPanel = new JPanel(new GridLayout(16, 1));

        JLabel tambahBukuLabel = new JLabel(main.judulHTML("Tambah Buku"));
        JLabel judulLabel = new JLabel("Judul");
        JLabel penulisLabel = new JLabel("Penulis");
        JLabel penerbitLabel = new JLabel("Penerbit");
        JLabel kategoriLabel = new JLabel("Kategori");
        JLabel stokLabel = new JLabel("Stok");

        JTextField judulTextField = new JTextField();
        JTextField penulisTextField = new JTextField();
        JTextField penerbitTextField = new JTextField();
        JTextField stokTextField = new JTextField();
    
        JButton tambahButton = new JButton(main.mainButtonHTML("Tambah"));
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Menambahkan komponen ke panel
        tambahBukuPanel.add(new JLabel());
        tambahBukuPanel.add(tambahBukuLabel);
        tambahBukuPanel.add(new JLabel());
        tambahBukuPanel.add(judulLabel);
        tambahBukuPanel.add(judulTextField);
        tambahBukuPanel.add(penulisLabel);
        tambahBukuPanel.add(penulisTextField);
        tambahBukuPanel.add(penerbitLabel);
        tambahBukuPanel.add(penerbitTextField);
        tambahBukuPanel.add(kategoriLabel);
        tambahBukuPanel.add(kategoriDropDown);
        tambahBukuPanel.add(stokLabel);
        tambahBukuPanel.add(stokTextField);
        tambahBukuPanel.add(new JLabel());
        tambahBukuPanel.add(tambahButton);
        tambahBukuPanel.add(kembaliButton);

        // Menambahkan panel
        add(tambahBukuPanel, BorderLayout.NORTH);

        // Action listener untuk tombol tambah ketika ditekan
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                // Mengambil data
                String judul = judulTextField.getText();
                String penulis = penulisTextField.getText();
                String penerbit = penerbitTextField.getText();
                String kategori = kategoriDropDown.getSelectedItem().toString();
                String stokString = stokTextField.getText();

                // Handle saat ada input kosong
                if (judul.equals("") || penulis.equals("") || penerbit.equals("") || kategori.equals("") || stokString.equals("")) {
                    // Handle saat input ada kosong
                    JOptionPane.showMessageDialog(frame,
                        "Tidak dapat menambahkan buku silahkan periksa kembali input anda!",
                        "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else if (stokString.equals("0")) {
                    // Handle saat stok tidak lebih dari 0
                    JOptionPane.showMessageDialog(frame,
                        "Stok harus lebih dari 0!",
                        "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    // Convert stok dari string ke integer
                    int stok = Integer.valueOf(stokString);

                    // Saat buku tidak ada di dalam daftar buku yang pernah ditambahkan maka tambahkan buku tersebut
                    if (SistakaNG.findBuku(judul, penulis) == null) {
                        // Menambahkan buku 
                        SistakaNG.addBuku(judul, penulis, penerbit, kategori, stok);
                        // Message
                        JOptionPane.showMessageDialog(frame,
                            String.format("Buku %s oleh %s berhasil ditambahkan!", judul, penulis),
                            "Success!",
                        JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Saat pernah ada maka kembalikan pesan
                        JOptionPane.showMessageDialog(frame,
                            String.format("Buku %s oleh %s sudah pernah ditambahkan", judul, penulis),
                            "Warning",
                        JOptionPane.WARNING_MESSAGE);
                    }
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
        // Refresh combobox dan set isi default 
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) kategoriDropDown.getModel();
        model.removeAllElements();
        ArrayList<Kategori> daftarKategori = SistakaNG.getDaftarKategori();
        for (Kategori kategori : daftarKategori) {
            model.addElement(kategori.getNama());
        }
        kategoriDropDown.setModel(model);
    }
}
