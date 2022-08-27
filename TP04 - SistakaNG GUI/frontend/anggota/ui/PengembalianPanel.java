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

// Kelas untuk menyusun panel pengembalian
public class PengembalianPanel extends SistakaPanel {
    // Menyusun drop down dari daftar kategori
    JComboBox<String> bukuDropDown = new JComboBox<>();

    public PengembalianPanel(HomeGUI main) {
        super(main);
        
        // Menyusun panel dan komponen
        JFrame frame = main.getFrame();

        JPanel kembaliBukuPanel = new JPanel(new GridLayout(12, 1));

        JLabel pengembalianBukuLabel = new JLabel(main.judulHTML("Pengembalian Buku"));
        JLabel bukuLabel = new JLabel("Buku");
        JLabel tanggalPengembalianLabel = new JLabel("Tanggal Pengembalian (DD/MM/YYYY)");
    
        JTextField tanggalPengembalianTextField = new JTextField();

        JButton kembalikanButton = new JButton(main.mainButtonHTML("Kembalikan"));
        JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

        // Menambahkan komponen ke panel
        kembaliBukuPanel.add(new JLabel());
        kembaliBukuPanel.add(new JLabel());
        kembaliBukuPanel.add(new JLabel());
        kembaliBukuPanel.add(pengembalianBukuLabel);
        kembaliBukuPanel.add(new JLabel());
        kembaliBukuPanel.add(bukuLabel);
        kembaliBukuPanel.add(bukuDropDown);
        kembaliBukuPanel.add(tanggalPengembalianLabel);
        kembaliBukuPanel.add(tanggalPengembalianTextField);
        kembaliBukuPanel.add(new JLabel());
        kembaliBukuPanel.add(kembalikanButton);
        kembaliBukuPanel.add(kembaliButton);

        // Menambahkan panel
        add(kembaliBukuPanel, BorderLayout.NORTH);

        // Action listener untuk tombol kembali ketika ditekan
        kembalikanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                // Lakukan pengecekan validasi masukan
                String tanggal = tanggalPengembalianTextField.getText();
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
                    kembali(buku, tanggal);
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
        // Method combobox default dan refresh
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) bukuDropDown.getModel();
        model.removeAllElements();
        ArrayList<Buku> daftarBuku = SistakaNG.getDaftarBuku();
        for (Buku buku : daftarBuku) {
            model.addElement(String.format("%s oleh %s", buku.getJudul(), buku.getPenulis())); // formatting <judul> oleh <penulis>
        }
        bukuDropDown.setModel(model);
    }

    // Method untuk melakukan pengembalian buku
    public void kembali(Buku buku, String tanggalPinjam) {
        Pengguna user = main.getUser();


        // Saat ada yang meminjam buku cek dulu apakah dia sedang meminjam atau tidak
        if (user.findBookOnLoan(buku) == -1) {
            // Saat find == -1 maka tidak ada dalam daftar pinjaman -> pengembalian tidak bisa dilakukan
        
            JOptionPane.showMessageDialog(main.getFrame(),
                String.format("Buku %s oleh %s tidak sedang dipinjam oleh %s", buku.getJudul(), buku.getPenulis(), user.getNama()),
                "Info",
            JOptionPane.WARNING_MESSAGE);
        } else {
            // Selain itu maka buku dapat dikembalikan
            // Munculkan pesan
            String pesan = user.kembali(buku, tanggalPinjam);

            // Munculkan pesan
            JOptionPane.showMessageDialog(main.getFrame(),
                String.format(pesan),
                "Info",
            JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
