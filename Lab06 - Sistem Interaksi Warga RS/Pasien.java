public class Pasien extends Warga {
	// TODO: Ubah modifier atribut pada class Pasien agar code menjadi lebih aman
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

	// TODO: Lengkapi method berinteraksi untuk pasien
	@Override
	public void berinteraksi(Warga X) {
		this.addLogInteraksi(X);
		// ADD HAPPINESS
		if (X.getJenisWarga() == "Dokter") { // Saat X => Dokter
			// Cek dokter ramah atau tidak
			if (X.getDokterRamah() == true) {
				this.happiness += 10;
			} else {
				this.happiness -= 5;
			}

			// Saat dokter tersebut dapat menyembuhkan
			if (X.getPenyakitKeahlian() == this.penyakit) {
				this.pasienSembuh = true;
				this.happiness += 20;
			}


		} else { // Saat X => Pasien
			this.happiness += 5;
		}

		// CEK OVERVALUE
		if (this.happiness > 100) {
			this.happiness = 100;
		} else if (this.happiness < 0) {
			this.happiness = 0;
		}
	}

	// TODO: Lengkapi toString dengan memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString();
	}

	public int getHappiness() {
		return this.happiness;
	}

	public boolean getStatusSembuh() {
		return this.pasienSembuh;
	}

	public String getPenyakit() {
		return this.penyakit;
	}

}
