
public class Saham extends Aset{
	// TODO lengkapi visibility modifier atribut dan methods berikut
	private double dividen;
	public double pertumbuhan;
	
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {
		super(nama, jumlah, harga);
		this.pertumbuhan = pertumbuhan;
		this.dividen = dividen;
	}

	@Override
	public double nextYear() {
		super.nextYear();
		grow();

		// TODO modifikasi harga sesuai dengan pertumbuhan sekarang dan tambahkan dividen ke earnings
		// Set harga baru karena kenaikan tahunan
		this.setHarga( this.getHarga() * (pertumbuhan + 1) );
		// Tambahkan Dividen
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

	// TODO lengkapi method toString ini
	@Override
	public String toString() {
		return this.getNama() + "\n"
			+ "Tipe: Saham" + "\n"
			+ "Harga: " + String.format("%.2f", this.getHarga()) + "\n"
			+ "Jumlah: " + this.getJumlah() + "\n"
			+ "Dividen: " + String.format("%.2f", this.dividen) + "\n"
			+ "Pertumbuhan: " + String.format("%.2f", this.pertumbuhan);
	}

	// TODO buat getter dan setter untuk fields pada class ini
}
