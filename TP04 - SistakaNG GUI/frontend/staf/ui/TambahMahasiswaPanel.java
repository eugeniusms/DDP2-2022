package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Mahasiswa;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk panel tambah mahasiswa
public class TambahMahasiswaPanel extends SistakaPanel {
    public TambahMahasiswaPanel(HomeGUI main) {
        super(main);

        // Menyusun beberapa komponen yang digunakan
        JFrame frame = main.getFrame();

        JPanel tambahMahasiswaPanel = new JPanel(new GridLayout(16, 1));

        JLabel tambahMahasiswaLabel = new JLabel(main.judulHTML("Tambah Mahasiswa"));
        JLabel namaLabel = new JLabel("Nama");
        JLabel tanggalLahirLabel = new JLabel("Tanggal Lahir (DD/MM/YYYY)");
        JLabel programStudiLabel = new JLabel("Program Studi");
        JLabel angkatanLabel = new JLabel("Angkatan");

        JTextField namaTextField = new JTextField();
        JTextField tanggalLahirTextField = new JTextField();
        JTextField angkatanTextField = new JTextField();

        // Menyusun drop down dari program studi
        String[] programStudi = {"SIK", "SSI", "MIK", "MTI", "DIK"};
        JComboBox<String> programStudiDropDown = new JComboBox<>(programStudi);

        JButton tambahButton = new JButton(main.mainButtonHTML("Tambah"));
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Menambahkan komponen ke dalam panel
        tambahMahasiswaPanel.add(new JLabel());
        tambahMahasiswaPanel.add(new JLabel());
        tambahMahasiswaPanel.add(tambahMahasiswaLabel);
        tambahMahasiswaPanel.add(new JLabel());
        tambahMahasiswaPanel.add(namaLabel);
        tambahMahasiswaPanel.add(namaTextField);
        tambahMahasiswaPanel.add(tanggalLahirLabel);
        tambahMahasiswaPanel.add(tanggalLahirTextField);
        tambahMahasiswaPanel.add(programStudiLabel);
        tambahMahasiswaPanel.add(programStudiDropDown);
        tambahMahasiswaPanel.add(angkatanLabel);
        tambahMahasiswaPanel.add(angkatanTextField);
        tambahMahasiswaPanel.add(new JLabel());
        tambahMahasiswaPanel.add(tambahButton);
        tambahMahasiswaPanel.add(kembaliButton);

        // Menambahkan panel
        add(tambahMahasiswaPanel, BorderLayout.NORTH);

        // Action listener untuk tombol tambah ketika ditekan
        tambahButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("MENAMBAHKAN ANGGOTA MAHASISWA"); // TESTING

                // Mengambil masukan pengguna
                String nama = namaTextField.getText();
                String tanggalLahir = tanggalLahirTextField.getText();
                String programStudi = programStudiDropDown.getSelectedItem().toString();
                String angkatan = angkatanTextField.getText();

                // Construct
                Mahasiswa mahasiswaBaru = SistakaNG.addMahasiswa(nama, tanggalLahir, programStudi, angkatan);
                // Return null saat ada input yang salah
                if (mahasiswaBaru == null) {
                    JOptionPane.showMessageDialog(frame,
                        "Tidak dapat menambahkan mahasiswa silahkan periksa kembali input anda!",
                            "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame,
                        "Berhasil menambahkan mahasiswa dengan id " + mahasiswaBaru.getId() + "!",
                        "Success!",
                    JOptionPane.INFORMATION_MESSAGE);
                }
                // Example : SIK211305029Q
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
