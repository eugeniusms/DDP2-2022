import java.io.*;
import java.util.*;
//Revisi: revisi line 40
public class Main {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    static Mobil[] daftarMobil;
  
    public static void main(String[] args) {      
        int P = in.nextInt();
        daftarMobil = new Mobil[P];
        for(int i = 0; i < P; i++){
            String nama = in.next();
            String jenis = in.next();    // dijamin salah satu dari Terbang, Air, Truk
            String behaviour = in.next();  // dijamin salah satu dari Listrik atau BBM
            String bahanBakar = in.next();

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
        
        int Q = in.nextInt();
        for(int i = 0; i < Q; i++) {
            String nama = in.next();
            String perintah = in.next();

            Mobil mobilNow = null;
            // Mobil dijamin ada, tidak perlu handle kasus null
            for(Mobil m : daftarMobil) {
                if(m.getNama().equals(nama)){
                    mobilNow = m;
                    break;
                }
            }
            
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