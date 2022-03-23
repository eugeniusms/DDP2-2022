
public class Obligasi {
	// TODO lengkapi visibility modifier atribut dan methods berikut
	double bunga;
	int maturitas;
	boolean jatuhTempo = false;
	
	Obligasi(String nama, int jumlah, double harga, double bunga, int maturitas) {
		// TODO lengkapi constructor ini
	}
	
	@Override
	void nextYear() {
		// TODO validasi jatuh tempo

		super.nextYear();
		// TODO tambahkan bunga ke total pendapatan Pacilnomo 

	}

	// TODO lengkapi method toString ini
	@Override
	String toString() {

	}

	// TODO buat getter dan setter untuk fields pada class ini
}
