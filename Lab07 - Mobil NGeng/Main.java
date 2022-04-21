// Mengimport java.io dan java.util
import java.io.*;
import java.util.*;
//Revisi: revisi line 40
public class Main {
    // Inisiasi inputReader dan printWriter
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    // Deklarasi variabel daftarMobil untuk menyimpan daftar mobil
    static Mobil[] daftarMobil;
  
    // Method main sebagai lokasi utama berjalannya program
    public static void main(String[] args) {    
        // Mengambil jumlah mobil yang ingin digunakan
        int P = in.nextInt();
        daftarMobil = new Mobil[P];

        // Mengambil data mobil oleh user
        for(int i = 0; i < P; i++){
            String nama = in.next();
            String jenis = in.next();    // dijamin salah satu dari Terbang, Air, Truk
            String behaviour = in.next();  // dijamin salah satu dari Listrik atau BBM
            String bahanBakar = in.next();

            // Mengisi jenis dan behavior mesin dari mobil yang bersangkutan serta jenis bahan bakarnya
            // Kemudian menginisiasi object sesuai dengan ketiga hal tersebut dan ditambahkan ke daftarMobil
            EngineBehaviour engineBehaviour = null;
            if(behaviour.equals("BBM")) engineBehaviour = new BBM();
            else if(behaviour.equals("Listrik")) engineBehaviour = new Listrik();
            
            if(jenis.equals("Truk")){
                daftarMobil[i] = new MobilTruk(nama, engineBehaviour, bahanBakar);
            } else if(jenis.equals("Terbang")){
                daftarMobil[i] = new MobilTerbang(nama, engineBehaviour, bahanBakar);
            } else {
                daftarMobil[i] = new MobilAir(nama, engineBehaviour, bahanBakar);
            }
        }
        
        // Mengambil jumlah command yang ingin dilakukan
        int Q = in.nextInt();
        // Mengambil setiap command oleh user
        for(int i = 0; i < Q; i++) {
            String nama = in.next();
            String perintah = in.next();

            // Saat mobil tidak ada 
            Mobil mobilNow = null;
            // Mobil dijamin ada, tidak perlu handle kasus null
            for(Mobil m : daftarMobil) {
                if(m.getNama().equals(nama)){
                    mobilNow = m;
                    break;
                }
            }
            
            // Penjalanan perintah ada beberapa hal
            // 1) START -> memulai penghidupan mesin
            // 2) GAS -> menjalankan mesin
            // 3) STOP -> mematikan mesin
            // 4) ISI -> mengisi bahan bakar mesin
            // 5) SIMULASI -> mensimulasikan jalannya mesin
            // Setelah command didapati program maka akan dicetak
            // berdasarkan return yang ada di dalam object methodnya
            if (perintah.equals("START")){
                out.println(mobilNow.start());
            }
            else if (perintah.equals("GAS")){
                out.println(mobilNow.gas());
            }
            else if (perintah.equals("STOP")) {
                out.println(mobilNow.stop());
            }
            else if(perintah.equals("ISI")){
                out.println(mobilNow.isiBahanBakar());
            }
            else if (perintah.equals("SIMULASI")){
                String[] res = mobilNow.simulasi();
                for(String s : res){
                    out.println(s);
                }
            }
        }

        // Menutup Scanner
        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
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