// Melakukan import Java library
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

// Kelas CurrencyConversionFrame merupakan hasil extends dari JFrame
public class CurrencyConversionFrame extends JFrame {
    // Melakukan deklarasi variabel yang digunakan sesuai objeknya
    JLabel labelTitle, labelFrom, labelTo;
    JButton convertButton, exitButton;
    JPanel mainPanel, titlePanel, fromPanel, toPanel, buttonPanel;
    JComboBox<String> dropDownFrom, dropDownTo;
    JTextField fromTextField, toTextField;
    String[] currency = {"Rupiah", "Euro", "US Dollar"};
    HashMap<String, Integer> kurs = new HashMap<String, Integer>();

    // Construct CurrencyConversionFrame
    public CurrencyConversionFrame() {
        // Memasukan daftar mata uang yang digunakan dalam perhitungan ke dalam HashMap
        kurs.put("Rupiah", 1);
        kurs.put("Euro", 15000);
        kurs.put("US Dollar", 14000);

        // Menentukan layout dan size yang ingin digunakan 
        setSize(600, 150);

        // Set default things
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle("Currency Converter");

        // Membuat Panel sesuai kebutuhan
        mainPanel = new JPanel(new GridLayout(4, 1));
        titlePanel = new JPanel(new GridLayout(1, 3));
        fromPanel = new JPanel(new GridLayout(1, 5));
        toPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel = new JPanel(new GridLayout(1, 4));

        // Menyusun judul dari program
        labelTitle = new JLabel();
        labelTitle.setText("Welcome to Currency Converter");

        // Menyusun label dari from-to
        labelFrom = new JLabel("From");
        labelTo = new JLabel("To");

        // Menyusun drop down dari from-to
        dropDownFrom = new JComboBox<>(currency);
        dropDownTo = new JComboBox<>(currency);

        // Menyusun textfield dari from-to
        fromTextField = new JTextField();
        toTextField = new JTextField();
        toTextField.setEditable(false);

        // Menyusun button akhir
        convertButton = new JButton("Convert");
        exitButton = new JButton("Exit");

        // Memasukkan widget ke panel yang tepat
        // Widget untuk judul
        titlePanel.add(new JLabel());
        titlePanel.add(labelTitle);
        titlePanel.add(new JLabel());

        // Widget untuk form From
        fromPanel.add(new JLabel());
        fromPanel.add(labelFrom);
        fromPanel.add(dropDownFrom);
        fromPanel.add(fromTextField);
        fromPanel.add(new JLabel());

        // Widget untuk form To
        toPanel.add(new JLabel());
        toPanel.add(labelTo);
        toPanel.add(dropDownTo);
        toPanel.add(toTextField);
        toPanel.add(new JLabel());

        // Widget untuk tombol
        buttonPanel.add(new JLabel());
        buttonPanel.add(convertButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(new JLabel());

        // Memasukkan semua panel ke dalam mainPanel
        mainPanel.add(titlePanel);
        mainPanel.add(fromPanel);
        mainPanel.add(toPanel);
        mainPanel.add(buttonPanel);

        // Memasukkan panel ke dalam frame
        add(mainPanel);
        setVisible(true);

        // Action listener untuk tombol convert ketika ditekan
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Mengambil data dari form From
                String money = fromTextField.getText();
                String fromKurs = dropDownFrom.getSelectedItem().toString();
                String toKurs = dropDownTo.getSelectedItem().toString();
                // Melakukan konversi menggunakan method ConvertCurrency
                String result = ConvertCurrency(money, fromKurs, toKurs);
                // Mengubah nilai hasil konversi pada textField To
                toTextField.setText(result);
            }
        });

        // Action listener untuk tombol exit ketika ditekan
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // Menghentikan eksekusi program
                System.exit(0);
            }
        });
    }
    
    // Method converter kurs
    public String ConvertCurrency(String originalValue, String tipeCurrencyFrom, String tipeCurrencyTo) {
        // Mengambil nilai dengan tipe data Double
        double value = Double.valueOf(originalValue);
        double fromValue = value * kurs.get(tipeCurrencyFrom);
        // Melakukan perhitungan konversi dan mengembalikan dengan string formatting value.xx
        double convertedValue = fromValue / kurs.get(tipeCurrencyTo);
        return String.format("%.2f", convertedValue);
    }
    
}
