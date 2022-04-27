import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

public class DekDepeTalk extends JFrame {
  // Import images
  private ImageIcon profileIcon = new ImageIcon("images/dekdepe-profile.png");
  // Create text fields for interest rate, years
  // loan amount, monthly payment, and total payment
  private JTextField jtfTypeChat = new JTextField();
  private JTextField jtfAnnualInterestRate = new JTextField();
  private JTextField jtfNumberOfYears = new JTextField();
  private JTextField jtfLoanAmount = new JTextField();
  private JTextField jtfMonthlyPayment = new JTextField();
  private JTextField jtfTotalPayment = new JTextField();

  Color profileColor = new Color(50, 50, 50);

  // Create a Compute Payment button
  private JButton jbtComputeLoan = new JButton("Visualize");
  private JButton jbtSendMessage = new JButton("Kirim");

  // Online Label
  private JLabel profileName = new JLabel("Dek Depe");
  private JLabel logActivity = new JLabel("Online");

  public DekDepeTalk() {
    // Panel p1 to hold labels and text fields
    JPanel profile = new JPanel(new FlowLayout());
    profile.add(new JLabel(profileIcon));

    JPanel seen = new JPanel(new GridLayout(2,1));
    profileName.setForeground(Color.WHITE);
    profileName.setFont(profileName.getFont().deriveFont(Font.BOLD, 14f));
    seen.add(profileName);
    logActivity.setForeground(Color.GREEN);
    seen.add(logActivity);
    seen.setBackground(profileColor);

    profile.add(seen);
    profile.setBackground(profileColor);

    JPanel p1 = new JPanel(new GridLayout(1,2));
    p1.add(profile);
    p1.add(new JLabel());
    p1.setBackground(profileColor);

    //Create a border
    Border blackline = BorderFactory.createLineBorder(Color.black);
    p1.setBorder(blackline);

    // Panel p2 to hold the button
    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    p2.add(jbtComputeLoan);
    p2.setBorder(blackline);

    // Panel p3 untuk memvisualisasikan grafik
    JPanel p3 = new JPanel(new GridLayout(3,1));
    p3.add(new JLabel("Ketik Pesan "));
    p3.add(jtfTypeChat);

    JPanel send = new JPanel(new GridLayout(1,3));
    send.add(new JLabel());
    send.add(new JLabel());
    send.add(jbtSendMessage);
    p3.add(send);
    p3.setBorder(blackline);

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
    DekDepeTalk frame = new DekDepeTalk();
    frame.pack();
    frame.setTitle("DekDepeTalk");
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
