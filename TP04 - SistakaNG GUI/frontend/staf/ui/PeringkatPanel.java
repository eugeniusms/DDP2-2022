package assignments.assignment4.frontend.staf.ui;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.frontend.HomeGUI;
import assignments.assignment4.frontend.SistakaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk menyusun panel peringkat
public class PeringkatPanel extends SistakaPanel {
    // Menyusun panel dan komponen yang akan digunakan
    JPanel peringkatPanel = new JPanel(new BorderLayout());

    JPanel judulPanel = new JPanel(new GridLayout(4,1));
    JPanel kembaliPanel = new JPanel(new GridLayout(2,1));

    JLabel peringkatLabel = new JLabel(main.judulHTML("Peringkat"));
    JButton kembaliButton = new JButton(main.kembaliButtonHTML("Kembali"));

    public PeringkatPanel(HomeGUI main) {
        super(main);

        // Action listener untuk tombol kembali ketika ditekan
        kembaliButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            main.setPanel("staf");
            }
        });
    }

    @Override
    public void refresh() {
        // Reset clear panel dulu
        judulPanel.removeAll();
        kembaliPanel.removeAll();
        peringkatPanel.removeAll();


        // Mengambil data pemeringkatan
        JLabel pemeringkatanLabel = new JLabel(SistakaNG.handleRankingAnggota());

        // Set align
        peringkatLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Menambahkan komponen ke dalam panel
        judulPanel.add(new JLabel());
        judulPanel.add(new JLabel());
        judulPanel.add(peringkatLabel);
        judulPanel.add(new JLabel());

        kembaliPanel.add(new JLabel());
        kembaliPanel.add(kembaliButton);

        peringkatPanel.add(judulPanel, BorderLayout.NORTH);
        peringkatPanel.add(pemeringkatanLabel, BorderLayout.CENTER);
        peringkatPanel.add(kembaliPanel, BorderLayout.SOUTH);

        // Menambahkan panel
        add(peringkatPanel);
    }
}
