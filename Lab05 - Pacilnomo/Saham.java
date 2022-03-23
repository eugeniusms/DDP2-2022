
public class Saham {
	// TODO lengkapi visibility modifier atribut dan methods berikut
	double dividen;
	double pertumbuhan;
	
	Saham(String nama, int jumlah, double harga, double pertumbuhan, double dividen) {

	}

	@Override
	void nextYear() {
		super.nextYear();
		grow();

		// TODO modifikasi harga sesuai dengan pertumbuhan sekarang dan tambahkan dividen ke earnings
	}

	// Linear congruential generator for subsequent growth
	void grow() {
		int a = 0x4b;
		int c = 0x4a;
		int m = 2;
		pertumbuhan = ((a * pertumbuhan + c) % m) - 1;
		pertumbuhan = pertumbuhan < 0 ? pertumbuhan % -m : pertumbuhan;
	}

	// TODO lengkapi method toString ini
	@Override
	String toString() {
		
	}

	// TODO buat getter dan setter untuk fields pada class ini
}
