// Saham merupakan subclass dari Aset yang digunakan untuk inisiasi object saham
public class Saham extends Aset{
	// Inisiasi data field class Saham
	private double dividen;
	public double pertumbuhan;
	
	// Constructor Saham
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {
		super(nama, jumlah, harga);
		this.pertumbuhan = pertumbuhan;
		this.dividen = dividen;
	}

	// Method ini akan meng-override method yang ada di dalam superclass
	// Override digunakan khusus untuk kasus saham dalam hal ini,
	// Karena saham berbeda perhitungan dengan obligasi
	@Override
	public double nextYear() {
		// Akan menambahkan tahun berlangsungnya program
		super.nextYear();
		// Menyusun pertumbuhan tahunan menggunakan method grow()
		grow();

		// Set harga baru karena kenaikan tahunan
		this.setHarga( this.getHarga() * (pertumbuhan + 1) );
		// Menambahkan Dividen
		return this.dividen * this.getHarga() * this.getJumlah();
	}

	// Linear congruential generator for subsequent growth
	public void grow() {
		int a = 0x4b;
		int c = 0x4a;
		int m = 2;
		pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
		this.pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
	}

	// Method ini digunakan untuk menampilkan tampilan dari saham yang dimiliki
	// oleh pengguna, tampilan akan diformat sedemikian rupa agar mudah dibaca
	@Override
	public String toString() {
		// Mengembalikan format tampilan layar nantinya ke pengguna
		return this.getNama() + "\n"
			+ "Tipe: Saham" + "\n"
			+ "Harga: " + String.format("%.2f", this.getHarga()) + "\n"
			+ "Jumlah: " + this.getJumlah() + "\n"
			+ "Dividen: " + String.format("%.2f", this.dividen) + "\n"
			+ "Pertumbuhan: " + String.format("%.2f", this.pertumbuhan);
	}
}
