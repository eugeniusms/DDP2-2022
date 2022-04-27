import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BolaFrame extends JFrame implements ActionListener {
	private JPanel kanvas;
	private JButton buttonJatuhkan;

	public BolaFrame() {
		setTitle("Program Bola");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();

		// buat tombol untuk menjatuhkan bola dan letakkan di atas
		JPanel p = new JPanel();
		buttonJatuhkan = new JButton("Jatuhkan Bola");
		buttonJatuhkan.addActionListener(this);
		p.add(buttonJatuhkan);
		c.add(p, BorderLayout.NORTH);

		// buat kanvas untuk menggambar bola dan letakkan di tengah
		kanvas = new JPanel();
		c.add(kanvas, BorderLayout.CENTER);

		// buat lantai dan letakkan di bawah
		JPanel lantai = new JPanel();
		lantai.setBackground(Color.GRAY);
		c.add(lantai, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		// membuat bola baru pada kanvas dan memulai animasi bola tersebut
		new Bola(kanvas).start();
	}

	public static void main(String[] args) {
		BolaFrame f = new BolaFrame();
		f.setSize(300, 250);
		f.setVisible(true);
	}
}

class Bola extends Thread {
	private static final Color BALL_COLOR = Color.BLUE;
	private static final int BALL_SIZE = 40;

	private JPanel kotak;
	private int x = 120;
	private int y = 0;
	private int dy = 1;

	public Bola(JPanel k) {
		kotak = k;
	}

	/**
	 * Gambarkan bola pada posisi x dan y
	 */
	public void gambarkan() {
		Graphics g = kotak.getGraphics();
		g.setColor(BALL_COLOR);
		g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
	}

	/**
	 * Hapus bola pada posisi x dan y, geser posisi, dan gambar bola pada posisi baru
	 */
	public void gerakan() {
		Graphics g = kotak.getGraphics();
		g.setColor(BALL_COLOR);
		g.setXORMode(kotak.getBackground());
		// menghapus bola di posisi sebelumnya
		g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
		// menggeser bola
		y += dy;
		// menggambar bola di posisi baru
		g.fillOval(x, y, BALL_SIZE, BALL_SIZE);

		// mencari besar dari kotak, gunakan perintah:
		// Dimension d = kotak.getSize();
	}

	@Override
	public void run() {
		// gambar bola di posisi awal
		gambarkan();
		// ulangi gerakan sebanyak 1000 kali
		for (int i = 1; i <= 1000; i++) {
			gerakan();
			try {
				// menunggu 10 milidetik sebelum bergerak lagi
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
}
