import java.util.Scanner;

public class ValidasiKodeIMEI {
    public static void main(String[] args) {
        /* Program ini digunakan untuk mengecek validitas dari
        kode IMEI ponsel. Terdapat beberapa masukan IMEI yang
        dapat dicek oleh program melalui berbagai tahapan:
        1. Cek jumlah digit dan kode inisial di firstCheck()
        2. Cek jumlah digit indeks ganjil & genap di 
           sumOddIndex() dan sumEvenIndex() melalui
           validityCheck()
        Kemudian, hasil cek IMEI akan dikumpulkan dalam array
        codeData berupa "YES"/"NO" untuk ditampilkan ke pengguna */

        // Menginisiasi Scanner input
        Scanner input = new Scanner(System.in);
        // Mengambil data total masukan dan menginisiasi codeData[]
        int total = input.nextInt();
        String[] codeData =  new String[total];

        // Mengambil data IMEI dan memasukan dalam codeData[]
        for (int i = 0; i < total; i++) {
            long temp = input.nextLong();
            // Mengecek jumlah digit dan inisial kode dengan firstCheck()
            if (firstCheck(temp)) {
                codeData[i] = validityCheck(temp);
            } else {
                codeData[i] = "NO";
            }
        }

        // Mencetak hasil pengecekan validitas IMEI (YES/NO)
        for (int i = 0; i < total; i++) {
            System.out.println(codeData[i]);
        }
        // Menutup Scanner input
        input.close();
    }

    public static boolean firstCheck(long code) {
        /* Method ini digunakan untuk mengecek jumlah digit kode
        yang diberikan oleh pengguna dan juga mengecek kode inisial:
        2 : Produksi Zimbabwe
        18 : Produksi China 
        Method akan mengembalikan nilai true jika kondisi sesuai
        seperti penjelasan di atas */

        // Cek panjang digit
        if (Math.floor(code / Math.pow(10,10)) < 10) { 
            // Cek inisial kode
            if ((Math.floor(code / Math.pow(10,10)) == 2) 
                || (Math.floor(code / Math.pow(10,9)) == 18)) { 
                return true;
            }
        }
        return false;
    }

    public static String validityCheck(long code) {
        /* Method ini digunakan sebagai pengantar untuk mengambil
        data jumlah perkalian 2 digit indeks ganjil dengan jumlah 
        digit indeks genap melalui method:
        - sumOddIndex() 
        - sumEvenIndex()
        Setelah didapati jumlah kemudian dicek sisa bagi dengan 10,
        jika habis terbagi maka kembalikan nilai "YES", jika sebaliknya
        kembalikan nilai "NO" */

        // Mengambil jumlahan digit
        long totalOdd = sumOddIndex(code);
        // Memotong code menjadi 10 digit terdepan (khusus genap)
        long totalEven = sumEvenIndex((long) code / 10);

        // Cek sisa bagi 10
        if ((totalOdd + totalEven) % 10 == 0) { 
            return "YES";
        } else {
            return "NO";
        }
    }

    public static long sumOddIndex(long code) {
        /* Method ini digunakan secara rekursif untuk menjumlahkan
        hasil perkalian 2 digit-digit berindeks ganjil, jika hasil
        perkalian > 9 maka jumlahkan 1 + (hasil % 10), karena selalu
        11 digit setelah melewati firstCheck() maka operasi akan dimulai
        dari indeks terakhir = indeks 11 (ganjil) */

        if (code == 0) { // base case
            return 0;
        } else { // recursive case
            // Menentukan digit dan kode yang diolah selanjutnya
            long lastDigit = code % 10;
            long newCode = (long) code / 100;

            // Memanggil total jumlahan setiap kali recursive case lebih dalam
            if (lastDigit*2 <= 9) { 
                // Jika hasil perkalian dengan dua < 9
                return lastDigit*2 + sumOddIndex(newCode);
            } else {
                // Jika hasil perkalian dengan dua < 9
                return 1 + (lastDigit*2 % 10) + sumOddIndex(newCode);
            }
        }
    }

    public static long sumEvenIndex(long code) {
        /* Method ini digunakan secara rekursif untuk menjumlahkan
        digit-digit berindeks genap, karena selalu 10 digit setelah 
        melewati pemotongan di validityCheck(), maka operasi akan dimulai
        dari indeks terakhir = indeks 10 (genap)*/

        if (code == 0) { // base case
            return 0;
        } else { // recursive case
            // Menentukan digit dan kode yang diolah selanjutnya
            long lastDigit = code % 10;
            long newCode = (long) code / 100; 

            // Memanggil total jumlahan setiap kali recursive case lebih dalam
            return lastDigit + sumEvenIndex(newCode);
        }
    }
}