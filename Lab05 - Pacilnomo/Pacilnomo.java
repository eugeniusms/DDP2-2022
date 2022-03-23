import java.util.Scanner;

public class Pacilnomo {
	private static Aset[] portofolio;
	private static double earnings;

	private static void printSeparator() {
		System.out.println("=".repeat(64));
	}
	
	private static void daftarAset() {
		printSeparator();
		System.out.printf("Kamu memiliki %d total aset:\n", portofolio.length);
		for(Aset a : portofolio) {
			System.out.println("- " + a);
		}
		printSeparator();
	}

	private static void infoPortofolio() {
		int jumlahSaham = 0, jumlahObligasi = 0;
		double netWorth = 0;

		// TODO implementasikan perhitungan total valuasi aset, yaitu nilai semua aset dan pendapatan dari dividen dan bunga

		printSeparator();
		System.out.printf("" +
		"Info Portofolio\n" +
		"Jumlah Jenis Saham: %d\n" +
		"Jumlah Jenis Obligasi: %d\n" +
		"Total Nilai Portofolio: %.2f\n", jumlahSaham, jumlahObligasi, netWorth);
		printSeparator();
	}

	private static void nextYear() {
		// TODO implementasikan pemanggilan nextYear untuk setiap aset pada portofolio
		for(Aset a : portofolio) {
			a.nextYear();
			earnings += a.kirimBunga();
		}
		System.out.println("CEK: " + earnings);
	}
	
	public static void main(String[] args) {
		// Program utama akan berjalan dari sini untuk menampilkan tampilan program dan
		// mengambil input program
		Scanner in = new Scanner(System.in);
		System.out.print("Silakan masukkan banyak aset yang tersedia: ");
		int banyakAset = Integer.parseInt(in.nextLine());
		
		// Inisiasi portofolio yang merupakan object Aset
		portofolio = new Aset[banyakAset];
		
		// Program akan mengambil input dari user berupa format:
		// 1) Format Saham
		// <Nama Aset> <Jenis Aset> <Jumlah> <Harga> <Pertumbuhan> <Dividen>
		// 2) Format Obligasi
		// <Nama Aset> <Jenis Aset> <Jumlah> <Harga> <Bunga> <Maturitas>
		for(int i = 0; i < banyakAset; i++) {
			System.out.printf("Aset %d: ", i + 1);
			String inp[] = in.nextLine().split("\\s+");
			String namaAset = inp[0], jenisAset = inp[1]; 
			int jumlah = Integer.valueOf(inp[2]); 
			double harga = Double.valueOf(inp[3]);
			
			if(jenisAset.equals("SAHAM")) {
				// TODO tambahkan aset saham ke portofolio berdasarkan input user 
				// portofolio[i] = new Saham(namaAset, jumlah, harga);
			} else if(jenisAset.equals("OBLIGASI")) {
				// TODO tambahkan aset obligasi ke portofolio berdasarkan input user
				double bunga = Double.valueOf(inp[4]);
				int maturitas = Integer.valueOf(inp[5]);
				portofolio[i] = new Obligasi(namaAset, jumlah, harga, bunga, maturitas);
			} 
		}

		System.out.print("Selamat datang di...");
		System.out.print("\n" +
		"\n" +
		" /$$$$$$$                     /$$ /$$\n" +
		"| $$__  $$                   |__/| $$\n" +
		"| $$  \\ $$ /$$$$$$   /$$$$$$$ /$$| $$ /$$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$\n" +
		"| $$$$$$$/|____  $$ /$$_____/| $$| $$| $$__  $$ /$$__  $$| $$_  $$_  $$ /$$__  $$\n" +
		"| $$____/  /$$$$$$$| $$      | $$| $$| $$  \\ $$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$\n" +
		"| $$      /$$__  $$| $$      | $$| $$| $$  | $$| $$  | $$| $$ | $$ | $$| $$  | $$\n" +
		"| $$     |  $$$$$$$|  $$$$$$$| $$| $$| $$  | $$|  $$$$$$/| $$ | $$ | $$|  $$$$$$/\n" +
		"|__/      \\_______/ \\_______/|__/|__/|__/  |__/ \\______/ |__/ |__/ |__/ \\______/\n\n");
		
		while(true) {
			System.out.printf("" +
			"Silakan pilih salah satu opsi berikut:\n" +
			"[1] Daftar aset\n" +
			"[2] Info portofolio\n" +
			"[3] Lanjut ke tahun berikutnya\n" +
			"[*] Keluar\n");
			printSeparator();
			System.out.print("Input: ");
			String pilihan = in.nextLine();
			if(pilihan.equals("1")) {
				daftarAset();
			} else if(pilihan.equals("2")) {
				infoPortofolio();
			} else if(pilihan.equals("3")) {
				nextYear();
				System.out.println("Setahun telah berlalu...");
				printSeparator();
			} else {
				System.out.println("Terima kasih telah menggunakan layanan Pacilnomo ~ !");
				break;
			}
		}
		
		in.close();
	}
	
	public static void addToEarnings(double jumlah) {
		earnings += jumlah;
	}
}
