package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.frontend.anggota.ui.*;
import assignments.assignment4.frontend.staf.ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Sebuah class seperti blueprint GUI
public class HomeGUI {
    private final CardLayout layout = new CardLayout();
    private final JFrame frame;
    private final JPanel mainPanel = new JPanel();
    private final Map<String, SistakaPanel> panelMap = new HashMap<>();
    private Pengguna user;

    // Setting HomeGUI
    public HomeGUI(JFrame frame) {
        this.frame = frame;
        mainPanel.setLayout(layout);
        initGUI();
        frame.setContentPane(mainPanel);
    }

    // Method untuk melakukan inisiasi panel pada program
    private void initGUI() {
        frame.setSize(600, 1000);
        SistakaPanel welcomePanel = new WelcomePanel(this);
        panelMap.put("welcome", welcomePanel);
        mainPanel.add(welcomePanel, "welcome");
        SistakaPanel loginPanel = new LoginPanel(this);
        panelMap.put("login", welcomePanel);
        mainPanel.add(loginPanel, "login");
        SistakaPanel anggotaPanel = new AnggotaHomePanel(this);
        panelMap.put("anggota", anggotaPanel);
        mainPanel.add(anggotaPanel, "anggota");
        SistakaPanel stafPanel = new StafHomePanel(this);
        panelMap.put("staf", stafPanel);
        mainPanel.add(stafPanel, "staf");
        SistakaPanel tambahMahasiswa = new TambahMahasiswaPanel(this);
        panelMap.put("tambahMhs", tambahMahasiswa);
        mainPanel.add(tambahMahasiswa, "tambahMhs");
        SistakaPanel tambahDosen = new TambahDosenPanel(this);
        panelMap.put("tambahDosen", tambahDosen);
        mainPanel.add(tambahDosen, "tambahDosen");
        SistakaPanel tambahKategori = new TambahKategoriPanel(this);
        panelMap.put("tambahKategori", tambahKategori);
        mainPanel.add(tambahKategori, "tambahKategori");
        SistakaPanel tambahBuku = new TambahBukuPanel(this);
        panelMap.put("tambahBuku", tambahBuku);
        mainPanel.add(tambahBuku, "tambahBuku");
        SistakaPanel hapusBuku = new HapusBukuPanel(this);
        panelMap.put("hapusBuku", hapusBuku);
        mainPanel.add(hapusBuku, "hapusBuku");
        SistakaPanel peringkat = new PeringkatPanel(this);
        panelMap.put("peringkat", peringkat);
        mainPanel.add(peringkat, "peringkat");
        SistakaPanel detailAnggota = new DetailAnggotaPanel(this);
        panelMap.put("detailAnggota", detailAnggota);
        mainPanel.add(detailAnggota, "detailAnggota");
        SistakaPanel daftarPeminjam = new DaftarPeminjamPanel(this);
        panelMap.put("daftarPeminjam", daftarPeminjam);
        mainPanel.add(daftarPeminjam, "daftarPeminjam");
        SistakaPanel peminjaman = new PeminjamanPanel(this);
        panelMap.put("peminjaman", peminjaman);
        mainPanel.add(peminjaman, "peminjaman");
        SistakaPanel pengembalian = new PengembalianPanel(this);
        panelMap.put("pengembalian", pengembalian);
        mainPanel.add(pengembalian, "pengembalian");
        SistakaPanel pembayaran = new PembayaranPanel(this);
        panelMap.put("pembayaran", pembayaran);
        mainPanel.add(pembayaran, "pembayaran");
        SistakaPanel detailUser= new DetailUserPanel(this);
        panelMap.put("detailUser", detailUser);
        mainPanel.add(detailUser, "detailUser");
    }

    // Getter and Setter
    
    public Pengguna getUser() {
        return user;
    }

    public void setUser(Pengguna user) {
        SistakaNG.setPenggunaLoggedIn(user);
        this.user = user;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setPanel(String target){
        panelMap.get(target).refresh();
        layout.show(mainPanel, target);
    }

    public void exit() {
        frame.dispose();
    }

    // MY STYLING

    // Style Judul
    public String judulHTML(String judul) {
        return "<html><b><p style='font-size:20px; color:#004C99; padding:5px'>" + judul + "</p></b></html>";
    }

    // Style Notes
    public String notesHTML(String judul) {
        return "<html><p style='font-size:8px; color:#A0A0A0; padding:5px'>" + judul + "</p></html>";
    }

    // Style Main Button
    public String mainButtonHTML(String judul) {
        return "<html><b><p style='color:#004C99;'>" + judul + "</p></b></html>";
    }

    // Style Kembali Button
    public String kembaliButtonHTML(String judul) {
        return "<html><p style='color:#990000;'>" + judul + "</p></html>";
    }
}
