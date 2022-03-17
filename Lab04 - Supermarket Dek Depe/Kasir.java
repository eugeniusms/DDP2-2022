import java.io.*;
import java.util.*;

public class Kasir {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    //Gunakan out sebagai pengganti System.out
    //out ini akan menahan output sampai dia di-(close/flush)
    //Contoh jika ingin print("merah"), maka tulis out.print("merah")
  
    static Barang[] barang;
    static Pelanggan[] pelanggan;
    static int N, M;

    static Pelanggan cariPelanggan(String nama) {
        for (Pelanggan p: pelanggan) {
            if (nama.equals(p.getNama())) {
                return p;
            }
        }
        return null;
    }
    
    static Barang cariBarang(String namaBarang) {
        for (Barang b: barang) {
            if (namaBarang.equals(b.getNama())) {
                return b;
            }
        }
        return null;
    }

    // TODO lengkapi method di bawah ini
    static void kasir(Pelanggan K){
        // Menampilkan tampilan total belanja

        // Jika tidak ada barang keranjang len = 0 kembalikan tidak ada
        if (K.getKeranjang().length == 0) {
            out.println("Maaf tidak ada barang di keranjang " + K.getNama());
        } else {
            // Saat ada barang di keranjang
            // Cetak tampilan printout
            out.println("Pembelian " + K.getNama() + " berhasil:");
            for (Order ord: K.getKeranjang()) {
                out.println("* " + ord.getBarang().getNama() + " " + ord.getBanyakBarang() + " = " 
                        + ord.getBarang().getHarga() * ord.getBanyakBarang());
            }
            out.println("* Total Belanjaan = " + K.totalHargaBarang());
            out.println("* Sisa Uang = " + K.cekUang());

            // Reset Isi keranjang Udin
            K.setKeranjangToZero();
        }
    }
    
    public static void main(String[] args) {
        N = in.nextInt();
        // Array untuk menyimpan semua barang pelanggan
        barang = new Barang[N];
        for (int i = 0; i < N; i++) {
            String namaBarang = in.next();
            int hargaBarang = in.nextInt();
            int beratBarang = in.nextInt();
            int stock = in.nextInt();
            
            //TODO: Construct Barang baru (CLEAR)
            // Menambahkan barang ke dalam array
            barang[i] = new Barang(namaBarang, hargaBarang, beratBarang, stock);
        }

        M = in.nextInt();
        pelanggan = new Pelanggan[M];
        for (int j = 0; j < M; j++) {
            String namaPelanggan = in.next();
            int uang = in.nextInt();

            //TODO: Construct Pelanggan baru
            // Menambah pelanggan baru ke kumpulan object pelanggan dan setel 
            // default dari kapasitas adalah 5000
            pelanggan[j] = new Pelanggan(namaPelanggan, uang, 5000);
        }
        
        int P = in.nextInt();
        for (int k = 0; k < P; k++) {
            String command = in.next();
            
            if (command.equals("ADD")) {
                String namaPelanggan = in.next();
                String namaBarang = in.next();
                int banyakBarangDiambil = in.nextInt();
                
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.print(plg.addBarang(cariBarang(namaBarang), banyakBarangDiambil));
            }
            
            if (command.equals("TOTAL_HARGA")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.printf("Total harga belanjaan %s adalah %d%n", plg.getNama(), plg.totalHargaBarang());
            }
            
            if (command.equals("KASIR")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                kasir(plg);
            }
            
            if (command.equals("CEK_UANG")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.print(plg.cekUang());
            }
        }
        
        // don't forget to close/flush the output
        out.close(); 
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
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