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
  Color chatBackgroundSectionColor = new Color(115, 115, 115);

  // Create a Compute Payment button
  private JButton jbtMessage = new JButton("Visualize Lorem Ipsum Sit Dolor Amet");
  private JButton jbtSendMessage = new JButton("Kirim");

  // Online Label
  private JLabel profileName = new JLabel("Dek Depe");
  private JLabel logActivity = new JLabel("Online");
  private JLabel ketikPesan = new JLabel("Ketik Pesan");

  JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

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
    
    // jbtMessage.setPreferredSize(new Dimension(jbtMessage.getWidth(), 40));
    p2.add(jbtMessage);
    p2.setBorder(blackline);
    p2.setBackground(chatBackgroundSectionColor);

    // Panel p3 untuk memvisualisasikan grafik
    JPanel p3 = new JPanel(new GridLayout(3,1));
    ketikPesan.setForeground(Color.WHITE);
    p3.add(ketikPesan);
    p3.add(jtfTypeChat);

    JPanel send = new JPanel(new GridLayout(1,3));
    send.add(new JLabel());
    send.add(new JLabel());
    send.add(jbtSendMessage);
    send.setBackground(profileColor);
    p3.add(send);
    p3.setBorder(blackline);
    p3.setBackground(profileColor);

    // Add the panels to the frame
    add(p1, BorderLayout.NORTH);
    add(p2, BorderLayout.CENTER);
    add(p3, BorderLayout.SOUTH);

    // Register listener
    jbtSendMessage.addActionListener(new ButtonListener());
  }

  /** Handle the Compute Payment button */
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      p2.add(jbtMessage);
      repaint();
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
