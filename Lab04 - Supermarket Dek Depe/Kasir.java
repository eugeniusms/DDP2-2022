import java.io.*;
import java.util.*;

public class Kasir {
    // Kelas utama dari program di mana bertindak seperti kasir dan
    // Mengatur jalannya program
    
    // Inisiasi in dan out sebagai input dan output
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    //Gunakan out sebagai pengganti System.out
    //out ini akan menahan output sampai dia di-(close/flush)
    //Contoh jika ingin print("merah"), maka tulis out.print("merah")
  
    // Inisiasi data field
    static Barang[] barang;
    static Pelanggan[] pelanggan;
    static int N, M;

    // Method mencari pelanggan
    public static Pelanggan cariPelanggan(String nama) {
        for (Pelanggan p: pelanggan) {
            if (nama.equals(p.getNama())) {
                return p;
            }
        }
        return null;
    }
    
    // Method mencari barang
    public static Barang cariBarang(String namaBarang) {
        for (Barang b: barang) {
            if (namaBarang.equals(b.getNama())) {
                return b;
            }
        }
        return null;
    }

    // Method ini digunakan untuk menampilkan bon pelanggan
    static void kasir(Pelanggan K){

        // Jika tidak ada barang keranjang len = 0 kembalikan tidak ada
        if (K.getKeranjang().length == 0) {
            out.println("Maaf tidak ada barang di keranjang " + K.getNama());
        } else {
            // Saat ada barang di keranjang cek apakah uang sesuai 
            // Saat uang mencukupi checkout
            if (K.getUang() >= K.totalHargaBarang()) {
                // Kurangi uang pelanggan
                K.setUang(K.getUang() - K.totalHargaBarang());
                
                // Cetak tampilan printout
                out.println("Pembelian " + K.getNama() + " berhasil:");
                for (Order ord: K.getKeranjang()) {
                    out.println("* " + ord.getBarang().getNama() + " " + ord.getBanyakBarang() + " = " 
                            + ord.getBarang().getHarga() * ord.getBanyakBarang());
                }
                out.println("* Total Belanjaan = " + K.totalHargaBarang());
                out.println("* Sisa Uang = " + K.cekUang());

                
                // Reset isi keranjang pelanggan
                K.setKeranjangToZero();
            } else {
                // Saat uang tidak mencukupi maaf tidak cukup uang
                out.println("Maaf " + K.getNama() + " tidak memiliki cukup uang");
            }
        }
    }
    
    // Method utama dari program, program berjalan di dalam sini utamanya
    public static void main(String[] args) {
        // Mengambil jumlah barang untuk diinput ke toko
        N = in.nextInt();
        // Array untuk menyimpan semua barang pelanggan
        barang = new Barang[N];
        for (int i = 0; i < N; i++) {
            String namaBarang = in.next();
            int hargaBarang = in.nextInt();
            int beratBarang = in.nextInt();
            int stock = in.nextInt();
            
            // Menambahkan barang ke dalam array
            barang[i] = new Barang(namaBarang, hargaBarang, beratBarang, stock);
        }

        // Mengambil jumlah pelanggan yang ada di dalam sistem
        M = in.nextInt();
        pelanggan = new Pelanggan[M];
        for (int j = 0; j < M; j++) {
            String namaPelanggan = in.next();
            int uang = in.nextInt();

            // Menambah pelanggan baru ke kumpulan object pelanggan dan setel 
            // default dari kapasitas adalah 5000
            pelanggan[j] = new Pelanggan(namaPelanggan, uang, 5000);
        }
        
        // Melakukan pengecekan dalam sistem dan transaksi untuk ditampilkan ke terminal
        int P = in.nextInt();
        for (int k = 0; k < P; k++) {
            // Mengambil command
            String command = in.next();
            
            // Saat command ADD berarti kita akan menambahkan barang ke dalam keranjang pelanggan
            if (command.equals("ADD")) {
                String namaPelanggan = in.next();
                String namaBarang = in.next();
                int banyakBarangDiambil = in.nextInt();
                
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.print(plg.addBarang(cariBarang(namaBarang), banyakBarangDiambil));
            }
            
            // Saat command TOTAL_HARGA kita akan menampilkan total harga dari yang dibeli oleh pelanggan
            if (command.equals("TOTAL_HARGA")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.printf("Total harga belanjaan %s adalah %d%n", plg.getNama(), plg.totalHargaBarang());
            }
            
            // Saat command KASIR maka kita akan mengecek belian pelanggan dan mengurangi uangnya
            // jika sesuai dengan jumlah barang yang dibeli, kita juga akan menampilkan tampilan
            // bon dari pelanggan
            if (command.equals("KASIR")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                kasir(plg);
            }
            
            // Saat commaand CEK_UANG maka kita akan mengecek uang dari pelanggan
            if (command.equals("CEK_UANG")) {
                String namaPelanggan = in.next();
                Pelanggan plg = cariPelanggan(namaPelanggan);
                out.println("Uang " + plg.getNama() + " sekarang " + plg.cekUang());
            }
        }
        
        // don't forget to close/flush the output
        // Menutup out
        out.close(); 
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // Method tokenizer dan reader
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