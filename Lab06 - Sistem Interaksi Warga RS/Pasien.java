public class Pasien extends Warga {
	// Private modifier atribut pada class Pasien agar code menjadi lebih aman
	private int happiness;
	private String penyakit;
	private boolean pasienSembuh;

	// Constructor, saat inisiasi happiness masih 0, pasien masih sakit (false)
	Pasien(String nama, String penyakit) {
		super(nama, "Pasien"); // Memanggil constructor superclass menyusun nama warga
		this.happiness = 0;
		this.penyakit = penyakit;
		this.pasienSembuh = false;
	}

	// Method berinteraksi untuk pasien
	@Override
	public void berinteraksi(Warga X) {
		// Menambah log interaksi dengan warga X
		this.addLogInteraksi(X);
		// Menambah happiness sesuai kriteria 
		if (X.getJenisWarga().equals("Dokter")) { // Saat X => Dokter
			// Saat dokter ramah tambahkan 10 jika tidak kurangi 5
			if (X.getDokterRamah() == true) {
				this.happiness += 10;
			} else {
				this.happiness -= 5;
			}
			// Saat pasien masih sakit dan dokter tersebut dapat menyembuhkan maka tambahkan 20
			if (this.pasienSembuh == false && X.getPenyakitKeahlian().toLowerCase().equals(this.penyakit.toLowerCase())) {
				this.pasienSembuh = true;
				this.happiness += 20;
			}
		} else { // Saat X => Pasien (bertemu pasien)
			this.happiness += 5;
		}

		// Mengatasi nilai yang berlebih dari 100 maupun berkurang dari 0
		if (this.happiness > 100) {
			this.happiness = 100;
		} else if (this.happiness < 0) {
			this.happiness = 0;
		}
	}

	// Memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString();
	}

	// Getter untuk happiness
	public int getHappiness() {
		return this.happiness;
	}

	// Getter untuk status kesembuhan
	public boolean getStatusSembuh() {
		return this.pasienSembuh;
	}

	// Getter untuk penyakit
	public String getPenyakit() {
		return this.penyakit;
	}

}
