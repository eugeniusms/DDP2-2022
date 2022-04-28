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

            while (inputFile.hasNextLine()) {
                line = inputFile.nextLine();
                // TODO: Implementasi penyimpanan data mahasiswa ke array sesuai spesifikasi
            }

            // Proses data mahasiswa
            System.out.println("\nLOG:");
            for (int i = 0; i < jumlahMahasiswa; i++) {
                try {
                    // TODO: Implementasi pengecekan tingkat kesehatan mahasiswa
                } catch (HealthinessUnworthyException e) {
                    // TODO: Implementasi output ketika mahasiswa tidak memenuhi kelayakan
                }
            }

            // Output ke teks
            outputFile = new PrintWriter(new File(outputFileName));

            outputFile.write("Nama Mahasiswa          | Status\n");
            outputFile.write("-------------------------------------------------------\n");
            // TODO: Implementasi output data mahasiswa ke file output

            System.out.println("\nData mahasiswa berhasil diproses!");

        } catch () {
            // TODO: Implementasi output ketika file tidak ditemukan
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
