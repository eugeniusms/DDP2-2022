public class Warga {
	// Protected sebagai modifier atribut pada class Warga agar hanya dapat diakses oleh subclass-subclassnya
	protected String nama;
	protected Warga[] logInteraksi = new Warga[0];
	protected String jenisWarga;

	// Constructor Warga, logInteraksi diinisiasi masih kosong
	Warga(String nama, String jenisWarga) {
		this.nama = nama;
		this.jenisWarga = jenisWarga;
		this.logInteraksi = new Warga[0];
	}

	public String getNama() {
		return nama;
	}
	// Method sengaja dikosongkan
	public void berinteraksi(Warga X){
	}

	// Method untuk menambah log interaksi
	public void addLogInteraksi(Warga warga){
		Warga[] newLog = new Warga[this.logInteraksi.length+1];

        for(int i = 0; i < this.logInteraksi.length; i++){
            newLog[i] = this.logInteraksi[i];
        }
        this.logInteraksi = newLog;

        newLog[this.logInteraksi.length -1] = warga;
	}
	
	// Method override mengembalikan nama warga
	@Override
	public String toString() {
		return this.nama;
	}

	// TODO: Lengkapi getLogInteraksi() dengan mengembalikan log interaksi warga
	public Warga[] getLogInteraksi(){
		return this.logInteraksi;
	}

	public String getJenisWarga() {
		return this.jenisWarga;
	}

	// Method override sengaja aku (Mario) default
	public int getHappiness() {
		return 0;
	}

	// Method override sengaja aku (Mario) default
	public boolean getStatusSembuh() {
		return false;
	}

	// Method override sengaja aku (Mario) default
	public int getJumlahPasienDitemui() {
		return 0;
	}

	// Method override sengaja aku (Mario) default
	public String getPenyakitKeahlian(){
		return "";
	}

	// Method override sengaja aku (Mario) default
	public boolean getDokterRamah(){
		return false;
	}
}
