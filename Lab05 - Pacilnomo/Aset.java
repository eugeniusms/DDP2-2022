
public class Aset {
	// TODO lengkapi visibility modifier atribut dan methods berikut (CLEAR)
	private String nama;
	private int jumlah;
	private double harga;
	public int tahun = 0;
	
	public Aset(String nama, int jumlah, double harga) {
		// TODO lengkapi constructor ini
		this.nama = nama;
		this.jumlah = jumlah;
		this.harga = harga;
	}
	
	public double kirimUang() {
		return 0;
	} 

	// Increment tahun
	public double nextYear() {
		tahun += 1;
		return 0;
	}

	// TODO buat getter dan setter untuk fields pada class ini
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

	// Getter NetWorth
	public double getNetWorth() {
		return this.getHarga() * this.getJumlah();
	}
}
