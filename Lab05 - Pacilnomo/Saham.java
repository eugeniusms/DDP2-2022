
public class Saham extends Aset{
	// TODO lengkapi visibility modifier atribut dan methods berikut
	private double dividen;
	private double pertumbuhan;
	
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {
		super(nama, jumlah, harga);
		this.pertumbuhan = pertumbuhan;
		this.dividen = dividen;
	}

	@Override
	public void nextYear() {
		super.nextYear();
		grow();

		// TODO modifikasi harga sesuai dengan pertumbuhan sekarang dan tambahkan dividen ke earnings
	}

	// Linear congruential generator for subsequent growth
	public void grow() {
		int a = 0x4b;
		int c = 0x4a;
		int m = 2;
		pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
		pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
	}

	// TODO lengkapi method toString ini
	@Override
	public String toString() {
		return this.getNama() + "\n"
			+ "Tipe: Saham" + "\n"
			+ "Harga: " + this.getHarga() + "\n"
			+ "Jumlah: " + this.getJumlah() + "\n"
			+ "Dividen: " + this.dividen + "\n"
			+ "Pertumbuhan: " + this.pertumbuhan;
	}

	// TODO buat getter dan setter untuk fields pada class ini
}
