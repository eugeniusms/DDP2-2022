public class Dokter extends Warga {
	// Mengubah modifier atribut pada class Dokter menjadi private agar code menjadi lebih aman
	private int jumlahPasienDitemui;
	private String penyakitKeahlian;
	private boolean dokterRamah;
	
	// Constructor, saat awal inisiasi jumlah pasien yang ditemui masih nol
	Dokter(String nama, String penyakitKeahlian, boolean dokterRamah) {
		super(nama, "Dokter"); // Memanggil constructor superclass menyusun nama warga
		this.jumlahPasienDitemui = 0;
		this.penyakitKeahlian = penyakitKeahlian;
		this.dokterRamah = dokterRamah;
	}

	// Method berinteraksi untuk dokter
	@Override
	public void berinteraksi(Warga X){
		this.addLogInteraksi(X);
		if (X.getJenisWarga().equals("Pasien")) {
			// Menambahkan jumlah pasien yang ditemui oleh dokter
			this.jumlahPasienDitemui += 1;
		}
	}

	// Memanggil method toString milik superclass
	@Override
	public String toString() {
		return super.toString();
	}

	// Getter untuk jumlah pasien yang ditemui
	public int getJumlahPasienDitemui(){
		return this.jumlahPasienDitemui;
	}

	// Getter untuk dokter ahli penyakit apa
	public String getPenyakitKeahlian(){
		return this.penyakitKeahlian;
	}

	// Getter untuk mengambil data dokter ramah atau tidak
	public boolean getDokterRamah(){
		return this.dokterRamah;
	}

}
