import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


class RumahSakit {

    private static InputReader in;
    private static PrintWriter out;
    static Map<String, Warga> daftarWarga1 = new HashMap<String, Warga>();
    Warga[] daftarWarga = new Warga[0];

    // Method ini digunakan untuk mencetak tampilan handleLog warga
    static private void handleLog(Warga X) {
		// Saat log warga pasien maka cetak happiness dan status sembuh
		if (X.getJenisWarga().equals("Pasien")) { 
			out.println(X.getHappiness()); 
			out.println(X.getStatusSembuh()); 
		} else { 
            // Saat log warga dokter maka cetak jumlah pasien yang ditemui
			out.println(X.getJumlahPasienDitemui()); // TODO: Lengkapi dengan jumlah pasien yang ditemui dokter
		}


		for (int i = 0; i < X.getLogInteraksi().length; i++){ // ArrayList.size() digunakan untuk mencari ukuran arraylist
			out.println(X.getLogInteraksi()[i]); // ArrayList.get(i) digunakan untuk mengambil isi dari arraylist pada index ke-i
		}
		out.println("------------");;
        
    }

    // Method ini digunakan untuk menjalankan interaksi antarwarga
    static private void handleInteraksi(Warga X, Warga Y) {
		X.berinteraksi(Y);
		Y.berinteraksi(X);
    }

    // Method untuk menambahkan daftar warga
    private void masukkanKeDaftarWarga(Warga objWarga){
        Warga[] newDaftarWarga = new Warga[this.daftarWarga.length+1];

        for(int i = 0; i < this.daftarWarga.length; i++){
            newDaftarWarga[i] = this.daftarWarga[i];
        }
        this.daftarWarga = newDaftarWarga;

        newDaftarWarga[this.daftarWarga.length -1] = objWarga;
    }

    // Method untuk mendapat objek warga berdasarkan nama
    private Warga getWarga(String nama){
        for(Warga warga: this.daftarWarga){
            if(warga.getNama().equalsIgnoreCase(nama)){
                return warga;
            }
        }
        return null;
    }

    // Method utama program berjalan
    private void mainProgram(){

        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        int N;
        
        // Mengambil jumlah command
        N = in.nextInt();
        for(int tmp=0;tmp<N;tmp++) {
            String event = in.next();

            // Penjalanan command "ADD"
            if(event.equals("ADD")) {
                // Mengambil input user
                String roleWarga = in.next();
                String nama = in.next();
                // Saat rolenya adalah Dokter maka inisiasikan object dokter dan tambahkan ke warga[]
				if (roleWarga.equals("DOKTER")){
					String penyakitKeahlian = in.next();
					boolean dokterRamah = in.next().equals("Yes") ? true : false;
					// TODO: Buat objek Dokter dan masukkan ke dalam Map daftarWarga dengan memanggil method masukkanKeMap(nama, objekDokter)
                    Dokter dokterBaru = new Dokter(nama, penyakitKeahlian, dokterRamah);
					daftarWarga1.put(nama, dokterBaru);
                    masukkanKeDaftarWarga(dokterBaru);
				} else {
                    // Saat rolenya adalah Pasien maka inisiasikan object pasien dan tambahkan ke warga[]
					String penyakit = in.next();
					// TODO: Buat objek Pasien dan masukkan ke dalam Map daftarWarga dengan memanggil method masukkanKeMap(nama, objekPasien)
                    Pasien pasienBaru = new Pasien(nama, penyakit);
                    daftarWarga1.put(nama, pasienBaru);
                    masukkanKeDaftarWarga(pasienBaru);
					
				}
            } else if(event.equals("INTERAKSI")) {
                // Penjalanan command "INTERAKSI"
                String X = in.next();
				String Y = in.next();
				handleInteraksi(getWarga(X), getWarga(Y)); 
				// daftarWarga.get() Mengambil objek yang sesuai berdasarkan namanya pada daftar warga
            } else {
                // Penjalanan command "LOG"
                String X = in.next();
                handleLog(getWarga(X));
				// daftarWarga.get() Mengambil objek yang sesuai berdasarkan namanya pada daftar warga
            }
        }

        out.flush();
    }

    // Method pemulaian program
    public static void main(String[] args) throws IOException{
        RumahSakit rs = new RumahSakit();
        rs.mainProgram();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
    }
}