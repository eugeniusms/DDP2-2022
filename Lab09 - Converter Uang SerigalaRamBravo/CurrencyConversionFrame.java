import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class CurrencyConversionFrame extends JFrame {
    JLabel labelTitle, labelFrom, labelTo;
    JButton convertButton, exitButton;
    JPanel mainPanel, titlePanel, fromPanel, toPanel, buttonPanel;
    JComboBox<String> dropDownFrom, dropDownTo;
    JTextField fromTextField, toTextField;
    String[] currency = {"Rupiah", "Euro", "US Dollar"};
    HashMap<String, Integer> kurs = new HashMap<String, Integer>();

    public CurrencyConversionFrame() {
        kurs.put("Rupiah", 1);
        kurs.put("Euro", 15000);
        kurs.put("US Dollar", 14000);

        // TO DO: Menentukan layout dan size yang ingin digunakan 
        setSize(600, 300);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setTitle("Currency Converter");

        // TO DO: Membuat Panel sesuai kebutuhan
        mainPanel = new JPanel(new GridLayout(4, 1));
        titlePanel = new JPanel(new GridLayout(1, 3));
        fromPanel = new JPanel(new GridLayout(1, 5));
        toPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel = new JPanel(new GridLayout(1, 4));

        
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

        // Menyusun button akhir
        convertButton = new JButton("Convert");
        exitButton = new JButton("Exit");
        

        // TO DO: Masukkan widget ke panel yang tepat
        titlePanel.add(new JLabel());
        titlePanel.add(labelTitle);
        titlePanel.add(new JLabel());

        fromPanel.add(new JLabel());
        fromPanel.add(labelFrom);
        fromPanel.add(dropDownFrom);
        fromPanel.add(fromTextField);
        fromPanel.add(new JLabel());

        toPanel.add(new JLabel());
        toPanel.add(labelTo);
        toPanel.add(dropDownTo);
        toPanel.add(toTextField);
        toPanel.add(new JLabel());

        buttonPanel.add(new JLabel());
        buttonPanel.add(convertButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(new JLabel());

        mainPanel.add(titlePanel);
        mainPanel.add(fromPanel);
        mainPanel.add(toPanel);
        mainPanel.add(buttonPanel);

        // add(labelTitle);
        // TO DO: Masukkan panel ke dalam frame
        add(mainPanel);


        setVisible(true);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // TO DO: implementasikan pemanggilan method ConvertCurrency
        
                
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                // TO DO: hentikan eksekusi program

            }
        });
    }
    
    // Method converter
    public String ConvertCurrency(String originalValue, String tipeCurrencyFrom, String tipeCurrencyTo) {
        double value = Double.valueOf(originalValue);
        double fromValue = value * kurs.get(tipeCurrencyFrom);
        double convertedValue = fromValue / kurs.get(tipeCurrencyTo);
        return String.format("%.2f", convertedValue);
    }
    
}
