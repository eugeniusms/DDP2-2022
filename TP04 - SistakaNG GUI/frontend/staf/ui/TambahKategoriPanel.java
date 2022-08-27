package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk panel tambah kategori
public class TambahKategoriPanel extends SistakaPanel {
    public TambahKategoriPanel(HomeGUI main) {
        super(main);

        // Menyusun beberapa komponen yang digunakan
        JFrame frame = main.getFrame();

        JPanel tambahKategoriPanel = new JPanel(new GridLayout(12, 1));

        JLabel tambahKategoriLabel = new JLabel(main.judulHTML("Tambah Kategori"));
        JLabel namaLabel = new JLabel("Nama");
        JLabel poinLabel = new JLabel("Poin");

        JTextField namaTextField = new JTextField();
        JTextField poinTextField = new JTextField();
    
        JButton tambahButton = new JButton(main.mainButtonHTML("Tambah"));
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Menambahkan komponen ke dalam panel
        tambahKategoriPanel.add(new JLabel());
        tambahKategoriPanel.add(new JLabel());
        tambahKategoriPanel.add(new JLabel());
        tambahKategoriPanel.add(tambahKategoriLabel);
        tambahKategoriPanel.add(new JLabel());
        tambahKategoriPanel.add(namaLabel);
        tambahKategoriPanel.add(namaTextField);
        tambahKategoriPanel.add(poinLabel);
        tambahKategoriPanel.add(poinTextField);
        tambahKategoriPanel.add(new JLabel());
        tambahKategoriPanel.add(tambahButton);
        tambahKategoriPanel.add(kembaliButton);

        // Menambahkan panel
        add(tambahKategoriPanel, BorderLayout.NORTH);

        // Action listener untuk tombol tambah ketika ditekan
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("MENAMBAHKAN KATEGORI"); // TESTING

                // Mengambil masukan pengguna
                String nama = namaTextField.getText();
                String poinText = poinTextField.getText();

                // Handle saat belum ada input
                if (nama.equals("") || poinText.equals("")) {
                    // Saat input ada yang kosong
                    JOptionPane.showMessageDialog(frame,
                        "Tidak dapat menambahkan kategori silahkan periksa kembali input anda!",
                        "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {

                    // Convert poin dari string ke integer
                    int poin = Integer.valueOf(poinText);

                    if (SistakaNG.findKategori(nama) == null) {
                        // Saat kategori belum pernah dibuat maka tambahkan kategori baru
                        SistakaNG.addKategori(nama, poin);

                        // Message Success
                        JOptionPane.showMessageDialog(frame,
                            String.format("Kategori %s dengan poin %d berhasil ditambahkan", nama, poin),
                            "Success!",
                        JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Saat kategori sudah pernah dibuat
                        JOptionPane.showMessageDialog(frame,
                            String.format("Kategori %s sudah pernah ditambahkan!", nama),
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
        // Pass
    }
}
