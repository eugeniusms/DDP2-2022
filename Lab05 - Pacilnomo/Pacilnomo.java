import java.util.Scanner;
// Pacilnomo merupakan program yang ditujukan untuk memudahkan pelacakan
// instrumen investasi pengguna, terdapat dua jenis investasi yaitu
// saham dan obligasi yang masing-masingnya memiliki cara kerja tersendiri
public class Pacilnomo {
	// Menginisiasikan data field Pacilnomo
	private static Aset[] portofolio;
	private static double earnings;
	private static int totalSaham;
	private static int totalObligasi;

	// Method untuk mempercantik tampilan ===...
	private static void printSeparator() {
		System.out.println("=".repeat(64));
	}
	
	// Method untuk menampilkan daftar aset yang dimiliki pengguna
	private static void daftarAset() {
		printSeparator();
		System.out.printf("Kamu memiliki %d total aset:\n", portofolio.length);
		// Mencetak toString dari setiap aset yang dimiliki pengguna
		for(Aset a : portofolio) {
			System.out.println("- " + a);
		}
		printSeparator();
	}

	// Method untuk menampilkan info portofolio pengguna (jumlah dan hasil)
	private static void infoPortofolio() {
		int jumlahSaham = totalSaham, jumlahObligasi = totalObligasi;
		double netWorth = 0;

		// Mengambil netWorth dari setiap instrumen investasi untuk ditambahkan
		for (Aset a : portofolio) {
			netWorth += a.getNetWorth();
		}
		netWorth += earnings;

		// Mencetak tampilan yang mudah dibaca pengguna
		printSeparator();
		System.out.printf("" +
		"Info Portofolio\n" +
		"Jumlah Jenis Saham: %d\n" +
		"Jumlah Jenis Obligasi: %d\n" +
		"Total Nilai Portofolio: %.2f\n", jumlahSaham, jumlahObligasi, netWorth);
		printSeparator();
	}

	// Method digunakan untuk memanggil nextYear() dari Aset dan subclassnya
	// setelah itu, menambahkan hasil yang didapat dari setiap instrumen
	// ke dalam earnings
	private static void nextYear() {
		for(Aset a : portofolio) {
			addToEarnings(a.nextYear());
		}
	}
	
	// Program utama dari Pacilnomo berjalan di method main
	// method main digunakan untuk mengatur jalannya program 
	// melalui input yang diberikan pengguna 
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
				// Saat jenisAset adalah saham maka lakukan perhitungan saham dan inisiasikan
				// aset sebagai saham
				totalSaham += 1;
				double pertumbuhan = Double.valueOf(inp[4]);
				double dividen = Double.valueOf(inp[5]);
				portofolio[i] = new Saham(namaAset, jumlah, harga, pertumbuhan, dividen);
			} else if(jenisAset.equals("OBLIGASI")) {
				// Saat jenisAset adalah obligasi maka lakukan perhitungan saham dan inisiasikan
				// aset sebagai obligasi
				totalObligasi += 1;
				double bunga = Double.valueOf(inp[4]);
				int maturitas = Integer.valueOf(inp[5]);
				portofolio[i] = new Obligasi(namaAset, jumlah, harga, bunga, maturitas);
			} 
		}

		// Membuat tampilan layar yang mudah dibaca oleh pengguna
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
		
		// Selama program belum diminta berhenti maka lakukan pencetakan tampilan
		// dan permintaan perintah oleh pengguna
		while(true) {
			System.out.printf("" +
			"Silakan pilih salah satu opsi berikut:\n" +
			"[1] Daftar aset\n" +
			"[2] Info portofolio\n" +
			"[3] Lanjut ke tahun berikutnya\n" +
			"[*] Keluar\n");
			printSeparator();
			System.out.print("Input: ");

			// Mengambil command dari pengguna
			String pilihan = in.nextLine();

			// Menyesuaikan command yang dipilih dengan method masing-masing
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
		
		// Menutup Scanner
		in.close();
	}
	
	// Method digunakan untuk menambahkan earnings pengguna
	public static void addToEarnings(double jumlah) {
		earnings += jumlah;
	}
}
