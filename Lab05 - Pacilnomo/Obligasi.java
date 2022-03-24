// Obligasi merupakan subclass dari Aset yang digunakan untuk inisiasi object obligasi
public class Obligasi extends Aset {
	// Inisiasi data field class Obligasi
	private double bunga;
	private int maturitas;
	private boolean jatuhTempo = false;
	
	// Constructor Obligasi
	Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
		// TODO lengkapi constructor ini
		super(nama, jumlah, harga);
		this.bunga = bunga;
		this.maturitas = maturitas;
	}

	// Method ini akan meng-override method yang ada di dalam superclass
	// Override digunakan khusus untuk kasus obligasi dalam hal ini,
	// Karena obligasi berbeda perhitungan dengan saham
	@Override
	public double nextYear() {
		// Saat tahun sudah melebihi maturitas dari obligasi maka obligasi akan jatuh tempo
		if (super.tahun > this.maturitas) {
			jatuhTempo = true;
		}
		// Menambahkan tahun berjalan
		super.nextYear();

		// Saat belum jatuh tempo maka masih dapat bertambah bunganya dan dikembalikan ke earnings
		if (jatuhTempo == false) {
			return this.getJumlah() * (this.getHarga() * this.bunga);
		} else {
			// Saat sudah jatuh tempo maka tidak bertambah bunganya
			return 0;
		}
	}

	// Method ini digunakan untuk menampilkan tampilan dari obligasi yang dimiliki
	// oleh pengguna, tampilan akan diformat sedemikian rupa agar mudah dibaca
	@Override
	public String toString() {
		// Pertama-tama cek terlebih dahulu tahun yang berjalan, jika sudah melewati
		// maturitas maka ganti sekaligus nilai dari jatuh temponya
		if (super.tahun > this.maturitas) {
			jatuhTempo = true;
		}

		// Mengembalikan format tampilan layar nantinya ke pengguna
		return this.getNama() + "\n"
			+ "Tipe: Obligasi" + "\n"
			+ "Harga: " + String.format("%.2f", this.getHarga()) + "\n"
			+ "Jumlah: " + this.getJumlah() + "\n"
			+ "Bunga: " + String.format("%.2f", this.bunga) + "\n"
			+ "Jatuh Tempo: " + jatuhTempo;
	}
}
