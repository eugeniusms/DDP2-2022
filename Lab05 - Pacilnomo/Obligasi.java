
public class Obligasi extends Aset {
	// TODO lengkapi visibility modifier atribut dan methods berikut
	private double bunga;
	private int maturitas;
	private boolean jatuhTempo = false;
	
	Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
		// TODO lengkapi constructor ini
		super(nama, jumlah, harga);
		this.bunga = bunga;
		this.maturitas = maturitas;
	}

	@Override
	public double nextYear() {
		// TODO validasi jatuh tempo
		if (super.tahun >= this.maturitas) {
			jatuhTempo = true;
		}
		super.nextYear();
		// TODO tambahkan bunga ke total pendapatan Pacilnomo 
		// Saat belum jatuh tempo maka masih dapat bertambah bunganya
		if (jatuhTempo == false) {
			return this.getJumlah() * (this.getHarga() * this.bunga);
		} else {
			// Saat sudah jatuh tempo maka tidak bertambah bunganya
			return 0;
		}
	}

	// TODO lengkapi method toString ini
	@Override
	public String toString() {
		return this.getNama() + "\n"
			+ "Tipe: Obligasi" + "\n"
			+ "Harga: " + this.getHarga() + "\n"
			+ "Jumlah: " + this.getJumlah() + "\n"
			+ "Bunga: " + this.bunga + "\n"
			+ "Jatuh Tempo: " + jatuhTempo;
	}
	// TODO buat getter dan setter untuk fields pada class ini
}
