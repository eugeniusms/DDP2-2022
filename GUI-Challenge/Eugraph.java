import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Eugraph extends JFrame {
  // Create text fields for interest rate, years
  // loan amount, monthly payment, and total payment
  private JTextField jtfAnnualInterestRate = new JTextField();
  private JTextField jtfNumberOfYears = new JTextField();
  private JTextField jtfLoanAmount = new JTextField();
  private JTextField jtfMonthlyPayment = new JTextField();
  private JTextField jtfTotalPayment = new JTextField();

  // Create a Compute Payment button
  private JButton jbtComputeLoan = new JButton("Visualize");

  public Eugraph() {
    // Panel p1 to hold labels and text fields
    JPanel p1 = new JPanel(new GridLayout(1, 4));
    p1.add(new JLabel("Masukkan Fungsi : "));
    p1.add(jtfAnnualInterestRate);
    p1.add(new JLabel("x^2 + "));
    p1.add(jtfNumberOfYears);
    p1.add(new JLabel("x + "));
    p1.add(jtfLoanAmount);
    p1.add(new JLabel(" "));
    p1.setBorder(new
      TitledBorder("Eugraph Mathematical Curve Graph"));

    // Panel p2 to hold the button
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    p2.add(jbtComputeLoan);

    // Panel p3 untuk memvisualisasikan grafik
    JPanel p3 = new JPanel(new FlowLayout());
    p3.add(new JLabel("Ini Grafik"));

    // Add the panels to the frame
    add(p1, BorderLayout.NORTH);
    add(p2, BorderLayout.CENTER);
    add(p3, BorderLayout.SOUTH);

    // Register listener
    jbtComputeLoan.addActionListener(new ButtonListener());
  }

  /** Handle the Compute Payment button */
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // Get values from text fields
      double interest =
        Double.parseDouble(jtfAnnualInterestRate.getText());
      int year = Integer.parseInt(jtfNumberOfYears.getText());
      double loanAmount =
        Double.parseDouble(jtfLoanAmount.getText());

      // Create a loan object
      Loan loan = new Loan(interest, year, loanAmount);

      // Display monthly payment and total payment
      jtfMonthlyPayment.setText(String.format("%.2f",
        loan.getMonthlyPayment()));
      jtfTotalPayment.setText(String.format("%.2f",
        loan.getTotalPayment()));
    }
  }

  public static void main(String[] args) {
    Eugraph frame = new Eugraph();
    frame.pack();
    frame.setTitle("LoanCalculator");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
