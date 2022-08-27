package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;
import assignments.assignment4.backend.pengguna.Pengguna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk menyusun panel staf
public class StafHomePanel extends SistakaPanel {
    // Menyusun beberapa komponen yang akan digunakan
    JPanel allPanel = new JPanel(new GridLayout(4,1));

    JPanel judulPanel = new JPanel(new GridLayout(2, 1));
    JPanel staPanel = new JPanel(new GridLayout(4, 2));
    JPanel logoutPanel = new JPanel(new GridLayout(3,3));

    JButton tambahMahasiswaButton = new JButton("Tambah Mahasiswa");
    JButton tambahDosenButton = new JButton("Tambah Dosen");
    JButton tambahKategoriButton = new JButton("Tambah Kategori");
    JButton tambahBukuButton = new JButton("Tambah Buku");
    JButton hapusBukuButton = new JButton("Hapus Buku");
    JButton tigaPeringkatPertamaButton = new JButton("3 Peringkat Pertama");
    JButton detailAnggotaButton = new JButton("Detail Anggota");
    JButton daftarPeminjamanBukuButton = new JButton("Daftar Peminjaman Buku");
    JButton logoutButton = new JButton("<html><p style='color:#990000'>Logout</p></html>");

    JLabel petunjukLabel = new JLabel(main.notesHTML("Silakan pilih menu sesuai dengan apa yang ingin anda lakukan."));

    public StafHomePanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol tambah mahasiswa ketika ditekan
        tambahMahasiswaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahMhs");
            }
        });

        // Action listener untuk tombol tambah dosen ketika ditekan
        tambahDosenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahDosen");
            }
        });

        // Action listener untuk tombol tambah kategori ketika ditekan
        tambahKategoriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahKategori");
            }
        });

        // Action listener untuk tombol tambah buku ketika ditekan
        tambahBukuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("tambahBuku");
            }
        });

        // Action listener untuk tombol hapus buku ketika ditekan
        hapusBukuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("hapusBuku");
            }
        });

        // Action listener untuk tombol peringkat anggota ketika ditekan
        tigaPeringkatPertamaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("peringkat");
            }
        });

        // Action listener untuk tombol detail anggota ketika ditekan
        detailAnggotaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("detailAnggota");
            }
        });

        // Action listener untuk tombol daftar peminjam ketika ditekan
        daftarPeminjamanBukuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("daftarPeminjam");
            }
        });

        // Action listener untuk tombol logout ketika ditekan
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                main.setPanel("login");
            }
        });
    }

    @Override
    public void refresh() {

        // Clear reset staLabel dulu
        judulPanel.removeAll();
        staPanel.removeAll();
        logoutPanel.removeAll();
        allPanel.removeAll();

        // Tampilkan nama pengguna
        Pengguna user = main.getUser();
        JLabel stafLabel = new JLabel(main.judulHTML("Selamat datang kembali " + user.getNama() + "!"));

        // Set Align
        petunjukLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Menambahkan komponen ke panel
        judulPanel.add(stafLabel);
        judulPanel.add(petunjukLabel);

        staPanel.add(tambahMahasiswaButton);
        staPanel.add(tambahDosenButton);

        staPanel.add(tambahKategoriButton);
        staPanel.add(tambahBukuButton);

        staPanel.add(hapusBukuButton);
        staPanel.add(tigaPeringkatPertamaButton);

        staPanel.add(detailAnggotaButton);
        staPanel.add(daftarPeminjamanBukuButton);

        logoutPanel.add(new JLabel());
        logoutPanel.add(new JLabel());
        logoutPanel.add(new JLabel());
        logoutPanel.add(new JLabel());
        logoutPanel.add(logoutButton);
        logoutPanel.add(new JLabel());
        logoutPanel.add(new JLabel());
        logoutPanel.add(new JLabel());
        logoutPanel.add(new JLabel());

        allPanel.add(new JLabel());
        allPanel.add(judulPanel);
        allPanel.add(staPanel);
        allPanel.add(logoutPanel);

        // Menambahkan panel
        add(allPanel);
    }

}
