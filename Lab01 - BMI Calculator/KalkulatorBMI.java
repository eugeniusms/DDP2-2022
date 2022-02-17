import java.util.Scanner;

public class KalkulatorBMI {
    public static void main(String [] args) {
        /* Program ini digunakan untuk mencari data kuantitatif BMI dari beberapa mahasiswa.
        Terdapat 4 kasus perhitungan skala BMI mahasiswa:
          1) Berat badan di bawah normal (BMI < 18.5)
          2) Berat badan normal (18.5 <= BMI < 25)
          3) Berat badan di atas normal (25 <= BMI < 30)
          4) Berat badan obesitas (BMI >= 30)
        Program akan meminta input jumlah mahasiswa yang akan didata, kemudian setiap 
        pendataan akan diberikan opsi dua buah skala ukuran yaitu:
          a) METRIK (kilogram, sentimeter)
          b) IMPERIAL (pon, inci)
        Setelah itu program akan menghitung BMI melalui method calculate() dan mengirimkan 
        hasilnya kembali untuk pendataan dengan variabel xWeight (sesuai 4 kasus di atas) */

        // Menginisiasi input sebagai Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("Selamat datang di program kalkulator BMI!");
        System.out.println("--------------------------------------------------------");
        System.out.printf("Masukkan jumlah mahasiswa yang akan dihitung datanya: ");

        // Mengambil jumlah mahasiswa
        int student = input.nextInt();

         // Menginisiasi nilai 0 pada setiap kriteria berat badan mahasiswa
        int underWeight = 0;
        int normalWeight = 0;
        int overWeight = 0;
        int obesWeight = 0;

        // Melakukan perulangan pengambilan data mahasiswa untuk dikalkulasi dan dijumlahkan
        for (int i = 0; i < student; i++) {
            System.out.printf("--------------------DATA MAHASISWA %d--------------------\n", i+1);
            System.out.print("Standar pengukuran apakah yang digunakan? ");

            // Menentukan skala yang akan digunakan ke dalam tampilan layar program
            String massScale = "kilogram";
            String heightScale = "sentimeter"; 
            String scale = input.next();
            // Saat scale == "IMPERIAL" maka ubah tampilan default kg/cm jadi pon/inch
            if (scale.equals("IMPERIAL")) {
                massScale = "pon";
                heightScale = "inci"; 
            }

            // Meminta input data massa dan tinggi badan ke user
            System.out.printf("Masukkan massa tubuh mahasiswa (%s) : ", massScale);
            double mass = input.nextDouble();
            System.out.printf("Masukkan tinggi tubuh mahasiswa (%s) : ", heightScale);
            double height = input.nextDouble();

            // Melakukan perhitungan BMI dan mengembalikannya ke dalam variabel bmi
            double bmi = calculate(mass, height, scale);

            // Melakukan penyesuaian BMI terhadap kriteria dari 4 kasus 
            if (bmi < 18.5) {
                underWeight++;
            } else if (bmi < 25) {
                normalWeight++;
            } else if (bmi < 30) {
                overWeight++;
            } else if (bmi >= 30) {
                obesWeight++;
            }
            System.out.printf("BMI : %f\n", bmi);
        }
        // Menutup Scanner input
        input.close();
        
        // Melakukan pencetakan data kuantitatif berdasarkan kriteria yang ada ke tampilan layar
        System.out.println("---------------------RINGKASAN DATA---------------------");
        System.out.printf("Berikut merupakan ringkasan hasil pengukuran BMI dari %d mahasiswa.\n", student);
        System.out.printf("Jumlah mahasiswa dengan berat badan di bawah normal: %d\n", underWeight);
        System.out.printf("Jumlah mahasiswa dengan berat badan normal: %d\n", normalWeight);
        System.out.printf("Jumlah mahasiswa dengan berat badan di atas normal: %d\n", overWeight);
        System.out.printf("Jumlah mahasiswa obesitas: %d\n", obesWeight);
        System.out.println("--------------------------------------------------------");
        System.out.printf("Terima kasih telah menggunakan program kalkulator BMI!");
    }

    public static double calculate(double mass, double height, String scale) {
        /* Method ini digunakan untuk mencari nilai BMI dari data yang diberikan oleh user, 
        terdapat 3 argumen fungsi yaitu massa, tinggi, dan skala yang digunakan user. Setelah
        selesai dilakukan perhitungan, maka data akan dikirimkan kembali kepada main program
        di dalam variabel bmi */

        double result = mass / Math.pow(height/100, 2); // Rumus BMI skala METRIK
        // Jika skala = "IMPERIAL", maka timpa perhitungan result METRIK -> IMPERIAL
        if (scale.equals("IMPERIAL")) {
            result = 703 * mass / Math.pow(height, 2); // Rumus BMI skala IMPERIAL
        }
        return result;
    }
}