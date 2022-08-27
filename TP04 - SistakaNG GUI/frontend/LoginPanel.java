package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Kelas untuk panel login
public class LoginPanel extends SistakaPanel {
    public LoginPanel(HomeGUI main){
        super(main);

        // Melakukan inisiasi beberapa komponen
        JFrame frame = main.getFrame();

        JPanel bigPanel = new JPanel(new BorderLayout());

        JPanel logPanel = new JPanel(new GridLayout(7, 1));
        JPanel fieldLoginPanel = new JPanel(new GridLayout(2, 2));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));

        JLabel welcomeLabel = new JLabel(main.judulHTML("SistakaNG"));
        JLabel loginLabel = new JLabel(main.notesHTML("Masukkan ID Anda untuk login ke sistem"));
        JLabel usernameLabel = new JLabel("Username");
        JLabel idLabel = new JLabel("Password/ID");

        JTextField usernameTextField = new JTextField();
        JPasswordField loginTextField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Melakukan set beberapa komponen
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginTextField.setEchoChar('*');

        // Menambahkan komponen ke logPanel
        logPanel.add(new JLabel()); // DUMMIE
        logPanel.add(new JLabel()); // DUMMIE
        logPanel.add(new JLabel()); // DUMMIE
        logPanel.add(new JLabel()); // DUMMIE
        logPanel.add(new JLabel()); // DUMMIE
        logPanel.add(welcomeLabel); 
        logPanel.add(loginLabel);

        // Menambahkan komponen ke fieldLoginPanel
        fieldLoginPanel.add(usernameLabel);
        fieldLoginPanel.add(usernameTextField);
        fieldLoginPanel.add(idLabel);
        fieldLoginPanel.add(loginTextField);
        
        // Menambahkan komponen ke buttonPanel
        buttonPanel.add(new JLabel()); // DUMMIE
        buttonPanel.add(new JLabel()); // DUMMIE
        buttonPanel.add(new JLabel()); // DUMMIE
        buttonPanel.add(new JLabel()); // DUMMIE
        buttonPanel.add(loginButton);
        buttonPanel.add(new JLabel()); // DUMMIE

        // Menambahkan panel ke frame
        frame.add(logPanel);

        // Menambahkan subpanel ke panel utama
        bigPanel.add(logPanel, BorderLayout.NORTH);
        bigPanel.add(fieldLoginPanel, BorderLayout.CENTER);
        bigPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Menambahkan panel
        add(bigPanel);

        // Action listener untuk tombol login ketika ditekan
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String idMasukan = loginTextField.getText();

                // Handle pengguna login dengan masukan ID tidak terisi
                if (idMasukan.equals("")) {
                    //custom title, warning icon
                    JOptionPane.showMessageDialog(frame,
                        "Harap masukan id anda pada kotak diatas!",
                        "Warning",
                    JOptionPane.WARNING_MESSAGE);
                } else {
                    // Handle saat id masukan tidak ditemukan baik pada staff dan anggota
                    if (SistakaNG.findStaf(idMasukan) == null && SistakaNG.findAnggota(idMasukan) == null) {
                        JOptionPane.showMessageDialog(frame,
                            String.format("Pengguna dengan ID %s tidak ditemukan", idMasukan),
                            "Warning",
                        JOptionPane.WARNING_MESSAGE);
                    } else if (SistakaNG.findStaf(idMasukan) != null) {
                        // Saat id masukan ditemukan pada mode staf maka lanjut ke page staf
                        // Set pengguna logged in
                        main.setUser(SistakaNG.findStaf(idMasukan));
                        System.out.print(main.getUser().getNama()); // TESTING

                        // Handle username harus sesuai dengan id
                        if (usernameTextField.getText().equals(main.getUser().getNama())) {
                            // Reset masukan login
                            loginTextField.setText("");
                            // Set panel ke staf
                            main.setPanel("staf");
                        } else {
                            // Saat username berbeda dengan pengguna id keluarkan pesan
                            JOptionPane.showMessageDialog(frame,
                                "Username dan Password/ID Anda tidak cocok! Silakan masukkan ulang Username dan Password/ID Anda",
                                "Login Gagal",
                            JOptionPane.WARNING_MESSAGE);
                        }

                    } else if (SistakaNG.findAnggota(idMasukan) != null) {
                        // Saat id masukan ditemukan pada mode anggota maka lanjut ke page anggota
                        // Set pengguna logged in
                        main.setUser(SistakaNG.findAnggota(idMasukan));

                        // Handle username harus sesuai dengan id
                        if (usernameTextField.getText().equals(main.getUser().getNama())) {
                            // Reset masukan login
                            loginTextField.setText("");
                            // Set panel ke anggota
                            main.setPanel("anggota");
                        } else {
                            // Saat username berbeda dengan pengguna id keluarkan pesan
                            JOptionPane.showMessageDialog(frame,
                                "Username dan Password/ID Anda tidak cocok! Silakan masukkan ulang Username dan Password/ID Anda",
                                "Login Gagal",
                            JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            } 
        });
        
    }

    @Override
    public void refresh() {
        // ignored
    }
}
