import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    // Gunakan out sebagai pengganti System.out
    // out ini akan menahan output sampai dia di-(close/flush)
    // Contoh jika ingin print("merah"), maka tulis out.print("merah")

    // TODO: Ubahlah "?" menjadi class yang tepat
    private static DaftarPesanan<Makanan> daftarMakanan = new DaftarPesanan<>();
    private static DaftarPesanan<Minuman> daftarMinuman = new DaftarPesanan<>();

    // TODO: Lengkapi method main
    public static void main(String[] args) {
        int jumlahMakanan = in.nextInt();
        int jumlahMinuman = in.nextInt();

        for (int i = 0; i < jumlahMakanan; i++) {
            String namaMakanan = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            int tingkatKepedasan = in.nextInt();
            // TODO: tambahkan ke daftarMakanan
            Makanan baru = new Makanan(namaMakanan, harga, prioritas, tingkatKepedasan);
            daftarMakanan.tambahPesanan(baru);
        }

        for (int i = 0; i < jumlahMinuman; i++) {
            String namaMinuman = in.next();
            int harga = in.nextInt();
            int prioritas = in.nextInt();
            boolean isPakeEs = in.next().equals("YES");
            // TODO: tambahkan ke daftarMinuman
            Minuman baru = new Minuman(namaMinuman, harga, prioritas, isPakeEs);
            daftarMinuman.tambahPesanan(baru);
        }

        // Mengurutkan berdasar prioritas
        daftarMakanan.urutkanPrioritas();
        daftarMinuman.urutkanPrioritas();

        while (true) {
            String command = in.next();
            if (command.equals("KELUAR")) {
                break;
            }

            String tipe = in.next();

            if (tipe.equals("MAKANAN")) {
                // TODO: serve makanan
                if (daftarMakanan.getPointer() < jumlahMakanan) {
                    out.print(daftarMakanan.nextPesanan().toString() + " telah disajikan.\n");
                } else {
                    out.print("Semua pesanan makanan telah disajikan!\n");
                }
            } else {
                // TODO: serve minuman
                if (daftarMinuman.getPointer() < jumlahMinuman) {
                    out.print(daftarMinuman.nextPesanan().toString() + " telah disajikan.\n");
                } else {
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
