import java.io.*;
import java.util.StringTokenizer;

// Kelas Main (utama berjalannya program)
public class Main {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    // Gunakan out sebagai pengganti System.out
    // out ini akan menahan output sampai dia di-(close/flush)
    // Contoh jika ingin print("merah"), maka tulis out.print("merah")

    // Inisiasi variabel daftar pesanan
    private static DaftarPesanan<Makanan> daftarMakanan = new DaftarPesanan<>();
    private static DaftarPesanan<Minuman> daftarMinuman = new DaftarPesanan<>();

    // Method utama berjalannya program
    public static void main(String[] args) {
        // Mengambil masukan jumlah makanan dan jumlah minuman
        int jumlahMakanan = in.nextInt();
        int jumlahMinuman = in.nextInt();

        // Melakukan pengambilan data makanan
        for (int i = 0; i < jumlahMakanan; i++) {
            String namaMakanan = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            int tingkatKepedasan = in.nextInt();
            
            // Menambahkan data makanan baru ke daftar makanan
            Makanan baru = new Makanan(namaMakanan, harga, prioritas, tingkatKepedasan);
            daftarMakanan.tambahPesanan(baru);
        }

        // Melakukan pengambilan data minuman
        for (int i = 0; i < jumlahMinuman; i++) {
            String namaMinuman = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            boolean isPakeEs = in.next().equals("YES");
            
            // Menambahkan data minuman baru ke daftar minuman
            Minuman baru = new Minuman(namaMinuman, harga, prioritas, isPakeEs);
            daftarMinuman.tambahPesanan(baru);
        }

        // Mengurutkan daftar berdasar prioritas
        daftarMakanan.urutkanPrioritas();
        daftarMinuman.urutkanPrioritas();

        // Untuk setiap command yang dimasukkan jalankan sebagai berikut
        while (true) {
            // Mengambil command
            String command = in.next();

            // Keluar saat command == KELUAR
            if (command.equals("KELUAR")) {
                break;
            }

            // Mengambil tipe
            String tipe = in.next();

            // Penyesuaian tipe
            if (tipe.equals("MAKANAN")) {
                // Serve Makanan
                if (daftarMakanan.getPointer() < jumlahMakanan) {
                    out.print(daftarMakanan.nextPesanan().toString() + " telah disajikan.\n");
                } else {
                    // Saat sudah semua disajikan
                    out.print("Semua pesanan makanan telah disajikan!\n");
                }
            } else {
                // Serve Minuman
                if (daftarMinuman.getPointer() < jumlahMinuman) {
                    out.print(daftarMinuman.nextPesanan().toString() + " telah disajikan.\n");
                } else {
                    // Saat sudah semua disajikan
                    out.print("Semua pesanan minuman telah disajikan!\n");
                }
            }
        }

        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    private static class InputReader {
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
