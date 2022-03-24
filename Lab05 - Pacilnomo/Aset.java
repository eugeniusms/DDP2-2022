// Aset merupakan superclass dari Obligasi dan Saham
public class Aset {
	// Inisiasi data field class Aset
	private String nama;
	private int jumlah;
	private double harga;
	public int tahun = 0;
	
	// Constructor Aset
	public Aset(String nama, int jumlah, double harga) {
		this.nama = nama;
		this.jumlah = jumlah;
		this.harga = harga;
	}

	// Method untuk menambahkan tahun terkini
	public double nextYear() {
		tahun += 1;
		return 0;
	}

	// Getter Nama
	public String getNama() {
		return this.nama;
	}

	// Setter Nama
	public void setNama(String namaBaru) {
		this.nama = namaBaru;
	}

	// Getter Jumlah
	public int getJumlah() {
		return this.jumlah;
	}

	// Setter Jumlah
	public void setJumlah(int jumlahBaru) {
		this.jumlah = jumlahBaru;
	}

	// Getter Harga
	public double getHarga() {
		return this.harga;
	}

	// Setter Harga
	public void setHarga(double hargaBaru) {
		this.harga = hargaBaru;
	}

	// Getter NetWorth (Harga saham/obligasi * jumlah)
	public double getNetWorth() {
		return this.getHarga() * this.getJumlah();
	}
}
