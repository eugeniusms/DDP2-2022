package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Dosen;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk panel tambah dosen
public class TambahDosenPanel extends SistakaPanel {
    public TambahDosenPanel(HomeGUI main) {
        super(main);

        // Menyusun beberapa komponen yang akan digunakan
        JFrame frame = main.getFrame();

        JPanel tambahDosenPanel = new JPanel(new GridLayout(11, 1));

        JLabel tambahDosenLabel = new JLabel(main.judulHTML("Tambah Dosen"));
        JLabel namaLabel = new JLabel("Nama");

        JTextField namaTextField = new JTextField();
    
        JButton tambahButton = new JButton(main.mainButtonHTML("Tambah"));
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Menambahkan komponen ke panel
        tambahDosenPanel.add(new JLabel());
        tambahDosenPanel.add(new JLabel());
        tambahDosenPanel.add(new JLabel());
        tambahDosenPanel.add(new JLabel());
        tambahDosenPanel.add(tambahDosenLabel);
        tambahDosenPanel.add(new JLabel());
        tambahDosenPanel.add(namaLabel);
        tambahDosenPanel.add(namaTextField);
        tambahDosenPanel.add(new JLabel());
        tambahDosenPanel.add(tambahButton);
        tambahDosenPanel.add(kembaliButton);

        // Menambahkan panel
        add(tambahDosenPanel, BorderLayout.NORTH);

        // Action listener untuk tombol tambah ketika ditekan
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("MENAMBAHKAN ANGGOTA DOSEN"); // TESTING

                // Mengambil masukan pengguna
                String nama = namaTextField.getText();
            
                // Warning saat isian kosong
                if (nama.equals("")) {
                    JOptionPane.showMessageDialog(frame,
                        "Tidak dapat menambahkan dosen silahkan periksa kembali input anda!",
                            "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    // Construct
                    Dosen dosenBaru = SistakaNG.addDosen(nama);
                    // Message Success
                    JOptionPane.showMessageDialog(frame,
                        "Berhasil menambahkan dosen dengan id " + dosenBaru.getId() + "!",
                        "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
                }
                // Example : DOSEN-1
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
