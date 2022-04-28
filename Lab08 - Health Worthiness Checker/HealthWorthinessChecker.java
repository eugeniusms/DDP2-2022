import java.io.*;
import java.util.*;

public class HealthWorthinessChecker {
    private static int MINIMUM_TINGKAT_KESEHATAN = 70;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner inputFile = null;
        PrintWriter outputFile = null;
        int jumlahMahasiswa = 0;

        System.out.println("Selamat datang di program Health Worthiness Checker.");
        System.out.println("-------------------------------------------------------");

        System.out.print("Silakan masukkan nama file masukan: ");
        String inputFileName = in.next();
        System.out.print("Silakan masukkan nama file keluaran: ");
        String outputFileName = in.next();

        System.out.println("-------------------------------------------------------");

        in.close();

        try {
            inputFile = new Scanner(new File(inputFileName));
            System.out.println("Data sedang diproses, harap menunggu...");

            String line = inputFile.nextLine();

            jumlahMahasiswa = Integer.valueOf(line);
            // TODO: Implementasi inisiasi array penyimpanan data
            ArrayList<Mahasiswa> dataMahasiswa = new ArrayList<>();

            // Gunakan counter (saat ganjil -> ambil nama, sebaliknya ambil indikator dan susun objek)
            int counter = 0;
            String nama = "";
            int indikator = 0;
            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();
                // TODO: Implementasi penyimpanan data mahasiswa ke array sesuai spesifikasi
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
                            indikator += ch - 48;
                            System.out.println("CEK " + ch + " | " + indikator); // TESTING
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

                try {
                    // TODO: Implementasi pengecekan tingkat kesehatan mahasiswa
                    if (tingkatKesehatanMahasiswa > 70) {
                        System.out.println(namaMahasiswa + ": LAYAK | " + tingkatKesehatanMahasiswa);
                    } else {
                        // Saat tidak memenuhi standar kesehatan maka lemparkan error
                        throw new HealthinessUnworthyException(namaMahasiswa);
                    }
                } catch (HealthinessUnworthyException e) {
                    // TODO: Implementasi output ketika mahasiswa tidak memenuhi kelayakan
                    System.out.println(namaMahasiswa + ": TIDAK LAYAK | " + tingkatKesehatanMahasiswa);
                }
            }

            // Output ke teks
            outputFile = new PrintWriter(new File(outputFileName));

            outputFile.write("Nama Mahasiswa          | Status\n");
            outputFile.write("-------------------------------------------------------\n");
            // TODO: Implementasi output data mahasiswa ke file output
            for (int i = 0; i < jumlahMahasiswa; i++) {
                String keterangan = dataMahasiswa.get(i).toString();
                outputFile.write(keterangan);
            }

            System.out.println("\nData mahasiswa berhasil diproses!");

        } catch (FileNotFoundException e) { // 
            // TODO: Implementasi output ketika file tidak ditemukan
            System.out.println("ERROR: File masukan tidak ditemukan.");
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
            if (outputFile != null) {
                outputFile.close();
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Terima kasih telah menggunakan program Health Worthiness Checker.");
    }
}
