import java.io.*;
import java.util.*;

// Program ini digunakan untuk mengecek tingkat kesehatan mahasiswa
public class HealthWorthinessChecker {
    // Variabel ini merupakan variabel minimum tingkat kesehatan layak
    private final static int MINIMUM_TINGKAT_KESEHATAN = 70;

    // Program utama berjalan dalam method main
    public static void main(String[] args) {
        // Mengambil Scanner dan menginisiasikan beberapa properti I/O
        Scanner in = new Scanner(System.in);
        Scanner inputFile = null;
        PrintWriter outputFile = null;
        int jumlahMahasiswa = 0;

        // Mencetak tampilan untuk menggunakan program
        System.out.println("Selamat datang di program Health Worthiness Checker.");
        System.out.println("-------------------------------------------------------");

        // Mengambil inputan nama file masukan dan keluaran
        System.out.print("Silakan masukkan nama file masukan: ");
        String inputFileName = in.next();
        System.out.print("Silakan masukkan nama file keluaran: ");
        String outputFileName = in.next();

        System.out.println("-------------------------------------------------------");

        in.close();

        // Mengambil file yangs sesuai dengan namanya serta menjalankan pengambilan data mahasiswa
        try {
            // Mengambil file yang sesuai
            inputFile = new Scanner(new File(inputFileName));
            System.out.println("Data sedang diproses, harap menunggu...");

            // Mengambil data jumlah mahasiswa
            String line = inputFile.nextLine();

            jumlahMahasiswa = Integer.valueOf(line);
            // Inisiasi array penyimpanan data
            ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<>();

            // Gunakan counter (saat ganjil -> ambil nama, sebaliknya ambil indikator dan susun objek)
            int counter = 0;
            // Inisiasi nama dan indikator mahasiswa
            String nama = "";
            int indikator = 0;
            while (inputFile.hasNextLine()) {
                // Mengambil line
                line = inputFile.nextLine();
                // Saat genap maka ambil nama mahasiswa
                if (counter % 2 == 0) {
                    nama = line;
                } else {
                    // Saat ganjil maka ambil indikator dan inisiasi objek mahasiswa
                    // Reset indikator ke 0
                    indikator = 0;
                    // Lakukan penyusunan nama dan indikator
                    for (int i = 0; i < line.length(); i++) {
                        char ch = line.charAt(i);
                        // Saat char adalah spasi maka continue saja
                        if (ch == ' ') {
                            continue;
                        } 
                        // Saat tipe ASCII -> integer maka tambahkan ke jumlah indikator
                        // Bukti : Setiap indikator hanya bernilai 1 - 5 saja (tidak ada dua digit)
                        if (ch >= '0' && ch <= '9') {
                            // ASCII '0' adalah karakter ke 48
                            indikator += ch - 48;
                        }
                    }
                    // Nilai kesehatan adalah 2 x jumlah indikator
                    int tingkatKesehatan = 2 * indikator;
                    // Inisiasi objek mahasiswa
                    Mahasiswa mahasiswa = new Mahasiswa(nama, tingkatKesehatan);
                    dataMahasiswa.add(mahasiswa);
                }
                // Menambahkan counter
                counter++;
            }

            // Proses data mahasiswa
            System.out.println("\nLOG:");
            for (int i = 0; i < jumlahMahasiswa; i++) {
                // Ambil data mahasiswa yang akan dicek
                Mahasiswa mahasiswa = dataMahasiswa.get(i);
                String namaMahasiswa = mahasiswa.getNama();
                int tingkatKesehatanMahasiswa = mahasiswa.getTingkatKesehatan();

                // Lakukan try catch untuk menentukan layak tidaknya kesehatan
                try {
                    // Saat tingkatKesehatanMahasiswa lebih atau sama dengan MINIMUM_TINGKAT_KESEHATAN maka layak
                    if (tingkatKesehatanMahasiswa >= MINIMUM_TINGKAT_KESEHATAN) {
                        System.out.println(namaMahasiswa + ": LAYAK");
                    } else {
                        // Saat tidak memenuhi standar kesehatan maka lemparkan error
                        throw new HealthinessUnworthyException(namaMahasiswa);
                    }
                } catch (HealthinessUnworthyException e) {
                    // Saat tidak layak maka tampilkan pesan sebagai berikut
                    System.out.println(namaMahasiswa + ": TIDAK LAYAK");
                }
            }

            // Output ke teks
            outputFile = new PrintWriter(new File(outputFileName));

            // Mencetak output ke dalam file baru
            outputFile.write("Nama Mahasiswa          | Status\n");
            outputFile.write("-------------------------------------------------------\n");
            // Untuk setiap mahasiswa lakukan pencetakan menggunakan data yang ada
            for (int i = 0; i < jumlahMahasiswa; i++) {
                String keterangan = dataMahasiswa.get(i).toString();
                outputFile.write(keterangan);
            }

            // Mencetak data mahasiswa berhasil diproses
            System.out.println("\nData mahasiswa berhasil diproses!");

        } catch (FileNotFoundException e) { // 
            // Saat file tidak ditemukan maka keluarkan file masukan tidak ditemukan
            System.out.println("ERROR: File masukan tidak ditemukan.");
        } finally {
            // Finally dari try catch adalah close file
            if (inputFile != null) {
                inputFile.close();
            }
            if (outputFile != null) {
                outputFile.close();
            }
        }

        // Mencetak tampilan dari akhiran program
        System.out.println("-------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program Health Worthiness Checker.");
    }
}
